package dorm.repository;

import dorm.model.entity.RoomApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomApplicationRepository extends JpaRepository<RoomApplication, Long> {

    boolean existsByStudentIdAndDormId(Long studentId, Long dormId);

}
