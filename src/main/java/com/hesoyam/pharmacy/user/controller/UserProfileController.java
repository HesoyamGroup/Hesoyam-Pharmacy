package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.location.model.Address;
import com.hesoyam.pharmacy.location.model.Country;
import com.hesoyam.pharmacy.location.service.ICityService;
import com.hesoyam.pharmacy.location.service.ICountryService;
import com.hesoyam.pharmacy.security.TokenUtils;
import com.hesoyam.pharmacy.user.DTO.AddressDTO;
import com.hesoyam.pharmacy.user.exceptions.UserNotFoundException;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.IUserService;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value="/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserProfileController {

    private static final String ERRORS_FIELD_NAME = "errors";
    private static final String DATA_FIELD_NAME = "data";

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

        System.out.println("--------------------------------------------");
        System.out.println(newAddress.getAddressLine());
        System.out.println(newAddress.getCity().getCityName());
        System.out.println("--------------------------------------------");
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
}
