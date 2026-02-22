package nutritionDepartment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    @Id
    private Long id = 1L;

    private Double breakfastPrice;
    private Double lunchPrice;
    private Double dinnerPrice;

}