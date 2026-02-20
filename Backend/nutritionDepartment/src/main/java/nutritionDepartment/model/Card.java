package nutritionDepartment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double balance;

    @Temporal(TemporalType.DATE)
    private Date expirationDate;

    private Long studentId;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Meal> meals;

}
