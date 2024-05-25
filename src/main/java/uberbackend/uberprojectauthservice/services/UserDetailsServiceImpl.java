package uberbackend.uberprojectauthservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uberbackend.uberprojectauthservice.helpers.AuthPassengerDetails;
import uberbackend.uberprojectauthservice.models.Passenger;
import uberbackend.uberprojectauthservice.repositories.PassengerRepository;

import java.util.Optional;

/**
 * This class is responsible for loading the user in form of userDetails object for auth
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private  PassengerRepository passengerRepository;

//    public UserDetailsServiceImpl(PassengerRepository passengerRepository) {
//        this.passengerRepository = passengerRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Passenger> passenger = passengerRepository.findPassengerByEmail(username);// username since email is the unique identifier
        if (passenger.isPresent()) {
            return new AuthPassengerDetails(passenger.get());
        }else{
            throw new UsernameNotFoundException("Unable to find the user by the given username");
        }
    }
}
