package chairing.chairing.domain.wheelchair;

import jakarta.persistence.*;
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

    // @Column(nullable = false)
    private String location = "here"; // TODO => POINT 타입을 String으로 매핑


    public Wheelchair(WheelchairStatus status, WheelchairType type, String location) {
        this.status = status;
        this.type = type;
        this.location = location;
    }


    public void changeStatus(WheelchairStatus newStatus){
        status=newStatus;
    }
}