package chairing.chairing.repository.rental;

import org.springframework.stereotype.Repository;

import chairing.chairing.domain.rental.Rental;
import chairing.chairing.domain.user.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

    Optional<Rental> findByRentalIdAndUser(Long rentalId, User user);

    @Query("SELECT r FROM Rental r WHERE r.user = ?1 AND r.status = chairing.chairing.domain.rental.RentalStatus.ACTIVE")
    Optional<Rental> findCurrentRentalByUser(User user);

    // 현재 대여 중인 기록을 가져오는 메서드
    List<Rental> findByUser(User user);
}