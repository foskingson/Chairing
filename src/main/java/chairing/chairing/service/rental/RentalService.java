package chairing.chairing.service.rental;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chairing.chairing.domain.rental.Rental;
import chairing.chairing.domain.rental.RentalStatus;
import chairing.chairing.domain.user.User;
import chairing.chairing.domain.wheelchair.Wheelchair;
import chairing.chairing.domain.wheelchair.WheelchairStatus;
import chairing.chairing.domain.wheelchair.WheelchairType;
import chairing.chairing.repository.rental.RentalRepository;
import chairing.chairing.repository.user.UserRepository;
import chairing.chairing.repository.wheelchair.WheelchairRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final WheelchairRepository wheelchairRepository;
    private final UserRepository userRepository;

    // 대여 코드 생성 (UUID 대신 타임스탬프 기반 코드)
    private String generateRentalCode() {
        return String.valueOf(System.currentTimeMillis());
    }

    // 휠체어 대여 로직
    @Transactional
    public Rental rentWheelchair(Principal principal, WheelchairType wheelchairType, LocalDateTime returnDate) {
        // 로그인한 유저 정보 가져오기
        String username = principal.getName();
        User user = userRepository.findByusername(username)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        // 선택한 타입의 대여 가능한 휠체어 중 하나 가져오기
        Wheelchair wheelchair = wheelchairRepository.findFirstByTypeAndStatus(wheelchairType, WheelchairStatus.AVAILABLE)
                .orElseThrow(() -> new IllegalArgumentException("대여 가능한 휠체어가 없습니다."));

        // 대여 정보 생성 및 저장 (생성된 고유 대여 코드 사용)
        Rental rental = new Rental(user, wheelchair, LocalDateTime.now(), returnDate, RentalStatus.ACTIVE);

        // 휠체어 상태 변경
        wheelchair.changeStatus(WheelchairStatus.RENTED);
        wheelchairRepository.save(wheelchair);

        return rentalRepository.save(rental);
    }

    @Transactional
    public Rental returnWheelchair(Principal principal, Long rentalId) {
        // 로그인한 유저 정보 가져오기
        String username = principal.getName();
        User user = userRepository.findByusername(username)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        // 대여 정보 가져오기
        Rental rental = rentalRepository.findByRentalIdAndUser(rentalId, user)
                .orElseThrow(() -> new IllegalArgumentException("대여 기록을 찾을 수 없습니다."));

        // 이미 반납된 경우 예외 처리
        if (rental.getStatus() == RentalStatus.RETURNED) {
            throw new IllegalStateException("이미 반납된 대여입니다.");
        }

        // 대여된 휠체어 상태를 "AVAILABLE"로 변경
        Wheelchair wheelchair = rental.getWheelchair();
        wheelchair.changeStatus(WheelchairStatus.AVAILABLE);
        wheelchairRepository.save(wheelchair);

        // 대여 상태를 "RETURNED"로 변경
        rental.changeStatus(RentalStatus.RETURNED);
        return rentalRepository.save(rental);
    }

}
