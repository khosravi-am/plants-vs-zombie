package khosro.model.map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class MapRow implements Serializable {
    private ArrayList<MapHome> mapHomes;
    private ImageIcon image;
    private int count, x, width, y, height;
    private Boolean haveZombie = false,haveCar=true;

    public MapRow(int count) {
        this.count = count;
        setXY(count);
        setMapHomes();
        setImage();
    }

    private void setImage() {
            image=new ImageIcon("./src/khosro/model/res/car.png");

    }

    public void setHaveCar(Boolean haveCar) {
        this.haveCar = haveCar;
    }


    public Boolean getHaveCar() {
        return haveCar;
    }

    private void setXY(int count) {
        x=-30;
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

    public int size() {
        int j = 0;
        for (int i = 0; i < 9; i++)
            if (mapHomes.get(i).isPor())
                j++;
        return j;
    }

    public int sunFlowerNumber() {
        int j = 0;
        for (int i = 0; i < 9; i++)
            if (mapHomes.get(i).isPor() && mapHomes.get(i).getPlant().getClass().getSimpleName().equals("SunFlower"))
                j++;
        return j;
    }

    public int freezeNumber() {
        int j = 0;
        for (int i = 0; i < 9; i++)
            if (mapHomes.get(i).isPor() && mapHomes.get(i).getPlant().getClass().getSimpleName().equals("SnowPea"))
                j++;
        return j;
    }

    public int peaNumber() {
        int j = 0;
        for (int i = 0; i < 9; i++)
            if (mapHomes.get(i).isPor() && mapHomes.get(i).getPlant().getClass().getSimpleName().equals("PeaShooter"))
                j++;
        return j;
    }

    public int potatoNumber() {
        int j = 0;
        for (int i = 0; i < 9; i++)
            if (mapHomes.get(i).isPor() && mapHomes.get(i).getPlant().getClass().getSimpleName().equals("Potato"))
                j++;
        return j;
    }

    public void setHaveZombie() {

        for (int i = 0; i < 9; i++)
            if (mapHomes.get(i).getHaveZombie()) {
                haveZombie = true;
                return;
            }
        haveZombie = false;
    }

    public Boolean getHaveZombie() {
        return haveZombie;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public Image getImage() {
        return image.getImage();
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
            mapHomes.get(i).setRow(count);
            mapHomes.get(i).setImage(new ImageIcon("./src/khosro/model/res/" + String.valueOf(count) + "," + String.valueOf(i) + ".png"));

        }
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "MapRow{" +
                "mapHomes=" + mapHomes +
                ", image=" + image +
                ", count=" + count +
                ", x=" + x +
                ", width=" + width +
                ", y=" + y +
                ", height=" + height +
                ", haveZombie=" + haveZombie +
                ", haveCar=" + haveCar +
                '}';
    }
}
