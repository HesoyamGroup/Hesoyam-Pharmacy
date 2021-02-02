package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.location.model.Address;
import com.hesoyam.pharmacy.location.service.ICityService;
import com.hesoyam.pharmacy.location.service.ICountryService;
import com.hesoyam.pharmacy.security.TokenUtils;
import com.hesoyam.pharmacy.user.dto.AccountInformationDTO;
import com.hesoyam.pharmacy.user.dto.AddressDTO;
import com.hesoyam.pharmacy.user.dto.PasswordDTO;
import com.hesoyam.pharmacy.user.dto.UserBasicInfoDTO;
import com.hesoyam.pharmacy.user.exceptions.UserNotFoundException;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value="/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserProfileController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private IUserService userService;

    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;


    @GetMapping("/user-information")
    public ResponseEntity<User> getProfileInformation(HttpServletRequest request){

        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);

        try{
            User userInformation = userService.findByEmail(username);
            userInformation.setPassword("");
            return ResponseEntity.ok().body(userInformation);
        }
        catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/user-address")
    public ResponseEntity<Address> getAddress(HttpServletRequest request) {

        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);

        try {
            User userInformation = userService.findByEmail(username);
            userInformation.setPassword("");
            userInformation.setAddress(userInformation.getAddress());

            return ResponseEntity.ok().body(userInformation.getAddress());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/change-address")
    public ResponseEntity<String> changeAddress(@RequestBody AddressDTO newAddress, HttpServletRequest request){

        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);

        try{
            User user = userService.findByEmail(username);
            Address address = new Address();
            address.setCity(newAddress.getCity());
            address.setAddressLine(newAddress.getAddressLine());
            address.setLatitude(user.getAddress().getLatitude());
            address.setLongitude(user.getAddress().getLongitude());

            user.setAddress(address);
            userService.update(user);

            return ResponseEntity.ok().body("ok");
        }
        catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("bad");
        }
    }

    @PostMapping("/change-user-basic-info")
    public ResponseEntity<String> chanceBasicInfo(@RequestBody UserBasicInfoDTO newInfo, HttpServletRequest request){

        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);

        try{
            User user = userService.findByEmail(username);
            user.setFirstName(newInfo.getFirstName());
            user.setLastName(newInfo.getLastName());
            user.setGender(newInfo.getGender());
            user.setTelephone(newInfo.getTelephone());

            userService.update(user);

            return ResponseEntity.ok().body("ok");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("bad");
        }
    }

    @PostMapping("/test-password")
    public ResponseEntity<String> testPassword(@RequestBody String password, HttpServletRequest request){
        String username = tokenUtils.getUsernameFromToken(tokenUtils.getToken(request));
        password = getStringFromJSON(password);

        try{
            User user = userService.findByEmail(username);
            if(passwordEncoder.matches(password, user.getPassword())){
                return ResponseEntity.ok().body("matches");
            }
            return ResponseEntity.ok().body("No match");
        }
        catch (UserNotFoundException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("no match");
        }
    }

    private String getStringFromJSON(String text) {
        text = text.substring(text.indexOf(':') + 2);
        text = text.substring(0, text.length() - 2);
        return text;
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordDTO passwordChanges, HttpServletRequest request){

        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);

        try{
            User user = userService.findByEmail(username);

            if(passwordEncoder.matches(passwordChanges.getOldPassword(), user.getPassword()))
            {
                user.setPassword(passwordEncoder.encode(passwordChanges.getPassword()));
                userService.update(user);
                return ResponseEntity.ok().body("password successfully changed");
            }
            else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("wrong old password");
        }
        catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("internal server error");
        }

    }

    @PostMapping("/change-account-information")
    public ResponseEntity<String> changeAccountInformation(@RequestBody AccountInformationDTO accountChanges, HttpServletRequest request){

        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);

        try{
            User user = userService.findByEmail(username);

            if(passwordEncoder.matches(accountChanges.getOldPassword(), user.getPassword()))
            {
                user.setPassword(passwordEncoder.encode(accountChanges.getPassword()));
                user.setEmail(accountChanges.getEmail());
                userService.update(user);
                return ResponseEntity.ok().body("account information successfully changed");
            }
            else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("wrong old password");
        }
        catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("internal server error");
        }

    }

    @PostMapping("/change-email")
    public ResponseEntity<String> changeEmail(@RequestBody String email, HttpServletRequest request){

        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);
        email = getStringFromJSON(email);
        try{
            User user = userService.findByEmail(username);

            user.setEmail(email);
            userService.update(user);
            return ResponseEntity.ok().body("changed email");
        }
        catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("internal server error");
        }

    }

    @GetMapping(value = "/check-role")
    public ResponseEntity<String> checkRole(HttpServletRequest request){
        String token = tokenUtils.getToken(request);
        String username = tokenUtils.getUsernameFromToken(token);
        try{
            User user = userService.findByEmail(username);
            return ResponseEntity.ok().body(user.getRoleEnum().toString());
        }
        catch (UserNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("internal server error");
        }
    }
}
