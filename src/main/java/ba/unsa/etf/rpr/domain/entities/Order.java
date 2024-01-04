package ba.unsa.etf.rpr.domain.entities;

import ba.unsa.etf.rpr.domain.Idable;

import java.util.Objects;

public class Order implements Idable {
    int id;
    int userId;
    int statusId;
    String selectedMeals;

    public Order(int id, int userId, int statusId, String selectedMeals) {
        this.id = id;
        this.userId = userId;
        this.statusId = statusId;
        this.selectedMeals = selectedMeals;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getSelectedMeals() {
        return selectedMeals;
    }

    public void setSelectedMeals(String selectedMeals) {
        this.selectedMeals = selectedMeals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order orders = (Order) o;
        return id == orders.id && userId == orders.userId && statusId == orders.statusId && Objects.equals(selectedMeals, orders.selectedMeals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, statusId, selectedMeals);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", userId=" + userId +
                ", statusId=" + statusId +
                ", selectedMeals='" + selectedMeals + '\'' +
                '}';
    }
}
