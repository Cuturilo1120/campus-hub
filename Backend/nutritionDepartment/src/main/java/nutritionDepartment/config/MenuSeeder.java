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
            menuRepository.save(new Menu(1L, 2.00, 4.00, 3.00));
        }
    }

}