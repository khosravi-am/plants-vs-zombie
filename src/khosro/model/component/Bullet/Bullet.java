package khosro.model.component.Bullet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Thread.interrupted;

public class Bullet implements Runnable{
    private ImageIcon image;
    private Long bornTime;
    private Boolean run=false;
    private int power;
    protected int x;
    protected int y;
    protected int locX;
    protected int locY;

    public Bullet(int x,int y){

    }

    public Bullet(){
        setBornTime(System.currentTimeMillis());
    }

    public Long getBornTime() {
        return bornTime;
    }

    public void setBornTime(Long bornTime) {
        this.bornTime = bornTime;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public void setRun(Boolean run) {
        this.run = run;
    }

    public Boolean getRun() {
        return run;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
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
        while (run){
            if (System.currentTimeMillis()-bornTime>5){
                setX(getX()+2);
                bornTime=System.currentTimeMillis();
            }
        }
        interrupted();
    }
}
