package chairing.chairing.repository.rental;

import org.springframework.stereotype.Repository;

import chairing.chairing.domain.rental.Rental;
import chairing.chairing.domain.user.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    Optional<Rental> findByRentalIdAndUser(Long rentalId, User user);
}