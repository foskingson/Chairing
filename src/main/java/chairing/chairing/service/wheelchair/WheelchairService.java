package chairing.chairing.service.wheelchair;

import chairing.chairing.domain.wheelchair.Wheelchair;
import chairing.chairing.repository.wheelchair.WheelchairRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WheelchairService {

    @Autowired
    private WheelchairRepository wheelchairRepository;

    public List<Wheelchair> getAllWheelchairs() {
        return wheelchairRepository.findAll();
    }
}