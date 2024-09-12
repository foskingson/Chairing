package chairing.chairing.dto.user;

import chairing.chairing.domain.user.UserRole;
import lombok.Data;

@Data
public class UserCreateRequest {
    private String username;
    private String phoneNumber;
    private String password;
    private UserRole role;
    private String guardianCode;
}
