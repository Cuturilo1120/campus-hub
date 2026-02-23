package users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import users.model.dto.MenuResponse;
import users.model.dto.MenuUpdateRequest;
import users.model.dto.StudentMenuResponse;
import users.model.entity.StudyStatus;

@Service
public class MenuService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${nutrition.service.url}")
    private String nutritionServiceUrl;

    public MenuResponse getMenu() {
        return restTemplate.getForObject(nutritionServiceUrl + "/menu", MenuResponse.class);
    }

    public StudentMenuResponse getMenuForStudent(StudyStatus status) {
        MenuResponse menu = getMenu();
        if (status == StudyStatus.SELF_FINANCE) {
            return new StudentMenuResponse(menu.breakfastPriceSelfFinance(), menu.lunchPriceSelfFinance(), menu.dinnerPriceSelfFinance());
        }
        return new StudentMenuResponse(menu.breakfastPriceBudget(), menu.lunchPriceBudget(), menu.dinnerPriceBudget());
    }

    public MenuResponse updateMenu(MenuUpdateRequest request) {
        return restTemplate.exchange(
                nutritionServiceUrl + "/menu",
                HttpMethod.PATCH,
                new HttpEntity<>(request),
                MenuResponse.class
        ).getBody();
    }

}