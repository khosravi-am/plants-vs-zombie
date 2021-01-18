package khosro.map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MapHome {
    private Image image;
    private int count, x,y, width, height;

    public MapHome(int count,int y,int height) {
        this.count = count;
        this.y=y;
        this.height=height;
        setXY(count);
        setImage(count);
    }

    private void setXY(int count) {
        if (count == 1) {
            x = 35;
            width=125;
        }

        if (count == 2) {
            x = 155;
            width=110;
            if (y>500) {
                x = 150;
                width=117;
            }
        }
        if (count == 3) {
            x = 260;
            width=125;
            if (y>500){
                x=255;
                width=135;
            }
        }
        if (count == 4) {
            x = 380;
            width=107;
        }
        if (count == 5) {
            x = 475;
            width=133;
        }
        if (count == 6) {
            x = 595;
            width=123;
        }
        if (count == 7) {
            x = 705;
            width=120;
        }
        if (count == 8) {
            x = 810;
            width=123;
        }

        if (count == 9) {
            x = 915;
            width=125;
            if (y>100)
                width=137;
        }

    }

    private void setImage(int count) {


    }

    public Image getImage() {
        return image;
    }

    public int getCount() {
        return count;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getY() {
        return y;
    }

    public void setImage(Image image) {
        this.image = image;
    }

}
