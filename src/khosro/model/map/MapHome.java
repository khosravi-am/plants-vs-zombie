package khosro.model.map;

import khosro.model.component.Bullet.Bullet;
import khosro.model.component.plant.Plants;
import khosro.model.component.zombie.Zombie;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


public class MapHome implements Serializable {
    private ImageIcon image;
    private int count, x,y,x2,y2, width, height,row;
    private Boolean click=false,sunflower=false,pea=false,freeze=false,potato=false,cherry=false,por=false,haveZombie=false,haveBullet=false,haveSnow=false;
    private Plants plant;
    private Zombie zombie;
    private Bullet bullet,snowBullet;
    public MapHome(int count,int y,int height) {
        this.count = count;
        this.y=y;
        this.height=height;
        setXY(count);
        setImage(count);
        x2=x+width;
        y2=y+height;
    }

    public void setZombie(Zombie zombie) {
        this.zombie = zombie;
        setHaveZombie();
        if (haveZombie) {
            this.zombie.setX(x2);
            this.zombie.setY(y-10);
        }

    }

    public Bullet getBullet() {
        return bullet;
    }

    public Zombie getZombie() {
        return zombie;
    }

    public void setHaveBullet(){
        if (bullet==null)
            haveBullet=false;
        else haveBullet=true;
    }

    public void setHaveSnow() {
        if (snowBullet==null)
            haveSnow=false;
        else haveSnow=true;
    }

    public Boolean getHaveSnow() {
        return haveSnow;
    }

    public Bullet getSnowBullet() {
        return snowBullet;
    }

    public void setSnowBullet(Bullet snowBullet) {
        this.snowBullet = snowBullet;
        setHaveSnow();
        if (haveSnow) {
            snowBullet.setX(getX() + 85);
            if (getY() > 110)
                snowBullet.setY(getY() + 20);
            else snowBullet.setY(getY() + 40);
        }
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
        setHaveBullet();
        if (haveBullet) {
            bullet.setX(getX()+85);
            if(getY()>110)
                bullet.setY(getY()+20);
            else bullet.setY(getY()+40);
        }
    }

    public Boolean getHaveBullet() {
        return haveBullet;
    }

    public void setHaveZombie() {
        if (zombie!=null)
            haveZombie=true;
        else haveZombie=false;
    }

    public Boolean getHaveZombie() {
        return haveZombie;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
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

    public Boolean isPor(){
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
        return image.getImage();
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

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public void setPlant(Plants plan) {

        if (plan!=null && plan.getClass().getSimpleName().equals("Cherry"))
            plan.setGif(new ImageIcon("./src/khosro/model/res/cherry.gif"));
        if (plan!=null) {
            plan.setX(getX());
            plan.setY(getY());
        }
        plant=plan;
        setPor();
    }

    private void setPor() {
        if (this.plant == null)
            por = false;
        else
            por = true;
    }

    @Override
    public String toString() {
        return "MapHome{" +
                "image=" + image +
                ", count=" + count +
                ", x=" + x +
                ", y=" + y +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", width=" + width +
                ", height=" + height +
                ", row=" + row +
                ", click=" + click +
                ", sunflower=" + sunflower +
                ", pea=" + pea +
                ", freeze=" + freeze +
                ", potato=" + potato +
                ", cherry=" + cherry +
                ", por=" + por +
                ", haveZombie=" + haveZombie +
                ", haveBullet=" + haveBullet +
                ", haveSnow=" + haveSnow +
                ", plant=" + plant +
                ", zombie=" + zombie +
                ", bullet=" + bullet +
                ", snowBullet=" + snowBullet +
                '}';
    }
}
