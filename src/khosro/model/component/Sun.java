package khosro.model.component;

import khosro.model.map.MapHome;

import javax.swing.*;
import java.awt.*;

public class Sun {
    private Image image;
    private MapHome locationX;
    private MapHome locationY;
    private final int disappearTimer = 5;
    private int x;
    private int y;

    public Sun(int x, int y) {
        this.x = x;
        this.y = y;
        //TODO set image address to program.
        image = new ImageIcon("imageIcon.png").getImage();
    }
}
