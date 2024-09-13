package chairing.chairing.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import chairing.chairing.domain.user.User;
import chairing.chairing.domain.user.UserRole;
import chairing.chairing.dto.user.UserCreateRequest;
import chairing.chairing.repository.user.UserRepository;

@Transactional
@SpringBootTest
public class NormalServiceTest {

    @Autowired
    private NormalService normalService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        UserCreateRequest request = new UserCreateRequest("testUser", "testPassword", "1234567890", UserRole.NORMAL,null);


        User user = normalService.create(request);

        assertEquals("testUser", user.getUsername());
        assertEquals("1234567890", user.getPhoneNumber());
    
    }
}