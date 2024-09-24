package chairing.chairing.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
<<<<<<< HEAD
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
=======
import lombok.Setter;

@Entity
@Getter
@Setter // 테스트 용도
>>>>>>> jo
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;

    @Column(nullable = true)
    private String address;

    private String guardianCode = "0";

    public User(String username, String password, String phoneNumber, UserRole role, String guardianCode, String address) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.guardianCode = guardianCode;
        this.address = address;
    }
}
