package chairing.chairing.domain.wheelchair;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
@Entity
public class Wheelchair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wheelchairId;

    @Enumerated(EnumType.STRING)
    private WheelchairStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WheelchairType type;

    @Embedded
    @Column(nullable = true)
    private Location location = new Location(0, 0); // TODO => POINT 타입을 String으로 매핑
    

    public Wheelchair(WheelchairStatus status, WheelchairType type, Location location) {
        this.status = status;
        this.type = type;
        this.location = location;
    }


    public void changeStatus(WheelchairStatus newStatus){
        status=newStatus;
    }
}