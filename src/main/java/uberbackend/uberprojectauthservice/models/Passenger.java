package uberbackend.uberprojectauthservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Passenger extends BaseModel{
    @Column(nullable = false)
    private String PassengerName;
    @Column(nullable = false)
    private String mobileNumber;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "passenger")
    private List<Booking> passengerBookings = new ArrayList<>();

}
