package chairing.chairing.controller.delivery;

import chairing.chairing.domain.delivery.DeliveryStatus;
import chairing.chairing.service.delivery.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;  // 의존성 주입된 서비스 사용

    // 배송 상태 업데이트
    @PostMapping("/update-status/{rentalId}")
    public ResponseEntity<String> updateCourierStatus(
            @PathVariable Long rentalId,
            @RequestBody DeliveryStatus status,
            @RequestParam String deliveryAddress) {  // deliveryAddress 추가

        // 비정적 메서드 호출 (주입된 인스턴스 사용)
        deliveryService.createOrUpdateCourier(rentalId, status, deliveryAddress);
        return ResponseEntity.ok("Courier status updated successfully");
    }
}
