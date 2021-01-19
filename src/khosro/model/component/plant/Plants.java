package khosro.model.component.plant;

import khosro.model.component.Bullet.Bullet;
import khosro.model.map.MapHome;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * SuperClass of all other plants.
 * Keep fields of plants.
 */
public class Plants {

    /**
     * Array of bullet that plant can shoot it.
     * If plant can't shoot a bullet, it equal to null.
     */
    protected ArrayList<Bullet> bulletArr;

    /**
     * Image address.
     */
    protected BufferedImage img;

    /**
     * Image of plants.
     */
    protected Image image;

    /**
     * Cost of each plants per sun
     */
    protected int cost;

    /**
     * Each plants how many life time when zombies eat it.
     */
    protected int life;

    /**
     * The time each plants produce its product (sunflower produce a sun and shooters shoot a bullet)
     */
    protected int preparingTime;

    /**
     * X location in visual map
     */
    protected MapHome locX;

    /**
     * Y location in visual map
     */
    protected MapHome locY;

    /**
     * Time has left a plants card refresh.
     */
    protected long bornTime;

    /**
     * X location in map
     */
    protected int x;
    protected int weigh;
    protected int high;

    /**
     * Y location in map
     */
    protected int y;

    protected boolean live;

    protected boolean use;

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
    public Plants(ArrayList<Bullet> bulletArr,
                  BufferedImage img,
                  Image image,
                  int life,
                  MapHome locX,
                  MapHome locY,
                  long bornTime,
                  int x,
                  int y) {
        this.bulletArr = bulletArr;
        this.img = img;
        this.image = image;
        this.life = life;
        this.locX = locX;
        this.locY = locY;
        this.bornTime = bornTime;
        this.x = x;
        this.y = y;
        live = true;

        try {
            img = ImageIO.read(new File(" input.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        image = new ImageIcon("input.gif").getImage();
    }

    public Plants() {
    }

    public MapHome getLocX() {
        return locX;
    }

    public MapHome getLocY() {
        return locY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isLive() {
        return live;
    }

    public ArrayList<Bullet> getBulletArr() {
        return bulletArr;
    }

    public void setBulletArr(ArrayList<Bullet> bulletArr) {
        this.bulletArr = bulletArr;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getPreparingTime() {
        return preparingTime;
    }

    public void setPreparingTime(int preparingTime) {
        this.preparingTime = preparingTime;
    }

    public void setLocX(MapHome locX) {
        this.locX = locX;
    }

    public void setLocY(MapHome locY) {
        this.locY = locY;
    }

    public long getBornTime() {
        return bornTime;
    }

    public void setBornTime(long bornTime) {
        this.bornTime = bornTime;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getWeigh() {
        return weigh;
    }

    public void setWeigh(int weigh) {
        this.weigh = weigh;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isUse() {
        return use;
    }

    public void setUse(boolean use) {
        this.use = use;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    //TODO remove plant form screen.
    public void dead() {
        live = false;
    }
}
