package ba.unsa.etf.rpr.domain.entities;

import ba.unsa.etf.rpr.domain.Idable;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import lombok.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
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
    public Blob getImageBlob() /*throws IOException, SQLException*/ {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", baos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] imageBytes = baos.toByteArray();
        try {
            return new javax.sql.rowset.serial.SerialBlob(imageBytes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
