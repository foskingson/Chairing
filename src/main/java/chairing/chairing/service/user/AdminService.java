package chairing.chairing.service.user;

import chairing.chairing.domain.delivery.DeliveryStatus;
import chairing.chairing.domain.rental.Rental;
import chairing.chairing.domain.rental.RentalStatus;
import chairing.chairing.domain.wheelchair.Wheelchair;
import chairing.chairing.domain.wheelchair.WheelchairStatus;
import chairing.chairing.repository.wheelchair.WheelchairRepository;
import chairing.chairing.service.delivery.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final WheelchairRepository wheelchairRepository;

    // 대여 요청 승인 처리 및 택배사로 전달
    @Transactional
    public void approveRentalRequest(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("Rental not found"));

        rental.setStatus(RentalStatus.APPROVED);  // 대여 승인
        rentalRepository.save(rental);

        // 택배사로 대여 신청 전달 (배송 준비 상태로 설정)
        DeliveryService.createOrUpdateCourier(rentalId, DeliveryStatus.PENDING);
    }

    // 대여 요청 거절 처리 (택배사에 전달되지 않음)
    @Transactional
    public void rejectRentalRequest(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("Rental not found"));

        rental.setStatus(RentalStatus.REJECTED);  // 대여 거절
        rentalRepository.save(rental);
    }
}
