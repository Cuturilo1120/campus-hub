package nutritionDepartment.repository;

import nutritionDepartment.model.Meal;
import nutritionDepartment.model.MealType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findByCardId(Long cardId);

    List<Meal> findByMealType(MealType mealType);

    List<Meal> findByCardIdAndMealType(Long cardId, MealType mealType);
}
