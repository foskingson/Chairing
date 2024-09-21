package chairing.chairing.domain.rental;

import java.time.LocalDateTime;

import chairing.chairing.domain.user.User;
import chairing.chairing.domain.wheelchair.Wheelchair;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Entity
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "wheelchair_id")
    private Wheelchair wheelchair;

    @Column(nullable = false)
    private LocalDateTime rentalDate;

    private LocalDateTime returnDate;

    @Column(nullable = false)
    private String rentalCode; // 임의의 코드

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RentalStatus status;

    // 생성자 추가
    public Rental(User user, Wheelchair wheelchair, LocalDateTime rentalDate, LocalDateTime returnDate, RentalStatus status) {
        this.user = user;
        this.wheelchair = wheelchair;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    // 상태 변경 메서드
    public void changeStatus(RentalStatus newStatus) {
        this.status = newStatus;
    }

}