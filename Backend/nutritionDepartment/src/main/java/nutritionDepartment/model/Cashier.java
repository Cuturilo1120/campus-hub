package nutritionDepartment.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Cashier extends Employee {

    @OneToMany(mappedBy = "cashier", cascade = CascadeType.ALL)
    private List<Card> cards;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
