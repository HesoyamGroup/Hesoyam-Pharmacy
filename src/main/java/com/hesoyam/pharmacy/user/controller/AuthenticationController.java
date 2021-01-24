package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.security.TokenUtils;
import com.hesoyam.pharmacy.user.DTO.LoginDTO;
import com.hesoyam.pharmacy.user.DTO.UserTokenState;
import com.hesoyam.pharmacy.user.exceptions.RegistrationUserNotUniqueException;
import com.hesoyam.pharmacy.user.exceptions.RegistrationValidationException;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import com.hesoyam.pharmacy.user.DTO.RegistrationDTO;

import javax.validation.Valid;
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
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Map<String, String>>> register(@RequestBody @Valid RegistrationDTO registrationDTO, BindingResult errors){
        Map<String, String> errorsMap = new HashMap<>();
        if(errors.hasErrors()){
            errorsMap = extractFieldErrorsFromErrors(errors);
            return ResponseEntity.badRequest().body(generateUniformResponse(ERRORS_FIELD_NAME, errorsMap));
        }

        User user;
        try {
            user = userService.save(registrationDTO);
        } catch (RegistrationValidationException e) {
            return ResponseEntity.badRequest().body(generateUniformResponse(ERRORS_FIELD_NAME, e.getErrorMessagesMap()));
        }catch (RegistrationUserNotUniqueException notUniqueException){
            errorsMap.put("emailNotUnique", notUniqueException.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(generateUniformResponse(ERRORS_FIELD_NAME, errorsMap));
        }
        return ResponseEntity.ok().body(generateUniformResponse(DATA_FIELD_NAME, extractUserDataToMap(user)));
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
        String jwt = tokenUtils.generateToken(user.getEmail());
        long expiresIn = tokenUtils.getExpiredIn();
        return ResponseEntity.ok().body(generateUniformResponse(DATA_FIELD_NAME, extractUserTokenStateToMap(new UserTokenState(jwt, expiresIn))));
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

}
