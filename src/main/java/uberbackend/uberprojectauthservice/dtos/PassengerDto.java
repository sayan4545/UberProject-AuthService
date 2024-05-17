package uberbackend.uberprojectauthservice.dtos;

import lombok.*;

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
}
