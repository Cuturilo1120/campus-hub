package dorm.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DormStay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date moveInDate;

    @Temporal(TemporalType.DATE)
    private Date moveOutDate;

    @Enumerated(EnumType.STRING)
    private StayStatus stayStatus;

    private Long studentId;

    @ManyToOne
    private Room room;

    private Long emplyeeId;

}
