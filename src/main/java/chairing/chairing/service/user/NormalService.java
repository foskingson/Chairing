package chairing.chairing.service.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import chairing.chairing.domain.user.User;
import chairing.chairing.dto.user.UserCreateRequest;
import chairing.chairing.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NormalService {

    private final UserRepository userRepository;

    public User create(UserCreateRequest request) {
        User user = new User(
            request.getUsername(),
            request.getPassword(),
            request.getPhoneNumber(),
            request.getRole(),
            request.getGuardianCode()
        );

        this.userRepository.save(user);
        return user;
    }
    
    public User getUser(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new IllegalArgumentException("없는 유저 입니다.");
        }
    }
}
