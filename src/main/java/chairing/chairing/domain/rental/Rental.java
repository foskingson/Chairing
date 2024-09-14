package chairing.chairing.domain.rental;

import java.time.LocalDateTime;

import chairing.chairing.domain.user.User;
import chairing.chairing.domain.wheelchair.Wheelchair;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Entity
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

    public void changeStatus(RentalStatus newStatus){
        status=newStatus;
    }

    public Rental(User user, Wheelchair wheelchair, LocalDateTime rentalDate, LocalDateTime returnDate,
            String rentalCode, RentalStatus status) {
        this.user = user;
        this.wheelchair = wheelchair;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.rentalCode = rentalCode;
        this.status = status;
    }
    
}