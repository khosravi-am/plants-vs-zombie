package khosro.views;


import khosro.model.map.MapRow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class GamePage extends JFrame {
    private Image background, bullet, sun, cardCherry, cardFreeze, cardPea, cardFlower, cardPotato, shovel, level, menuBar, menu;
    private int sunNum = 0, time;
    private Image icon;
    private Boolean cardTime;
    private ArrayList<Image> plant;
    private Long start;

    public GamePage() {
        super("Plants vs. Zombie");
        try {
            icon = ImageIO.read(new File("./src/khosro/model/res/icon.png"));
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
        start = System.currentTimeMillis();
//        JLabel l=new JLabel(new ImageIcon("./images/sunflower.gif"));
//        l.setLayout(null);
//        l.setBounds(50,120,90,80);
//        setOpacity(1);
//        add(l);


    }


    public void update(Graphics g) {

    }

    public void paintBack(Graphics2D g2d) {
        g2d.drawImage(background, 0, 30, 1080, 735, null);
    }


    public void setRows(Graphics2D g2d, ArrayList<MapRow> mapRow) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 9; j++) {
                g2d.drawImage(mapRow.get(i).getMapHomes().get(j).getImage(), mapRow.get(i).getMapHomes().get(j).getX(), mapRow.get(i).getY(), mapRow.get(i).getMapHomes().get(j).getWidth(), mapRow.get(i).getHeight(), null);
                g2d.drawImage(mapRow.get(i).getImage(), -30, mapRow.get(i).getY() + 10, 90, mapRow.get(i).getHeight() - 60, null);

            }
    }

    public void paintMenu(Graphics2D g2d) {
        g2d.drawImage(menuBar, 20, 31, 695, 110, null);
        g2d.drawImage(shovel, 615, 36, 90, 75, null);
        g2d.drawImage(menu, 925, 30, 140, 40, null);
        g2d.drawImage(level, 800, 735, 220, 30, null);
        g2d.drawImage(cardFlower, 130, 40, 70, 85, null);
        g2d.drawImage(cardPea, 210, 40, 70, 85, null);
        g2d.drawImage(cardFreeze, 290, 40, 70, 85, null);
        g2d.drawImage(cardCherry, 370, 40, 70, 85, null);
        g2d.drawImage(cardPotato, 450, 40, 70, 85, null);
        g2d.setColor(Color.GREEN);
        g2d.setFont(g2d.getFont().deriveFont(30.0f));
        g2d.drawString("menu", 955, 50);
        g2d.setColor(Color.BLACK);
        g2d.drawString(String.valueOf(sunNum), 65, 130);

    }

    public void delayCardFlower(Graphics2D g2d, int i) {
        g2d.setColor(new Color(0f, 0f, 0f, .5f));
        g2d.fillRect(130, 40, 70, i);
    }

    public void paintLevel(Graphics2D g2d, int i) {
        g2d.setColor(new Color(0f, 1f, 0f, 1f));
        g2d.fillRect(812, 750, i, 5);
    }

    public Image getBullet() {
        return bullet;
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

    public void setMenu(Image menu) {
        this.menu = menu;
    }

    public void setCardPotato(Image cardPotato) {
        this.cardPotato = cardPotato;
    }

    public void setMenuBar(Image menuBar) {
        this.menuBar = menuBar;
    }

    public void delayCardCherry(Graphics2D g2d, int cc) {
        g2d.setColor(new Color(0f, 0f, 0f, .5f));
        g2d.fillRect(370, 40, 70, cc);
    }

    public void delayCardPea(Graphics2D g2d, int cp) {
        g2d.setColor(new Color(0f, 0f, 0f, .5f));
        g2d.fillRect(210, 40, 70, cp);
    }

    public void delayCardFreeze(Graphics2D g2d, int f) {
        g2d.setColor(new Color(0f, 0f, 0f, .5f));
        g2d.fillRect(290, 40, 70, f);
    }

    public void delayCardPotato(Graphics2D g2d, int p) {
        g2d.setColor(new Color(0f, 0f, 0f, .5f));
        g2d.fillRect(450, 40, 70, p);
    }

    public void paintMouseImg(Graphics2D g2d, Image image, int mouseX, int mouseY) {
        g2d.drawImage(image, mouseX, mouseY, 80, 100, null);
    }

    public void paintHomeImage(Graphics2D g2d, Image image, int x, int y) {
        if (y > 115)
            g2d.drawImage(image, x + 20, y + 10, 80, 95, null);
        else
            g2d.drawImage(image, x + 20, y + 30, 80, 95, null);
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */

}
