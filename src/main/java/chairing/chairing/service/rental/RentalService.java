package chairing.chairing.service.rental;

import org.springframework.stereotype.Service;

import chairing.chairing.domain.rental.Rental;
import chairing.chairing.domain.user.User;
import chairing.chairing.repository.rental.RentalRepository;
import chairing.chairing.repository.user.UserRepository;
import chairing.chairing.repository.wheelchair.WheelchairRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RentalService {
//
//
//    private final UserRepository userRepository;
//
//    private final WheelchairRepository wheelchairRepository;
//
//    private final RentalRepository rentalRepository;
//
//    public Rental rentWheelchair(Long userId, Long wheelchairId) {
//        User user = userRepository.findById(userId)
//            .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
//
//        Wheelchair wheelchair = wheelchairRepository.findById(wheelchairId)
//            .orElseThrow(() -> new IllegalArgumentException("휠체어를 찾을 수 없습니다."));
//
//        if (wheelchair.getStatus() != WheelchairStatus.AVAILABLE) {
//            throw new IllegalStateException("휠체어가 대여 중이거나 점검 중입니다.");
//        }
//
//        Rental rental = new Rental();
//        rental.setUser(user);
//        rental.setWheelchair(wheelchair);
//        rental.setRentalDate(LocalDateTime.now());
//        rental.setStatus(RentalStatus.ACTIVE);
//        rental.setRentalCode(generateRentalCode());
//
//        rentalRepository.save(rental);
//
//        wheelchair.setStatus(WheelchairStatus.RENTED);
//        wheelchairRepository.save(wheelchair);
//
//        return rental;
//    }
//
//    private String generateRentalCode() {
//        return UUID.randomUUID().toString();
//    }
}
