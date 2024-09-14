package chairing.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WheelchairStatusDTO {  //휠체어의 전반적인 상태를 관리자가 확인할 수 있도록 하는 DTO

    private int total;      // 휠체어 총 개수
    private int available;  // 사용 가능한 휠체어 개수
    private int broken;     // 고장난 휠체어 개수
    private int rented;     // 대여 중인 휠체어 개수
}
