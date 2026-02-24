package dorm.repository;

import dorm.model.entity.DormStay;
import dorm.model.entity.StayStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DormStayRepository extends JpaRepository<DormStay, Long> {

    List<DormStay> findByStayStatusAndMoveInDateLessThanEqual(StayStatus status, Date date);

    List<DormStay> findByStayStatusAndMoveOutDateLessThanEqual(StayStatus status, Date date);

}
