package khosro.pages;

import khosro.map.MapRow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GamePage extends JFrame {
    private Image background, bullet, sun, cardCherry, cardFreeze, cardPea, cardFlower, cardPotato, shovel, level;
    private int sunNum = 0, time;
    private Image icon;
    private ArrayList<Image> car;
    private ArrayList<MapRow> mapRow;
    private Boolean cardTime;
    private ArrayList<Image> plant;
    private BufferStrategy bufferStrategy;
    Graphics2D g2d;


    public void initBufferStrategy() {
        // Triple-buffering
        createBufferStrategy(3);
        bufferStrategy = getBufferStrategy();

    }


    public GamePage() {
        super("Plants vs. Zombie");
        try {
            icon = ImageIO.read(new File("./images/icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.setIconImage(icon);
        super.setSize(1080, 770);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setLocationRelativeTo(null);
        super.setLayout(null);
        super.setResizable(false);
        super.setVisible(true);
        initBufferStrategy();
//        JLabel l=new JLabel(new ImageIcon("./images/sunflower.gif"));
//        l.setLayout(null);
//        l.setBounds(50,120,90,80);
//        setOpacity(1);
//        add(l);


    }


    public void paint2() {
        // Render single frame
        do {
            // The following loop ensures that the contents of the drawing buffer
            // are consistent in case the underlying surface was recreated
            do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                g2d = (Graphics2D) bufferStrategy.getDrawGraphics();
                try {
                    g2d.drawImage(background, 0, 30, 1080, 735, null);
                    g2d.drawImage(mapRow.get(0).getMapHomes().get(0).getImage(), 15, 90, 146, 157, null);
                    g2d.drawImage(cardFlower, 35, 109, 80, 70, this);

                } finally {
                    // Dispose the graphics
                    g2d.dispose();
                }
                // Repeat the rendering if the drawing buffer contents were restored
            } while (bufferStrategy.contentsRestored());

            // Display the buffer
            bufferStrategy.show();
            // Tell the system to do the drawing NOW;
            // otherwise it can take a few extra ms and will feel jerky!
            Toolkit.getDefaultToolkit().sync();

            // Repeat the rendering if the drawing buffer was lost
        } while (bufferStrategy.contentsLost());
    }


    private void setRows(Graphics2D g2d) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 9; j++)
                g2d.drawImage(mapRow.get(i).getMapHomes().get(j).getImage(), mapRow.get(i).getMapHomes().get(j).getX1(), mapRow.get(i).getY1(), null);
    }

    public void setMapRow(ArrayList<MapRow> mapRow) {
        this.mapRow = mapRow;

    }

    public Image getBullet() {
        return bullet;
    }

    public ArrayList<Image> getCar() {
        return car;
    }

    public Image getSun() {
        return sun;
    }

    public ArrayList<Image> getPlant() {
        return plant;
    }

    public Image getShovel() {
        return shovel;
    }

    public int getSunNum() {
        return sunNum;
    }

    public int getTime() {
        return time;
    }

    public void setBullet(Image bullet) {
        this.bullet = bullet;
    }

    public void setCar(ArrayList<Image> car) {
        this.car = car;
    }

    public void setShovel(Image shovel) {
        this.shovel = shovel;
    }

    public void setCardCherry(Image cardCherry) {
        this.cardCherry = cardCherry;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public void setCardFlower(Image cardFlower) {
        this.cardFlower = cardFlower;
    }

    public void setCardFreeze(Image cardFreeze) {
        this.cardFreeze = cardFreeze;
    }

    public void setCardPea(Image cardPea) {
        this.cardPea = cardPea;
    }

    public void setLevel(Image level) {
        this.level = level;
    }

    public void paint3() {
        do {
            // The following loop ensures that the contents of the drawing buffer
            // are consistent in case the underlying surface was recreated
            do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                g2d = (Graphics2D) bufferStrategy.getDrawGraphics();
                try {
                    //g2d.drawImage(background, 0, 30, 1080, 735, null);
                    //g2d.drawImage(mapRow.get(0).getMapHomes().get(0).getImage(), 15, 90, 146, 157, null);
                    g2d.drawImage(cardFlower, 135, 209, 80, 70, this);

                } finally {
                    // Dispose the graphics
                    g2d.dispose();
                }
                // Repeat the rendering if the drawing buffer contents were restored
            } while (bufferStrategy.contentsRestored());

            // Display the buffer
            bufferStrategy.show();
            // Tell the system to do the drawing NOW;
            // otherwise it can take a few extra ms and will feel jerky!
            Toolkit.getDefaultToolkit().sync();

            // Repeat the rendering if the drawing buffer was lost
        } while (bufferStrategy.contentsLost());
    }
}
