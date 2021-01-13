package khosro.zambia;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Zombie {
    protected int y;
    protected int x;
    protected int life;
    protected int counter;
    protected int speed;
    protected int locX;
    protected int locY;
    protected BufferedImage bufferedImage;
    protected Image image;
    protected boolean zombieMove;

    public Zombie() {
        //TODO get screen high and weigh to set zombies on map
        Random random = new Random();
        y = random.nextInt(5);
        locX = 811; //Screen resolution
        locY = y * 95 + 90;
        zombieMove = true;
        speed = 1;
        try {
            bufferedImage = ImageIO.read(new File("inputFile.png"));
            image = new ImageIcon("input.gif").getImage();
        } catch (IOException e) {
            System.err.println("We can't find image");
        }
    }

    public void move() {
        locX = (locX - speed); // 5 px/sec
    }
}
