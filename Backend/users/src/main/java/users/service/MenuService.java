package users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import users.model.dto.MenuResponse;
import users.model.dto.MenuUpdateRequest;

@Service
public class MenuService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${nutrition.service.url}")
    private String nutritionServiceUrl;

    public MenuResponse getMenu() {
        return restTemplate.getForObject(nutritionServiceUrl + "/menu", MenuResponse.class);
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