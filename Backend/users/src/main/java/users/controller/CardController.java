package users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import users.model.dto.CardRequest;
import users.model.dto.CardResponse;
import users.service.CardService;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('CASHIER')")
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
    @PreAuthorize("hasRole('CASHIER')")
    public void delete(@PathVariable Long id) {
        cardService.deleteCard(id);
    }
}
