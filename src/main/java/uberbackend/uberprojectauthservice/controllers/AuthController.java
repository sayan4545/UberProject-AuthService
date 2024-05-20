package uberbackend.uberprojectauthservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uberbackend.uberprojectauthservice.dtos.PassengerDto;
import uberbackend.uberprojectauthservice.dtos.PassengerSignupRequestDto;
import uberbackend.uberprojectauthservice.services.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/signUp/passenger")

    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto){
        PassengerDto response =authService.signUpPassenger(passengerSignupRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
