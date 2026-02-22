package nutritionDepartment.controller;

import nutritionDepartment.model.dto.CardRequest;
import nutritionDepartment.model.dto.CardResponse;
import nutritionDepartment.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponse create(@RequestBody CardRequest request) {
        return cardService.createCard(request);
    }

    @GetMapping
    public List<CardResponse> getAll() {
        return cardService.getAllCards();
    }

    @GetMapping("/{id}")
    public CardResponse getOne(@PathVariable Long id) {
        return cardService.getCardById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
}
