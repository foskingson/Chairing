package chairing.chairing.dto.user;

import chairing.chairing.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateRequest {
    private String username;
    private String password;
    private String phoneNumber;
    private UserRole role;
    private String guardianCode;
}
