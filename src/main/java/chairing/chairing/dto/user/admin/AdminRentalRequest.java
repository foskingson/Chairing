package chairing.chairing.dto.user.admin;

import chairing.chairing.domain.rental.RentalStatus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class AdminRentalRequest {    //관리자가 대여 요청을 승인하거나 거부할 때 사용할 DTO

    private Long rentalId;  // 대여 요청 ID
    private RentalStatus status;  // 관리자가 설정하는 대여 상태 (승인/거부)
    private String adminComment;  // 관리자의 코멘트 (optional)
}
