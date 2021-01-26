package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.security.TokenUtils;
import com.hesoyam.pharmacy.user.DTO.ChangePasswordDTO;
import com.hesoyam.pharmacy.user.DTO.LoginDTO;
import com.hesoyam.pharmacy.user.DTO.UserTokenState;
import com.hesoyam.pharmacy.user.events.OnRegistrationCompletedEvent;
import com.hesoyam.pharmacy.user.exceptions.InvalidChangePasswordRequestException;
import com.hesoyam.pharmacy.user.exceptions.UserNotFoundException;
import com.hesoyam.pharmacy.user.exceptions.UserNotUniqueException;
import com.hesoyam.pharmacy.user.exceptions.RegistrationValidationException;
import com.hesoyam.pharmacy.user.model.*;
import com.hesoyam.pharmacy.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import com.hesoyam.pharmacy.user.DTO.RegistrationDTO;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//Controller used for user authentication
@RestController
@RequestMapping(value="/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    private static final String ERRORS_FIELD_NAME = "errors";
    private static final String DATA_FIELD_NAME = "data";

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private IUserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Map<String, String>>> register(@RequestBody @Valid RegistrationDTO registrationDTO, BindingResult errors){
        Map<String, String> errorsMap = new HashMap<>();
        if(errors.hasErrors()){
            errorsMap = extractFieldErrorsFromErrors(errors);
            return ResponseEntity.badRequest().body(generateUniformResponse(ERRORS_FIELD_NAME, errorsMap));
        }

        Patient patient;
        try {
            patient = userService.register(registrationDTO);
            applicationEventPublisher.publishEvent(new OnRegistrationCompletedEvent(patient, ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString()));
        } catch (RegistrationValidationException e) {
            return ResponseEntity.badRequest().body(generateUniformResponse(ERRORS_FIELD_NAME, e.getErrorMessagesMap()));
        }catch (UserNotUniqueException notUniqueException){
            errorsMap.put("emailNotUnique", notUniqueException.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(generateUniformResponse(ERRORS_FIELD_NAME, errorsMap));
        }
        return ResponseEntity.ok().body(generateUniformResponse(DATA_FIELD_NAME, extractUserDataToMap(patient)));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Map<String, String>>> login(@RequestBody @Valid LoginDTO loginDTO, BindingResult errors){
        Map<String, String> errorsMap;
        if(errors.hasErrors()){
            errorsMap = extractFieldErrorsFromErrors(errors);
            return ResponseEntity.badRequest().body(generateUniformResponse(ERRORS_FIELD_NAME, errorsMap));
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        //Save logged user in security context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //Create token for user
        User user = (User) authentication.getPrincipal();
        Role userRole = user.getUserRole();
        if(isPasswordChangeRequired(user)){
            return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body(generateUniformResponse(DATA_FIELD_NAME, changePasswordData()));
        }
        String jwt = tokenUtils.generateToken(user.getEmail());
        long expiresIn = tokenUtils.getExpiredIn();
        return ResponseEntity.ok().body(generateUniformResponse(DATA_FIELD_NAME, extractUserTokenStateToMap(new UserTokenState(jwt, expiresIn, userRole))));
    }

    private boolean isPasswordChangeRequired(User user){
        Role role = user.getUserRole();
        if(role == null) return false;

        return user.isPasswordReset();
    }


    @GetMapping("/confirm-registration")
    public ResponseEntity<Map<String, Map<String, String>>> confirmRegistration(@RequestParam("token") String token){
        VerificationToken verificationToken = userService.getVerificationToken(token);
        Map<String, String> error_map = new HashMap<>();


        if(verificationToken == null){
            error_map.put("tokenNotFound", "Activation token could not be found.");
            return ResponseEntity.badRequest().body(generateUniformResponse(ERRORS_FIELD_NAME, error_map));
        }

        if(isVerificationTokenExpired(verificationToken)){
            error_map.put("expiredToken", "Activation token has expired.");
            return  ResponseEntity.badRequest().body(generateUniformResponse(ERRORS_FIELD_NAME, error_map));
        }

        User user = verificationToken.getUser();

        user.setEnabled(true);

        try {
            userService.update(user);
        } catch (UserNotFoundException e) {
            error_map.put("internalServerError", "A user account connected with passed token could not be found. Try again later.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(generateUniformResponse(ERRORS_FIELD_NAME, error_map));
        }

        Map<String, String> data = new HashMap<>();
        data.put("activationSuccessful", "Activation successful. You can now log in.");
        return ResponseEntity.ok().body(generateUniformResponse(DATA_FIELD_NAME, data));
    }


    @GetMapping("/validate-token")
    public ResponseEntity<Map<String, Map<String, String>>> validateToken(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(isUserAuthenticated(authentication)){
            String token = tokenUtils.getToken(request);

            if(isTokenExpired(token)){
                SecurityContextHolder.getContext().setAuthentication(null);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(generateUniformResponse(ERRORS_FIELD_NAME, generateTokenExpiredResponse()));
            }

            return ResponseEntity.ok().body(generateUniformResponse(DATA_FIELD_NAME, generateTokenValidResponse()));
        }
        //If not authenticated
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(generateUniformResponse(ERRORS_FIELD_NAME, generateTokenExpiredResponse()));
    }

    @PostMapping("/register-sys-admin-account")
    @Secured("ROLE_SYS_ADMIN")
    public ResponseEntity<Map<String, Map<String, String>>> createSysAdmin(@RequestBody @Valid RegistrationDTO registrationDTO, BindingResult errors){
        Map<String, String> errorsMap = new HashMap<>();
        if(errors.hasErrors()){
            errorsMap = extractFieldErrorsFromErrors(errors);
            return ResponseEntity.badRequest().body(generateUniformResponse(ERRORS_FIELD_NAME, errorsMap));
        }

        SysAdmin sysAdmin = null;
        try {
            sysAdmin = userService.registerSysAdmin(registrationDTO);
        } catch (UserNotUniqueException notUniqueException) {
            errorsMap.put("emailNotUnique", notUniqueException.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(generateUniformResponse(ERRORS_FIELD_NAME, errorsMap));
        }
        return ResponseEntity.ok().body(generateUniformResponse(DATA_FIELD_NAME, extractUserDataToMap(sysAdmin)));
    }

    @PostMapping("/register-dermatologist-account")
    @Secured("ROLE_SYS_ADMIN")
    public ResponseEntity<Map<String, Map<String, String>>> createDermatologist(@RequestBody @Valid RegistrationDTO registrationDTO, BindingResult errors){
        Map<String, String> errorsMap = new HashMap<>();
        if(errors.hasErrors()){
            errorsMap = extractFieldErrorsFromErrors(errors);
            return ResponseEntity.badRequest().body(generateUniformResponse(ERRORS_FIELD_NAME, errorsMap));
        }

        Dermatologist dermatologist = null;
        try{
            dermatologist = userService.registerDermatologist(registrationDTO);
        } catch (UserNotUniqueException notUniqueException) {
            errorsMap.put("emailNotUnique", notUniqueException.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(generateUniformResponse(ERRORS_FIELD_NAME, errorsMap));
        }

        return ResponseEntity.ok().body(generateUniformResponse(DATA_FIELD_NAME, extractUserDataToMap(dermatologist)));
    }


    //Requires login after pass change for now...
    @PostMapping("/change-pass")
    public ResponseEntity<Map<String, Map<String, String>>> changePassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO){
        User user = null;
        Map<String, String> errorsMap = new HashMap<>();
        try {
            user = userService.changePassword(changePasswordDTO);
        } catch (UserNotFoundException e) {
            errorsMap.put("userNotFound", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateUniformResponse(ERRORS_FIELD_NAME, errorsMap));
        } catch (InvalidChangePasswordRequestException e) {
            errorsMap.put("invalidRequest", e.getMessage());
            return ResponseEntity.badRequest().body(generateUniformResponse(ERRORS_FIELD_NAME, errorsMap));
        }

        String jwt = tokenUtils.generateToken(user.getEmail());
        long expiresIn = tokenUtils.getExpiredIn();

        return ResponseEntity.ok().body(generateUniformResponse(DATA_FIELD_NAME, extractUserTokenStateToMap(new UserTokenState(jwt, expiresIn, user.getUserRole()))));

    }

    private Map<String, String> changePasswordData(){
        Map<String, String> changePassMap = new HashMap<>();
        changePassMap.put("changePassRequired", Boolean.TRUE.toString());
        changePassMap.put("changePassAbsoluteURL", ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/auth/change-pass");
        return changePassMap;
    }

    private Map<String, String> generateTokenValidResponse(){
        Map<String, String> response_map = new HashMap<>();
        response_map.put("isTokenValid", Boolean.TRUE.toString());
        return response_map;
    }

    private Map<String, String> generateTokenExpiredResponse(){
        Map<String, String> error_map = new HashMap<>();
        error_map.put("tokenExpired", "A token has expired, please login again.");
        return error_map;
    }

    private boolean isTokenExpired(String token){
        if(token == null) {
            return true;
        }
        Date tokenExpiryDate = tokenUtils.getExpirationDateFromToken(token);
        return tokenExpiryDate.getTime() <= (new Date()).getTime();
    }

    private boolean isUserAuthenticated(Authentication authentication){
        return (authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated());
    }


    private boolean isVerificationTokenExpired(VerificationToken verificationToken){
        Calendar calendar = Calendar.getInstance();
        return ( (verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0);
    }

    private Map<String, Map<String, String>> generateUniformResponse(String responseType ,Map<String, String> messagesMap){
        Map<String, Map<String, String>> data = new HashMap<>();
        data.put(responseType, messagesMap);
        return data;
    }

    //At this point, not sure if we will need this data right after registration.
    private Map<String, String> extractUserDataToMap(User user){
        Map<String, String> data = new HashMap<>();
        data.put("firstName", user.getFirstName());
        data.put("lastName", user.getLastName());
        data.put("telephone", user.getTelephone());
        data.put("email", user.getEmail());
        data.put("gender", user.getGender().toString());
        data.put("password", user.getPassword());
        data.put("address_name", user.getAddress().getAddressLine());
        data.put("address_latitude", user.getAddress().getLatitude().toString());
        data.put("address_longitude", user.getAddress().getLongitude().toString());
        data.put("city", user.getAddress().getCity().getCityName());
        return data;
    }

    private Map<String, String> extractUserTokenStateToMap(UserTokenState userTokenState){
        Map<String, String> data = new HashMap<>();
        data.put("token", userTokenState.getToken());
        data.put("expiresIn", userTokenState.getExpiresIn().toString());
        data.put("role", userTokenState.getRole().getRoleName());
        return data;
    }

    private Map<String, String> extractFieldErrorsFromErrors(Errors errorsList){
        Map<String, String> errors = new HashMap<>();
        errorsList.getAllErrors().forEach( error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<Map<String, Map<String,String>>> handleException() {
        Map<String, String> error_map = new HashMap<>();
        error_map.put("notActivated", "Your account has not been activated yet.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(generateUniformResponse(ERRORS_FIELD_NAME, error_map));
    }
}
