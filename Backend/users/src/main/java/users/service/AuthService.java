package users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import users.config.JwtUtil;
import users.model.dto.AuthResponse;
import users.model.dto.LoginRequest;
import users.model.dto.RegisterRequest;
import users.model.entity.Employee;
import users.repository.EmployeeRepository;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        UserDetails userDetails = employeeService.loadUserByUsername(request.username());
        String token = jwtUtil.generateToken(userDetails);
        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse("");
        return new AuthResponse(token, request.username(), role);
    }

    public AuthResponse register(RegisterRequest request) {
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

        UserDetails userDetails = employeeService.loadUserByUsername(request.username());
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token, request.username(), request.role().name());
    }
}
