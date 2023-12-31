package ba.unsa.etf.rpr.domain.entities;

import ba.unsa.etf.rpr.domain.Idable;
import ba.unsa.etf.rpr.domain.enums.OrderStatus;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@AllArgsConstructor

public class Order implements Idable {
    private int id;
    private int userId;
    private int statusId;
    private String selectedMeals;

    public Order(Integer userId, int statusId, String selectedMeals) {
        this.userId = userId;
        this.statusId = statusId;
        this.selectedMeals = selectedMeals;
    }
    public void setStatus(OrderStatus status) {
        this.statusId = status.getStatusId();

    }
}
