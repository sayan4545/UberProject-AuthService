package uberbackend.uberprojectauthservice.controllers;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uberbackend.uberprojectauthservice.dtos.AuthRequestDto;
import uberbackend.uberprojectauthservice.dtos.PassengerDto;
import uberbackend.uberprojectauthservice.dtos.PassengerSignupRequestDto;
import uberbackend.uberprojectauthservice.services.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private AuthService authService;
    public AuthController(AuthService authService, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/signUp/passenger")

    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto){
        PassengerDto response =authService.signUpPassenger(passengerSignupRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/signIn")

    public ResponseEntity<?> signIn(@RequestBody AuthRequestDto authRequestDto){
        System.out.println("Request recieved" + authRequestDto.getEmail()+" "+ authRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword()));
        if(authentication.isAuthenticated()){
            return new ResponseEntity<>("Authentication Successful", HttpStatus.OK);
        }
        return new ResponseEntity<>("Auth failed", HttpStatus.OK);
    }
}
