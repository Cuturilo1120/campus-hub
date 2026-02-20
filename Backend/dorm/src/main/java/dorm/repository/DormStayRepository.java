package dorm.repository;

import dorm.model.entity.DormStay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DormStayRepository extends JpaRepository<DormStay, Long> {

}
