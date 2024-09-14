package chairing.chairing.service.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import chairing.chairing.domain.user.User;
import chairing.chairing.dto.user.UserCreateRequest;
import chairing.chairing.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;

// 공통 로직 회원가입 같은 것들 모음

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
        
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(UserCreateRequest request) {
        User user = new User(
            request.getUsername(),
            passwordEncoder.encode(request.getPassword()),
            request.getPhoneNumber(),
            request.getRole(),
            request.getGuardianCode()
        );

        this.userRepository.save(user);
        return user;
    }
    
}
