package chairing.chairing.dto.user.admin;

import chairing.chairing.domain.wheelchair.WheelchairStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AdminWheelchair {
    private Long wheelchairId;  // 휠체어 ID
    private WheelchairStatus status;  // 휠체어 상태 (사용 가능, 고장 등)
}
