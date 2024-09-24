package chairing.chairing.controller.user;

import chairing.chairing.service.user.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 대여 승인
    @PostMapping("/admin/reservation/approve/{rentalId}")
    public ResponseEntity<String> approveRental(@PathVariable Long rentalId) {
        adminService.approveRental(rentalId);
        return ResponseEntity.ok("Rental approved successfully.");
    }

    // 대여 거절
    @PostMapping("/admin/reservation/reject/{rentalId}")
    public ResponseEntity<String> rejectRental(@PathVariable Long rentalId) {
        adminService.rejectRental(rentalId);
        return ResponseEntity.ok("Rental rejected successfully.");
    }

    // 휠체어 상태 통계 조회
    @GetMapping("/admin/rentals")
    public ResponseEntity<Map<String, Long>> getWheelchairStatusCounts() {
        Map<String, Long> statusCounts = adminService.getWheelchairStatusCounts();
        return ResponseEntity.ok(statusCounts);
    }
}
