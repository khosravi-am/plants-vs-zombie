package khosro.model.component.plant;

import khosro.model.component.Bullet.Bullet;
import khosro.model.component.Sun;
import khosro.model.map.MapHome;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * SunFlower plant
 */
public class SunFlower extends Plants {

    /**
     * Time has left to produce a sun per second by a sunflower.
     */
    public static final int PRODUCINGTIME = 20;
    private Boolean haveSun=false;
    private Sun sun;
    private Long l;

    /**
     * @param bulletArr Array of bullet that plant can shoot it. If plant can't shoot a bullet, it equal to null.
     * @param img       Image address.
     * @param image     Image of plants.
     * @param life      Each plants how many life time when zombies eat it.
     * @param locX      X location in visual map
     * @param locY      Y location in visual map
     * @param bornTime  Time has left a plants card refresh.
     * @param x         X location in map
     * @param y         Y location in map
     */

    public SunFlower(ArrayList<Bullet> bulletArr,
                     BufferedImage img,
                     ImageIcon image,
                     int life,
                     MapHome locX,
                     MapHome locY,
                     long bornTime,
                     int x,
                     int y) {
        super(bulletArr, img, image, life, bornTime, x, y);
        cost = 100;
        preparingTime = 5;
    }

    public SunFlower(){
        setImage(new ImageIcon("./src/khosro/model/res/sunflower.png"));
        setGif(new ImageIcon("./src/khosro/model/res/sunflower.gif"));
        //setWidth(100);
        //setHeight(120);
        setPreparingTime(15000);
        sun=new Sun(getX()+50,getY()+50);
        setLife(50);
        setCost(50);
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        sun.setX(x+40);
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        sun.setY(y+30);
    }

    public Sun getSun() {
        return sun;
    }

    public void setSun(Sun sun) {
        this.sun=sun;
        if (sun==null)
            haveSun=false;
        else
            haveSun=true;
    }

    public Boolean getHaveSun() {
        return haveSun;
    }

    public void setHaveSun(Boolean haveSun) {
        this.haveSun = haveSun;
    }

    /**
     * Produce a sun
     */
    public void produceSun() {
       Thread thread=new Thread(){
           @Override
           public void run() {
               setBornTime(System.currentTimeMillis());
               while (isLive()) {
                   if (System.currentTimeMillis()-getBornTime()>5000) {
                       if (haveSun) {
                           setHaveSun(false);
                           setBornTime(System.currentTimeMillis());
                           continue;
                       }
                       if (System.currentTimeMillis()-getBornTime()>6500){
                           setHaveSun(true);
                           setBornTime(System.currentTimeMillis());
                       }
                   }


               }
           }
       };
       thread.start();

    }
}
