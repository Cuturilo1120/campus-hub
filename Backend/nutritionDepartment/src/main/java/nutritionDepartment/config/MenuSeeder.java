package nutritionDepartment.config;

import nutritionDepartment.model.Menu;
import nutritionDepartment.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MenuSeeder implements CommandLineRunner {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public void run(String... args) {
        if (!menuRepository.existsById(1L)) {
            menuRepository.save(new Menu(1L, 2.00, 4.00, 3.00, 2.50, 4.80, 3.60));
        } else {
            Menu menu = menuRepository.findById(1L).get();
            if (menu.getBreakfastPriceSelfFinance() == null) menu.setBreakfastPriceSelfFinance(2.50);
            if (menu.getLunchPriceSelfFinance() == null) menu.setLunchPriceSelfFinance(4.80);
            if (menu.getDinnerPriceSelfFinance() == null) menu.setDinnerPriceSelfFinance(3.60);
            menuRepository.save(menu);
        }
    }

}