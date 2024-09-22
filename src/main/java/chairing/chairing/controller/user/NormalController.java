package chairing.chairing.controller.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import chairing.chairing.domain.rental.Rental;
import chairing.chairing.domain.wheelchair.WheelchairType;
import chairing.chairing.service.rental.RentalService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@Secured("NORMAL")
@RequestMapping("/normal")
public class NormalController {

    private final RentalService rentalService;

    // 일반 사용자 페이지 환영 메시지
    @GetMapping
    public ResponseEntity<String> normalPage() {
        return ResponseEntity.ok("Welcome to the normal user page");
    }

    // 대여 폼 보여주기 (휠체어 종류 반환)
    @GetMapping("/rent")
    public ResponseEntity<WheelchairType[]> showRentForm() {
        return ResponseEntity.ok(WheelchairType.values());
    }

    // 휠체어 대여 처리
    @PostMapping("/rent")
    public ResponseEntity<Rental> rentWheelchair(
            Principal principal,
            @RequestParam("wheelchairType") WheelchairType wheelchairType,
            @RequestParam("returnDate") String returnDate
    ) {
        LocalDateTime returnDateTime = LocalDateTime.parse(returnDate);
        Rental rental = rentalService.rentWheelchair(principal, wheelchairType, returnDateTime);
        return ResponseEntity.status(HttpStatus.CREATED).body(rental);
    }

    // 휠체어 반납 처리
    @PostMapping("/return")
    public ResponseEntity<Rental> returnWheelchair(
            Principal principal,
            @RequestParam("rentalId") Long rentalId
    ) {
        Rental rental = rentalService.returnWheelchair(principal, rentalId);
        return ResponseEntity.ok(rental);
    }
}
