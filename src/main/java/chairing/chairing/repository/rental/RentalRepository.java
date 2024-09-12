package chairing.chairing.repository.rental;

import org.springframework.stereotype.Repository;

import chairing.chairing.domain.rental.Rental;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
}