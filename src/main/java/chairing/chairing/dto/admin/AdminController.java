package chairing.controller.admin;

import chairing.dto.admin.WheelchairStatusDTO;
import chairing.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {  //관리자가 휠체어의 현황을 확인할 수 있는 API를 제공

    private final AdminService adminService;

    // 휠체어 현황 조회
    @GetMapping("/wheelchair/status")
    public ResponseEntity<WheelchairStatusDTO> getWheelchairStatus() {
        WheelchairStatusDTO statusDTO = adminService.getWheelchairStatus();
        return ResponseEntity.ok(statusDTO);
    }
}
