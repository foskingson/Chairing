package chairing.chairing.domain.rental;

public enum RentalStatus {
    PENDING,    // 대기 중
    APPROVED,   // 승인됨
    REJECTED,   // 거절됨
    COMPLETED,   // 완료됨

    ACTIVE, RETURNED
}
