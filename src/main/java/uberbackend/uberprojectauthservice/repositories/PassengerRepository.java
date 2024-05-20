package uberbackend.uberprojectauthservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uberbackend.uberprojectauthservice.models.Passenger;
@Repository
public interface PassengerRepository extends JpaRepository<Passenger,Long> {

}
