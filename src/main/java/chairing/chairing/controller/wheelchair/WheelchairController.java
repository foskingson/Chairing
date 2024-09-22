package chairing.chairing.controller.wheelchair;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import chairing.chairing.domain.wheelchair.Wheelchair;
import chairing.chairing.repository.wheelchair.WheelchairRepository;
import chairing.chairing.service.wheelchair.WheelchairService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class WheelchairController {

    private final WheelchairService wheelchairService;
    private final WheelchairRepository wheelchairRepository;

    public WheelchairController(WheelchairService wheelchairService, WheelchairRepository wheelchairRepository) {
        this.wheelchairService = wheelchairService;
        this.wheelchairRepository = wheelchairRepository;
    }

    // 휠체어 위치 정보 제공
    @GetMapping("/map")
    public ResponseEntity<?> showMap() {
        Wheelchair wheelchair = wheelchairRepository.findById(1L)
            .orElseThrow(() -> new IllegalArgumentException("Wheelchair not found"));
        
        return ResponseEntity.ok(wheelchair.getLocation());
    }
}