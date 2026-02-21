package users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import users.config.JwtUtil;
import users.model.dto.AuthResponse;
import users.model.dto.LoginRequest;
import users.model.dto.RegisterRequest;
import users.model.dto.StudentRegisterRequest;
import users.model.entity.Employee;
import users.model.entity.Student;
import users.repository.EmployeeRepository;
import users.repository.StudentRepository;

@Service
public class AuthService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse login(LoginRequest request) {
        UserDetails userDetails = employeeService.loadUserByUsername(request.username());
        if (!passwordEncoder.matches(request.password(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(userDetails);
        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse("");
        return new AuthResponse(token, request.username(), role);
    }

    public void register(RegisterRequest request) {
        if (employeeRepository.findByUsername(request.username()).isPresent()) {
            throw new IllegalArgumentException("Username already taken: " + request.username());
        }
        Employee employee = new Employee();
        employee.setFirstName(request.firstName());
        employee.setLastName(request.lastName());
        employee.setUsername(request.username());
        employee.setPassword(passwordEncoder.encode(request.password()));
        employee.setRole(request.role());
        employeeRepository.save(employee);
    }

    public void registerStudent(StudentRegisterRequest request) {
        if (studentRepository.findByUsername(request.username()).isPresent()) {
            throw new IllegalArgumentException("Username already taken: " + request.username());
        }
        Student student = new Student();
        student.setFirstName(request.firstName());
        student.setLastName(request.lastName());
        student.setUsername(request.username());
        student.setPassword(passwordEncoder.encode(request.password()));
        student.setIndexNumber(request.indexNumber());
        student.setFacultyName(request.facultyName());
        student.setStatus(request.status());
        student.setCity(request.city());
        studentRepository.save(student);
    }

    public AuthResponse loginStudent(LoginRequest request) {
        UserDetails userDetails = studentService.loadUserByUsername(request.username());
        if (!passwordEncoder.matches(request.password(), userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token, request.username(), "STUDENT");
    }
}
