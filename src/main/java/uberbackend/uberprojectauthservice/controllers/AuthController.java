package uberbackend.uberprojectauthservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uberbackend.uberprojectauthservice.dtos.PassengerDto;
import uberbackend.uberprojectauthservice.dtos.PassengerSignupRequestDto;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @PostMapping("/signUp/passenger")

    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto){


        return null;
    }
}
