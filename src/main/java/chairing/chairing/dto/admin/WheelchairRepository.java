package chairing.repository;

import chairing.domain.wheelchair.Wheelchair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WheelchairRepository extends JpaRepository<Wheelchair, Long> {
    // 기본적인 CRUD 메서드 제공
}
