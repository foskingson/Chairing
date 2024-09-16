package chairing.chairing.service.delivery;

import chairing.chairing.domain.delivery.Delivery;
import chairing.chairing.service.delivery.DeliveryService;
import chairing.chairing.domain.delivery.DeliveryStatus;
import chairing.chairing.domain.rental.Rental;
import chairing.chairing.repository.delivery.DeliveryRepository;
import chairing.chairing.repository.rental.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final RentalRepository rentalRepository;

    // 대여 신청을 택배사로 전달하고 배송 상태를 업데이트
    @Transactional
    public void createOrUpdateCourier(Long rentalId, DeliveryStatus status) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("Rental not found"));

        Delivery courier = DeliveryRepository.findByRental(rental)
                .orElse(new Delivery(rental, status));

        courier.updateStatus(status);  // 배송 상태 업데이트
        courierRepository.save(courier);  // 택배 정보 저장
    }
}
