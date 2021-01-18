package khosro.map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MapRow {
    private ArrayList<MapHome> mapHomes;
    private Image image,image2;
    private int count, x, width, y, height;

    public MapRow(int count) {
        this.count = count;
        setXY(count);
        setMapHomes();
        setImage();
    }

    private void setImage() {
        try {
            image = ImageIO.read(new File("./images/car.png"));
            if (count==0)
                image2=ImageIO.read(new File("./images/row-1.png"));
            if (count==1)
                image2=ImageIO.read(new File("./images/row-2.png"));
            if (count==2)
                image2=ImageIO.read(new File("./images/row-3.png"));
            if (count==3)
                image2=ImageIO.read(new File("./images/row-4.png"));
            if (count==4)
                image2=ImageIO.read(new File("./images/row-5.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Image getImage2() {
        return image2;
    }

    private void setXY(int count) {
        if (count == 0) {
            y = 95;
            height = 147;
        }
        if (count == 1) {
            y = 240;
            height = 130;
        }
        if (count == 2) {
            y = 360;
            height = 130;
        }
        if (count == 3) {
            y = 480;
            height = 125;
        }
        if (count == 4) {
            y = 595;
            height = 145;
        }

    }

    public void setImage(Image image) {
        this.image = image;
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getY() {
        return y;
    }

    public ArrayList<MapHome> getMapHomes() {
        return mapHomes;
    }

    private void setMapHomes() {
        mapHomes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            mapHomes.add(new MapHome(i + 1, y, height));
            try {
                mapHomes.get(i).setImage(ImageIO.read(new File("./images/" + String.valueOf(count) + "," + String.valueOf(i) + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
