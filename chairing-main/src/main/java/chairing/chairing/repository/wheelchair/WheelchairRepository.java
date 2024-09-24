package chairing.chairing.repository.wheelchair;

import chairing.chairing.domain.wheelchair.Wheelchair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WheelchairRepository extends JpaRepository<Wheelchair, Long> {
    long countByStatus(String status);
}