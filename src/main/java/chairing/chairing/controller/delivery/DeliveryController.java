package chairing.chairing.controller.delivery;

import chairing.chairing.domain.delivery.DeliveryStatus;
import chairing.chairing.service.delivery.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courier")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    // 배송 상태 업데이트
    @PostMapping("/update-status/{rentalId}")
    public ResponseEntity<String> updateCourierStatus(
            @PathVariable Long rentalId,
            @RequestBody DeliveryStatus status) {

        DeliveryService.createOrUpdateCourier(rentalId, status);
        return ResponseEntity.ok("Courier status updated successfully");
    }
}
