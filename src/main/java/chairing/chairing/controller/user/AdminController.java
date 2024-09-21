package chairing.chairing.controller.user;

import chairing.chairing.domain.wheelchair.WheelchairStatus;
import chairing.chairing.service.user.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // 대여 요청 승인
    @PostMapping("/rental/approve/{rentalId}")
    public ResponseEntity<String> approveRentalRequest(@PathVariable Long rentalId) {
        adminService.approveRentalRequest(rentalId);
        return ResponseEntity.ok("Rental request approved successfully");
    }

    // 대여 요청 거절
    @PostMapping("/rental/reject/{rentalId}")
    public ResponseEntity<String> rejectRentalRequest(@PathVariable Long rentalId) {
        adminService.rejectRentalRequest(rentalId);
        return ResponseEntity.ok("Rental request rejected successfully");
    }
}
