package dorm.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dateOfApplication;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    private Long studentId;

    @ManyToOne
    @JsonBackReference("dorm-application")
    private Dorm dorm;

}
