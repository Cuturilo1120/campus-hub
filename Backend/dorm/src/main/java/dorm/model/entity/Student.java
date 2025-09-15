package dorm.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String indexNumber;

    private String FacultyName;

    @Enumerated(EnumType.STRING)
    private StudyStatus status;

    private String city;

    @OneToMany(mappedBy = "student")
    private List<DormStay> dormStayList;

    @OneToMany(mappedBy = "student")
    private List<RoomApplication> roomApplicationList;

}
