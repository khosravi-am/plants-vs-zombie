package khosro.map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MapHome {
    private Image image;
    private int count, x1, width, height, num;

    public MapHome(int count) {
        this.count = count;
        setXY(count);
        setImage(count);
    }

    private void setXY(int count) {

    }

    private void setImage(int count) {


    }

    public Image getImage() {
        return image;
    }

    public int getCount() {
        return count;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return width;
    }

    public int getY2() {
        return height;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
