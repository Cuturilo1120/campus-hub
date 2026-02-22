package nutritionDepartment.controller;

import nutritionDepartment.model.dto.MenuResponse;
import nutritionDepartment.model.dto.MenuUpdateRequest;
import nutritionDepartment.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public MenuResponse getMenu() {
        return menuService.getMenu();
    }

    @PatchMapping
    public MenuResponse updateMenu(@RequestBody MenuUpdateRequest request) {
        return menuService.updateMenu(request);
    }

}