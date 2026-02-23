package dorm.repository;

import dorm.model.entity.ApplicationStatus;
import dorm.model.entity.RoomApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomApplicationRepository extends JpaRepository<RoomApplication, Long> {

    boolean existsByStudentIdAndDormIdAndStatusIn(Long studentId, Long dormId, List<ApplicationStatus> statuses);

    List<RoomApplication> findByStudentId(Long studentId);

}
