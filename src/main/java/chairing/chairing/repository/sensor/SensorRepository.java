package chairing.chairing.repository.sensor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SensorRepository extends JpaRepository<Sensor,Long> {
    
}
