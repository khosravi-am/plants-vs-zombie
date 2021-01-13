package khosro.model.component.Bullet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class SnowBullet extends Bullet {
    public SnowBullet(int x, int y) {
        super(x, y);
        try {
            bufferedImage = ImageIO.read(new File("src/khosro/model/res/frozenBullet"));
            image = new ImageIcon(bufferedImage).getImage();
        } catch (IOException e) {
            System.err.println("We have a problems in loading image.");
        }
    }
}