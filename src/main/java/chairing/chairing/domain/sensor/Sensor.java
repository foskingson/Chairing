package chairing.chairing.domain.sensor;


import chairing.chairing.domain.wheelchair.Wheelchair;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sensorId;

    @OneToOne
    @JoinColumn(name = "wheelchair_id", nullable = false)
    private Wheelchair wheelchair;

    @Column(nullable = false)  // String으로 GPS 좌표를 저장
    private String gpsCoordinates;

    @Column(nullable = false)
    private boolean sosAlertStatus = false;

    @Column(nullable = false)
    private boolean objectDetectionStatus = false;
}
