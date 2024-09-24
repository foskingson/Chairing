package chairing.chairing.repository.sensor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chairing.chairing.domain.sensor.Sensor;


@Repository
public interface SensorRepository extends JpaRepository<Sensor,Long> {
    
}
