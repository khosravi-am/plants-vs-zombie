package khosro.model.component.plant;

import khosro.model.component.Bullet.Bullet;
import khosro.model.component.Sun;
import khosro.model.component.zombie.Zombie;
import khosro.model.map.MapHome;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.interrupted;

/**
 * SuperClass of all other plants.
 * Keep fields of plants.
 */
public class Plants implements Cloneable {

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
    protected ImageIcon image;
    protected ImageIcon gif;

    /**
     * Cost of each plants per sun
     */
    protected int cost;

    /**
     * Each plants how many life time when zombies eat it.
     */
    protected double life;

    /**
     * The time each plants produce its product (sunflower produce a sun and shooters shoot a bullet)
     */
    protected int preparingTime;

    /**
     * X location in visual map
     */

    /**
     * Time has left a plants card refresh.
     */
    protected long bornTime;

    /**
     * X location in map
     */
    protected int x;
    protected int width;
    protected int height;

    /**
     * Y location in map
     */
    protected int y;

    protected boolean live;
    private Runnable runnable;
    protected boolean use = false;
    protected boolean use2 = false;

    /**
     * @param bulletArr Array of bullet that plant can shoot it. If plant can't shoot a bullet, it equal to null.
     * @param img       Image address.
     * @param image     Image of plants.
     * @param life      Each plants how many life time when zombies eat it.
     * @param bornTime  Time has left a plants card refresh.
     * @param x         X location in map
     * @param y         Y location in map
     */
    public Plants(ArrayList<Bullet> bulletArr,
                  BufferedImage img,
                  ImageIcon image,
                  double life,
                  long bornTime,
                  int x,
                  int y) {
        this.bulletArr = bulletArr;
        this.img = img;
        this.image = image;
        this.life = life;
        this.bornTime = bornTime;
        this.x = x;
        this.y = y;
        live = true;

        try {
            img = ImageIO.read(new File(" input.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        image = new ImageIcon("input.gif");
    }

    public Plants() {
        live = true;
        setBornTime(System.currentTimeMillis());

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

    public ImageIcon getImage() {
        return image;
    }

    public boolean getUse2() {
        return use2;
    }

    public void setUse2(boolean use2) {
        this.use2 = use2;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
    }

    public int getPreparingTime() {
        return preparingTime;
    }

    public void setPreparingTime(int preparingTime) {
        this.preparingTime = preparingTime;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int high) {
        this.height = high;
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

    public ImageIcon getGif() {
        return gif;
    }

    public void setGif(ImageIcon gif) {
        this.gif = gif;
    }

    //TODO remove plant form screen.
    public void dead() {
        live = false;
    }

    public void dead(Sun sun) {
        live = false;
        sun = null;
    }

    /**
     * @return
     * @throws CloneNotSupportedException
     */

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public Runnable bemir(Zombie zombie) {
        runnable = new Runnable() {
            @Override
            public void run() {
                while (live && zombie.getLive()) {
                    if (zombie==null)
                        break;
                    if (System.currentTimeMillis() - getBornTime() > 150) {
                        setBornTime(System.currentTimeMillis());
                        setLife(getLife() - zombie.getPower());
                        if (getLife() <= 0) {
                            dead();
                            interrupted();
                            break;
                        }
                    }
                }
                interrupted();
            }
        };

        return runnable;
    }
}