package uberbackend.uberprojectauthservice.dtos;

import lombok.*;
import uberbackend.uberprojectauthservice.models.Passenger;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {
    private String id;
    private String name;
    private String email;
    private String mobileNumber;
    private String password;//Encrypted one
    private Date createdAt;

    public static PassengerDto fromPassenger(Passenger passenger) {
        PassengerDto result = PassengerDto.builder()
                .id((passenger.getId().toString()))
                .password((passenger.getPassword()))
                .createdAt(passenger.getCreatedAt())
                .email(passenger.getEmail())
                .mobileNumber(passenger.getMobileNumber())
                .name(passenger.getPassengerName())
                .build();

        return result;
    }
}
