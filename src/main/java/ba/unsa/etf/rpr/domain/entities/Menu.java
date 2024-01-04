package ba.unsa.etf.rpr.domain.entities;

import ba.unsa.etf.rpr.domain.Idable;
import javafx.scene.image.Image;

import java.util.Objects;

public class Menu implements Idable {
    int id;
    String name;
    String type;
    String description;
    Image image;
    double price;
    int amount;

    public Menu(int id, String name, String type, String description, Image image, double price, int amount) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.image = image;
        this.price = price;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return id == menu.id && Double.compare(price, menu.price) == 0 && amount == menu.amount && Objects.equals(name, menu.name) && Objects.equals(type, menu.type) && Objects.equals(description, menu.description) && Objects.equals(image, menu.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, description, image, price, amount);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
