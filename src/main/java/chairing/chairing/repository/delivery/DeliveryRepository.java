package chairing.chairing.repository.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long>{
    Optional<Delivery> findByRental(Rental rental);
}
