package khosro.map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MapRow {
    private ArrayList<MapHome> mapHomes;
    private Image image;
    private int count,x1,x2,y1,y2;

    public MapRow(int count){
        this.count=count;
        setXY(count);
        setMapHomes();
        setImage();
    }

    private void setImage() {
        try {
            image=ImageIO.read(new File("./images/car.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setXY(int count) {

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

    public int getX1() {
        return x1;
    }

    public int getY2() {
        return y2;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public ArrayList<MapHome> getMapHomes() {
        return mapHomes;
    }

    private void setMapHomes() {
        mapHomes=new ArrayList<>();
        for (int i=0;i<9;i++) {
            mapHomes.add(new MapHome(i + 1));
            try {
                mapHomes.get(i).setImage(ImageIO.read(new File("./images/"+String.valueOf(count)+","+String.valueOf(i)+".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
