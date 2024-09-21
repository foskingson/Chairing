package chairing.chairing.service.delivery;

import chairing.chairing.domain.delivery.Delivery;
import chairing.chairing.domain.delivery.DeliveryStatus;
import chairing.chairing.domain.rental.Rental;
import chairing.chairing.dto.delivery.DeliveryRequest;
import chairing.chairing.repository.delivery.DeliveryRepository;
import chairing.chairing.repository.rental.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;  // DeliveryRepository 사용
    private final RentalRepository rentalRepository;
    private final RestTemplate restTemplate;  // RestTemplate를 사용하여 외부 API 호출

    // 대여 신청을 택배사로 전달하고 배송 상태를 업데이트
    @Transactional
    public void createOrUpdateCourier(Long rentalId, DeliveryStatus status, String deliveryAddress) {
        // rentalId로 대여 신청을 찾음
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("Rental not found"));

        // rental과 관련된 Delivery 정보를 찾거나 새로 생성
        Delivery delivery = deliveryRepository.findByRental(rental)
                .orElse(new Delivery(rental, status, deliveryAddress));  // 새 Delivery 객체 생성

        // 배송 상태 업데이트
        delivery.updateStatus(status);

        // Delivery 정보 저장
        deliveryRepository.save(delivery);

        // 외부 API 호출을 통한 택배사와의 연동
        String courierApiUrl = "http://courier-service.com/api/deliveries";
        DeliveryRequest courierRequest = new DeliveryRequest(rentalId, deliveryAddress, status);

        try {
            restTemplate.postForEntity(courierApiUrl, courierRequest, String.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to communicate with courier service", e);
        }
    }
}
