package users.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import users.model.dto.MenuResponse;
import users.model.dto.MenuUpdateRequest;
import users.model.dto.StudentMenuResponse;
import users.repository.StudentRepository;
import users.service.MenuService;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public MenuResponse getMenu() {
        return menuService.getMenu();
    }

    @GetMapping("/mine")
    @PreAuthorize("hasRole('STUDENT')")
    public StudentMenuResponse getMyMenu() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        var student = studentRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        return menuService.getMenuForStudent(student.getStatus());
    }

    @PatchMapping
    @PreAuthorize("hasRole('COOK') or hasRole('ADMIN')")
    public MenuResponse updateMenu(@RequestBody MenuUpdateRequest request) {
        return menuService.updateMenu(request);
    }

}
