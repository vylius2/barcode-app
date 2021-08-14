package com.barcodegenerator.barcodegenerator.api.controller;

import com.barcodegenerator.barcodegenerator.api.dto.request.AuthenticationRequest;
import com.barcodegenerator.barcodegenerator.api.dto.response.AuthenticationResponse;
import com.barcodegenerator.barcodegenerator.service.JwtService;
import com.barcodegenerator.barcodegenerator.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class MainController {

    private AuthenticationManager authenticationManager;

    private UserService userService;

    private JwtService jwtService;

    public MainController(AuthenticationManager authenticationManager, UserService userService, JwtService jwtService){
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        System.out.println("AAAAAAAAAAAAAAAAAAA");
        try {
            new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        return ResponseEntity.ok(new AuthenticationResponse(
                jwtService.generateToken(
                        userService.loadUserByUsername(
                                authenticationRequest.getUsername()))));
    }
}
