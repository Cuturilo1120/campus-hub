package nutritionDepartment.repository;

import nutritionDepartment.model.Status;
import nutritionDepartment.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByIndex(String index);

    List<Student> findByStatus(Status status);

    List<Student> findByFaculty(String faculty);

    List<Student> findByLastNameContainingIgnoreCase(String lastName);
}
