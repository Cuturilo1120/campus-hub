package nutritionDepartment.model;

import jakarta.persistence.Column;
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

    @Column(name = "breakfast_price")
    private Double breakfastPriceBudget;

    @Column(name = "lunch_price")
    private Double lunchPriceBudget;

    @Column(name = "dinner_price")
    private Double dinnerPriceBudget;

    private Double breakfastPriceSelfFinance;
    private Double lunchPriceSelfFinance;
    private Double dinnerPriceSelfFinance;

}
