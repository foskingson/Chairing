package chairing.chairing.repository.delivery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chairing.chairing.domain.delivery.Delivery;


@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long>{
    
}
