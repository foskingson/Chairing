package chairing.chairing;

import chairing.chairing.domain.rental.Rental;
import chairing.chairing.domain.rental.RentalStatus;
import chairing.chairing.domain.user.User;
import chairing.chairing.domain.user.UserRole;
import chairing.chairing.domain.wheelchair.Wheelchair;
import chairing.chairing.domain.wheelchair.WheelchairStatus;
import chairing.chairing.domain.wheelchair.WheelchairType;
import chairing.chairing.repository.rental.RentalRepository;
import chairing.chairing.repository.user.UserRepository;
import chairing.chairing.repository.wheelchair.WheelchairRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
@RequiredArgsConstructor
public class TestDataLoader {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final WheelchairRepository wheelchairRepository;
    @Autowired
    private final RentalRepository rentalRepository;

    @Test
    public void loadTestData() {
        // 테스트 사용자 생성
        User user1 = new User("john_doe", "password", "1234567890",
                UserRole.NORMAL, "0", "123 Main ST, City");
        userRepository.save(user1);

        // 테스트 휠체어 생성
        Wheelchair wheelchair1 = new Wheelchair(WheelchairStatus.AVAILABLE, WheelchairType.ADULT, "Warehouse 1");
        wheelchairRepository.save(wheelchair1);

        // 대여 요청 생성
        Rental rental1 = new Rental(user1, wheelchair1, LocalDateTime.now(), LocalDateTime.now().plusDays(2), RentalStatus.PENDING);
        rentalRepository.save(rental1);

        // 승인된 대여 요청
        Rental rental2 = new Rental(user1, wheelchair1, LocalDateTime.now(), LocalDateTime.now().plusDays(3), RentalStatus.APPROVED);
        rentalRepository.save(rental2);

        // 휠체어 상태 업데이트
        wheelchair1.changeStatus(WheelchairStatus.RENTED);
        wheelchairRepository.save(wheelchair1);

        System.out.println("Test data loaded.");
    }
}
