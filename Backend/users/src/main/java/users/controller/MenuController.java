package users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import users.model.dto.MenuResponse;
import users.model.dto.MenuUpdateRequest;
import users.service.MenuService;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public MenuResponse getMenu() {
        return menuService.getMenu();
    }

    @PatchMapping
    @PreAuthorize("hasRole('COOK') or hasRole('ADMIN')")
    public MenuResponse updateMenu(@RequestBody MenuUpdateRequest request) {
        return menuService.updateMenu(request);
    }

}