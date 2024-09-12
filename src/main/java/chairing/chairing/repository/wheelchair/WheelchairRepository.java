package chairing.chairing.repository.wheelchair;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import chairing.chairing.domain.wheelchair.Wheelchair;


@Repository
public interface WheelchairRepository extends JpaRepository<Wheelchair, Long> {
}