package uberbackend.uberprojectauthservice.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uberbackend.uberprojectauthservice.dtos.PassengerDto;
import uberbackend.uberprojectauthservice.dtos.PassengerSignupRequestDto;
import uberbackend.uberprojectauthservice.models.Passenger;
import uberbackend.uberprojectauthservice.repositories.PassengerRepository;
@Service
public class AuthService {
    private PassengerRepository passengerRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public AuthService(PassengerRepository passengerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.passengerRepository = passengerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public PassengerDto signUpPassenger(PassengerSignupRequestDto passengerSignupRequestDto) {
        Passenger passenger = Passenger.builder()
                .email(passengerSignupRequestDto.getEmail())
                .password(bCryptPasswordEncoder.encode(passengerSignupRequestDto.getPassword()))
                .PassengerName(passengerSignupRequestDto.getName())
                .mobileNumber(passengerSignupRequestDto.getMobileNumber())
                .build();

        Passenger newPassenger = passengerRepository.save(passenger);
        PassengerDto response = PassengerDto.fromPassenger(newPassenger);
        return response;
    }
}
