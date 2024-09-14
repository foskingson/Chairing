package chairing.service;

import chairing.domain.wheelchair.Wheelchair;
import chairing.domain.wheelchair.WheelchairStatus;
import chairing.dto.admin.WheelchairStatusDTO;
import chairing.repository.WheelchairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService { //휠체어 상태 정보를 집계하는 로직을 추가

    private final WheelchairRepository wheelchairRepository;

    // 휠체어 현황 조회
    public WheelchairStatusDTO getWheelchairStatus() {
        List<Wheelchair> wheelchairs = wheelchairRepository.findAll();

        int total = wheelchairs.size();
        int available = (int) wheelchairs.stream()
                .filter(w -> w.getStatus() == WheelchairStatus.AVAILABLE)
                .count();
        int broken = (int) wheelchairs.stream()
                .filter(w -> w.getStatus() == WheelchairStatus.BROKEN)
                .count();
        int rented = (int) wheelchairs.stream()
                .filter(w -> w.getStatus() == WheelchairStatus.RENTED)
                .count();

        return new WheelchairStatusDTO(total, available, broken, rented);
    }
}
