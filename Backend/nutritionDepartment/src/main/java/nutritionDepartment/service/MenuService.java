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
        if (request.breakfastPriceBudget() != null) menu.setBreakfastPriceBudget(request.breakfastPriceBudget());
        if (request.lunchPriceBudget() != null) menu.setLunchPriceBudget(request.lunchPriceBudget());
        if (request.dinnerPriceBudget() != null) menu.setDinnerPriceBudget(request.dinnerPriceBudget());
        if (request.breakfastPriceSelfFinance() != null) menu.setBreakfastPriceSelfFinance(request.breakfastPriceSelfFinance());
        if (request.lunchPriceSelfFinance() != null) menu.setLunchPriceSelfFinance(request.lunchPriceSelfFinance());
        if (request.dinnerPriceSelfFinance() != null) menu.setDinnerPriceSelfFinance(request.dinnerPriceSelfFinance());
        return toResponse(menuRepository.save(menu));
    }

    private MenuResponse toResponse(Menu menu) {
        return new MenuResponse(
                menu.getBreakfastPriceBudget(),
                menu.getLunchPriceBudget(),
                menu.getDinnerPriceBudget(),
                menu.getBreakfastPriceSelfFinance(),
                menu.getLunchPriceSelfFinance(),
                menu.getDinnerPriceSelfFinance()
        );
    }

}
