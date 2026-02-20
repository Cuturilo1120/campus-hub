package nutritionDepartment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int mealNumber;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

}
