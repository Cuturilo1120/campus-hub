package nutritionDepartment.service;

import nutritionDepartment.model.Menu;
import nutritionDepartment.model.dto.MenuResponse;
import nutritionDepartment.model.dto.MenuUpdateRequest;
import nutritionDepartment.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public MenuResponse getMenu() {
        Menu menu = menuRepository.findById(1L).orElseThrow();
        return toResponse(menu);
    }

    public MenuResponse updateMenu(MenuUpdateRequest request) {
        Menu menu = menuRepository.findById(1L).orElseThrow();
        if (request.breakfastPrice() != null) menu.setBreakfastPrice(request.breakfastPrice());
        if (request.lunchPrice() != null) menu.setLunchPrice(request.lunchPrice());
        if (request.dinnerPrice() != null) menu.setDinnerPrice(request.dinnerPrice());
        return toResponse(menuRepository.save(menu));
    }

    private MenuResponse toResponse(Menu menu) {
        return new MenuResponse(menu.getBreakfastPrice(), menu.getLunchPrice(), menu.getDinnerPrice());
    }

}