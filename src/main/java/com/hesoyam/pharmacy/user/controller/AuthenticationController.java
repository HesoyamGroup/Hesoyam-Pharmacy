package com.hesoyam.pharmacy.user.controller;

import com.hesoyam.pharmacy.security.TokenUtils;
import com.hesoyam.pharmacy.user.DTO.LoginDTO;
import com.hesoyam.pharmacy.user.DTO.UserTokenState;
import com.hesoyam.pharmacy.user.model.User;
import com.hesoyam.pharmacy.user.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.hesoyam.pharmacy.user.DTO.RegistrationDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//Controller used for user authentication
@RestController
@RequestMapping(value="/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegistrationDTO registrationDTO){
        User user = userService.save(registrationDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UserTokenState> login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        //Save logged user in security context
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //Create token for user
        User user = (User) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getEmail());
        long expiresIn = tokenUtils.getExpiredIn();
        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }


    //Method used for debug purposes, at least for now.
    @GetMapping("isLogged")
    public ResponseEntity<String> isLogged(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String refreshedToken = null;
        if(authentication != null){
            refreshedToken = tokenUtils.refreshToken(tokenUtils.getToken(request));
        }
        return ResponseEntity.ok("Refreshed token: " + refreshedToken +  (authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()));

    }

}
