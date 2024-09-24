package chairing.chairing.dto.user.admin;

import chairing.chairing.domain.wheelchair.WheelchairStatus;
import chairing.chairing.repository.wheelchair.WheelchairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final WheelchairRepository wheelchairRepository;

    // 휠체어 상태 조회
    public Map<WheelchairStatus, Long> getWheelchairStatus() {
        Map<WheelchairStatus, Long> statusCounts = new EnumMap<>(WheelchairStatus.class);
        statusCounts.put(WheelchairStatus.AVAILABLE, wheelchairRepository.countByStatus(WheelchairStatus.AVAILABLE));
        statusCounts.put(WheelchairStatus.RENTED, wheelchairRepository.countByStatus(WheelchairStatus.RENTED));

        return statusCounts;
    }
}
