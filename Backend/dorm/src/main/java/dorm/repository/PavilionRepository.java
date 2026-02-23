package dorm.repository;

import dorm.model.entity.Pavilion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PavilionRepository extends JpaRepository<Pavilion, Long> {

    boolean existsByNumberAndDormId(Integer number, Long dormId);

}
