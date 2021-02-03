package khosro.model.component.zombie;

import javax.swing.*;
import java.io.Serializable;

public class Zombie implements Runnable , Serializable {
    private int x, y, width, height, life, row, home, headLife, speed = 2;
    private ImageIcon image, gif;
    private double power;
    private Boolean live, run = false;
    private Long bornTime;

    public Zombie() {
        live = true;
        bornTime = System.currentTimeMillis();
    }

    public Long getBornTime() {
        return bornTime;
    }

    public void setBornTime(Long bornTime) {
        this.bornTime = bornTime;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setHome(int home) {
        this.home = home;
    }

    public void setHeadLife(int headLife) {
        this.headLife = headLife;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setRun(Boolean run) {
        this.run = run;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setGif(ImageIcon gif) {
        this.gif = gif;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public ImageIcon getGif() {
        return gif;
    }

    public int getY() {
        return y;
    }

    public int getHeadLife() {
        return headLife;
    }

    public int getX() {
        return x;
    }

    public ImageIcon getImage() {
        return image;
    }

    public Boolean getLive() {
        return live;
    }

    public Boolean getRun() {
        return run;
    }

    public int getHeight() {
        return height;
    }

    public int getHome() {
        return home;
    }

    public int getLife() {
        return life;
    }

    public int getRow() {
        return row;
    }

    public int getWidth() {
        return width;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        bornTime = System.currentTimeMillis();
        while (live && getX() > 10) {
            System.out.println("nnnnnnn");
            if (System.currentTimeMillis() - bornTime > 1000) {
                System.out.println("n");

                setX(getX() - 1);
                bornTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public String toString() {
        return "Zombie{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", life=" + life +
                ", row=" + row +
                ", home=" + home +
                ", headLife=" + headLife +
                ", speed=" + speed +
                ", image=" + image +
                ", gif=" + gif +
                ", power=" + power +
                ", live=" + live +
                ", run=" + run +
                ", bornTime=" + bornTime +
                '}';
    }
}
