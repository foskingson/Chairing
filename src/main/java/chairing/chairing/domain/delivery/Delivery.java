package chairing.chairing.domain.delivery;

import chairing.chairing.domain.rental.Rental;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor  // 기본 생성자
@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    @OneToOne
    @JoinColumn(name = "rental_id", nullable = false)
    private Rental rental;      // 배송과 연결된 대여

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus deliveryStatus;  // 배송 상태

    @Column(nullable = false)
    private String deliveryAddress;         // 배송지 주소

    // 필드 값을 초기화하는 생성자 추가
    public Delivery(Rental rental, DeliveryStatus deliveryStatus, String deliveryAddress) {
        this.rental = rental;
        this.deliveryStatus = deliveryStatus;
        this.deliveryAddress = deliveryAddress;
    }

    // 상태 변경 메서드 추가
    public void updateStatus(DeliveryStatus newStatus) {
        this.deliveryStatus = newStatus;
    }
}
