package ba.unsa.etf.rpr.domain.entities;

import ba.unsa.etf.rpr.domain.Idable;
import javafx.scene.image.Image;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@AllArgsConstructor

public class Menu implements Idable {
    private int id;
    private String name;
    private String type;
    private String description;
    private Image image;
    private double price;
    private int amount;

}
