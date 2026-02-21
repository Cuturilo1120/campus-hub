package users.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String facultyName;

    @Enumerated(EnumType.STRING)
    private StudyStatus status;

    private String city;

    private String username;

    private String password;

}
