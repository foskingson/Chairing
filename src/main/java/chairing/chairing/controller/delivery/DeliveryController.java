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

        try {
            deliveryService.createOrUpdateCourier(rentalId, status, deliveryAddress);
            return ResponseEntity.ok("Courier status for rental ID " + rentalId + " updated to " + status + " at address: " + deliveryAddress);
        } catch (Exception e) {
            // 예외 발생 시 오류 메시지 반환
            return ResponseEntity.status(500).body("Failed to update courier status: " + e.getMessage());
        }
    }
}
