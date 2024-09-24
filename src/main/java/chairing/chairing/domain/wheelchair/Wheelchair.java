package chairing.chairing.domain.wheelchair;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
@Entity
public class Wheelchair {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private UUID wheelchairId;

    @Enumerated(EnumType.STRING)
    private WheelchairStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WheelchairType type;

    private String location = "here";

    public Wheelchair(WheelchairStatus status, WheelchairType type, String location) {
        this.status = status;
        this.type = type;
        this.location = location;
    }

    public void changeStatus(WheelchairStatus newStatus) {
        this.status = newStatus;
    }
}
