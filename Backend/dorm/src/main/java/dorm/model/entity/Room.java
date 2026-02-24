package dorm.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;

    private Integer capacity;

    @ManyToOne
    @JsonIgnoreProperties({"roomList", "dormStayList"})
    private Pavilion pavilion;

    @OneToMany(mappedBy = "room")
    @JsonManagedReference("room-dormstay")
    private List<DormStay> dormStayList;

}
