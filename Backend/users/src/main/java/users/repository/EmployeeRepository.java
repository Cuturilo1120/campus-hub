package users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import users.model.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
