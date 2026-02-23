package dorm.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pavilion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    private Integer capacity;

    private String address;

    @ManyToOne
    @JsonBackReference("dorm-pavilion")
    private Dorm dorm;

    @OneToMany(mappedBy = "pavilion")
    @JsonManagedReference("pavilion-room")
    private List<Room> roomList;
}
