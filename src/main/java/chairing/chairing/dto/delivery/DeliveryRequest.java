package chairing.chairing.dto.delivery;

import chairing.chairing.domain.delivery.DeliveryStatus;

public class DeliveryRequest {

    private Long rentalId;
    private String deliveryAddress;
    private String status;  // String 타입으로 변경 (JSON 직렬화 문제 해결)

    // 생성자
    public DeliveryRequest(Long rentalId, String deliveryAddress, DeliveryStatus status) {
        this.rentalId = rentalId;
        this.deliveryAddress = deliveryAddress;
        this.status = status.name();  // DeliveryStatus를 문자열로 변환하여 저장
    }

    // Getters and Setters
    public Long getRentalId() {
        return rentalId;
    }

    public void setRentalId(Long rentalId) {
        this.rentalId = rentalId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
