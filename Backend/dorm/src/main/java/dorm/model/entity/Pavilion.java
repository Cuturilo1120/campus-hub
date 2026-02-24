package dorm.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"number", "dorm_id"}))
public class Pavilion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    private String address;

    @ManyToOne
    @JsonIgnoreProperties({"pavilions", "roomApplications"})
    private Dorm dorm;

    @OneToMany(mappedBy = "pavilion")
    @JsonIgnoreProperties({"pavilion"})
    private List<Room> roomList;
}
