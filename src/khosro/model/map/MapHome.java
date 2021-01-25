package khosro.model.map;

import khosro.model.component.plant.Plants;

import javax.swing.*;
import java.awt.*;

public class MapHome {
    private Image image;
    private int count, x, y, x2, y2, width, height;
    private Boolean click = false, sunflower = false, pea = false, freeze = false, potato = false, cherry = false, por = false;
    private Plants plant;

    public MapHome(int count, int y, int height) {
        this.count = count;
        this.y = y;
        this.height = height;
        setXY(count);
        setImage(count);
      /*  setFocusable(false);
        setLayout(null);
        setBounds(x,y,width-10,height-30);
        if (y>115)
            setBounds(x,y-30,width-10,height);
      */  //setFocusPainted(false);
        x2 = x + width;
        y2 = y + height;
    }

    private void setXY(int count) {
        if (count == 1) {
            x = 35;
            width = 125;
        }

        if (count == 2) {
            x = 155;
            width = 110;
            if (y > 500) {
                x = 150;
                width = 117;
            }
        }
        if (count == 3) {
            x = 260;
            width = 125;
            if (y > 500) {
                x = 255;
                width = 135;
            }
        }
        if (count == 4) {
            x = 380;
            width = 107;
        }
        if (count == 5) {
            x = 475;
            width = 133;
        }
        if (count == 6) {
            x = 595;
            width = 123;
        }
        if (count == 7) {
            x = 705;
            width = 120;
        }
        if (count == 8) {
            x = 810;
            width = 123;
        }

        if (count == 9) {
            x = 915;
            width = 125;
            if (y > 100)
                width = 137;
        }

    }

    private void setImage(int count) {


    }

    public Boolean isPor() {
        return por;
    }

    public Plants getPlant() {
        return plant;
    }

    public void setClick(Boolean click) {
        this.click = click;
    }

    public Boolean getClick() {
        return click;
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

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public Boolean getCherry() {
        return cherry;
    }

    public Boolean getFreeze() {
        return freeze;
    }

    public Boolean getSunflower() {
        return sunflower;
    }

    public Boolean getPea() {
        return pea;
    }

    public Boolean getPotato() {
        return potato;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setFreeze(Boolean freeze) {
        this.freeze = freeze;
    }

    public void setCherry(Boolean cherry) {
        this.cherry = cherry;
    }

    public void setPea(Boolean pea) {
        this.pea = pea;
    }

    public void setSunflower(Boolean sunflower) {
        this.sunflower = sunflower;
    }

    public void setPotato(Boolean potato) {
        this.potato = potato;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setPlant(Plants plan) {

        if (plan != null && plan.getClass().getSimpleName().equals("Cherry"))
            plan.setGif(new ImageIcon("./src/khosro/model/res/cherry.gif"));
        plant = plan;
        setPor();
    }

    private void setPor() {
        if (this.plant == null)
            por = false;
        else
            por = true;
    }
}
