package chairing.chairing.dto.delivery;

import chairing.chairing.domain.delivery.DeliveryStatus;
import chairing.chairing.domain.rental.Rental;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class delivery {
    private Rental rental;  //
    private DeliveryStatus status;
    private String deliveryAddress;
    private Long deliveryId;  // 연결된 Delivery ID
}
