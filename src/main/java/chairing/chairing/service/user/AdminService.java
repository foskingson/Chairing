package chairing.chairing.service.user;

import chairing.chairing.domain.delivery.DeliveryStatus;
import chairing.chairing.domain.rental.Rental;
import chairing.chairing.domain.rental.RentalStatus;
import chairing.chairing.repository.rental.RentalRepository;
import chairing.chairing.service.delivery.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final RentalRepository rentalRepository;
    private final DeliveryService deliveryService;

    // 대여 요청 승인 처리 및 택배사로 전달
    @Transactional
    public void approveRentalRequest(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("Rental not found"));

        rental.changeStatus(RentalStatus.APPROVED);  // 상태 변경 메서드 호출
        rentalRepository.save(rental);

        // 택배사로 대여 신청 전달 (배송 준비 상태로 설정)
        String deliveryAddress = rental.getUser().getAddress();  // 사용자 주소 가져오기
        deliveryService.createOrUpdateCourier(rentalId, DeliveryStatus.PENDING, deliveryAddress);
    }

    // 대여 요청 거절 처리 (택배사에 전달되지 않음)
    @Transactional
    public void rejectRentalRequest(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("Rental not found"));

        rental.changeStatus(RentalStatus.REJECTED);  // 상태 변경 메서드 호출
        rentalRepository.save(rental);
    }
}
