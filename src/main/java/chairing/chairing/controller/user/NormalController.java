package chairing.chairing.controller.user;

import java.security.Principal;
import java.time.LocalDateTime;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import chairing.chairing.domain.rental.Rental;
import chairing.chairing.domain.wheelchair.WheelchairType;
import chairing.chairing.service.rental.RentalService;
import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;

@RequiredArgsConstructor
@Secured("NORMAL")
@RequestMapping("/normal")
@Controller
public class NormalController {

    private final RentalService rentalService;



    @GetMapping
    public String normalPage(Model model) {
        // 필요한 데이터를 model에 담아서 뷰로 전달
        model.addAttribute("message", "환영합니다! 일반 사용자 페이지입니다.");
        return "normal";  // normal.html로 리턴 (템플릿 엔진에서 뷰를 렌더링)
    }

    // 대여반납 로직 시작
    @GetMapping("/rent")
    public String showRentForm(Model model) {
        model.addAttribute("wheelchairTypes", WheelchairType.values());
        return "rent";
    }

    @PostMapping("/rent")
    public String rentWheelchair(
            Principal principal,
            @RequestParam("wheelchairType") WheelchairType wheelchairType,
            @RequestParam("returnDate") String returnDate,
            Model model
    ) {
        LocalDateTime returnDateTime = LocalDateTime.parse(returnDate);
        Rental rental = rentalService.rentWheelchair(principal, wheelchairType, returnDateTime);
        model.addAttribute("rental", rental);
        return "rentalConfirmation";
    }

    @GetMapping("/return")
    public String showReturnForm() {
        return "return";
    }

    @PostMapping("/return")
    public String returnWheelchair(
            Principal principal,
            @RequestParam("rentalId") Long rentalId,
            Model model
    ) {
        Rental rental = rentalService.returnWheelchair(principal, rentalId);
        model.addAttribute("rental", rental);
        return "returnConfirmation";
    }
}
