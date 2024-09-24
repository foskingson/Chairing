package chairing.chairing.service.user;

import chairing.chairing.domain.rental.Rental;
import chairing.chairing.domain.wheelchair.Wheelchair;
import chairing.chairing.repository.rental.RentalRepository;
import chairing.chairing.repository.wheelchair.WheelchairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private WheelchairRepository wheelchairRepository;

    // 대여 요청 승인
    public void approveRental(Long rentalId) {
        Optional<Rental> rentalOpt = rentalRepository.findById(rentalId);
        if (rentalOpt.isPresent()) {
            Rental rental = rentalOpt.get();
            rental.setStatus("APPROVED");  // 대여 승인
            rentalRepository.save(rental);

            // 휠체어 상태를 대여 중으로 변경
            Wheelchair wheelchair = rental.getWheelchair();
            wheelchair.setStatus("RENTED");
            wheelchairRepository.save(wheelchair);
        } else {
            throw new RuntimeException("Rental not found");
        }
    }

    // 대여 요청 거절
    public void rejectRental(Long rentalId) {
        Optional<Rental> rentalOpt = rentalRepository.findById(rentalId);
        if (rentalOpt.isPresent()) {
            Rental rental = rentalOpt.get();
            rental.setStatus("REJECTED");  // 대여 거절
            rentalRepository.save(rental);
        } else {
            throw new RuntimeException("Rental not found");
        }
    }

    // 휠체어 상태 조회
    public Map<String, Long> getWheelchairStatusCounts() {
        Map<String, Long> statusCounts = new HashMap<>();
        statusCounts.put("AVAILABLE", wheelchairRepository.countByStatus("AVAILABLE"));
        statusCounts.put("RENTED", wheelchairRepository.countByStatus("RENTED"));
        statusCounts.put("DAMAGED", wheelchairRepository.countByStatus("DAMAGED"));
        return statusCounts;
    }

    // 관리자가 전체 대여 목록을 조회
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }
}
