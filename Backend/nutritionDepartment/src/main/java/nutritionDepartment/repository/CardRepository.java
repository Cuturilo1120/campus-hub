package nutritionDepartment.repository;

import nutritionDepartment.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByStudentId(Long studentId);

    List<Card> findByCashierId(Long cashierId);

    List<Card> findByExpirationDateBefore(Date date);
}
