package uberbackend.uberprojectauthservice.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerSignupRequestDto {
    private String name;
    private String email;
    private String mobileNumber;
    private String password;
}
