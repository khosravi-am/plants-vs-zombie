package khosro.map;

import javax.swing.*;
import java.util.ArrayList;

public class Map {
    private ArrayList<MapRow> mapRows;
    private ImageIcon background, cardCherry, cardFreeze, cardPea, cardFlower, cardPotato, shovel, level, menuBar, menu;
    private int sunNum;

    public Map() {
        setImage();
        mapRows = new ArrayList<>();
        setMapRows(mapRows);
    }

    private void setMapRows(ArrayList<MapRow> mapRows) {
        for (int i = 0; i < 5; i++)
            mapRows.add(new MapRow(i));
    }

    public void setImage() {
            background = new ImageIcon("./images/bg.png");
            cardCherry = new ImageIcon("./images/card_cherrybomb.png");
            cardFreeze = new ImageIcon("./images/card_freezepeashooter.png");
            cardPea = new ImageIcon("./images/card_peashooter.png");
            cardFlower = new ImageIcon("./images/card_sunflower.png");
            cardPotato = new ImageIcon("./images/card_wallnut.png");
            shovel = new ImageIcon("./images/shovel.png");
            level = new ImageIcon("./images/level.png");
            menuBar = new ImageIcon("./images/menu_bar.png");
            menu = new ImageIcon("./images/menu.png");

    }

    public ArrayList<MapRow> getMapRows() {
        return mapRows;
    }

    public int getSunNum() {
        return sunNum;
    }

    public ImageIcon getBackground() {
        return background;
    }

    public ImageIcon getShovel() {
        return shovel;
    }

    public ImageIcon getCardCherry() {
        return cardCherry;
    }

    public ImageIcon getCardFreeze() {
        return cardFreeze;
    }

    public ImageIcon getCardFlower() {
        return cardFlower;
    }

    public ImageIcon getCardPea() {
        return cardPea;
    }

    public ImageIcon getCardPotato() {
        return cardPotato;
    }

    public ImageIcon getLevel() {
        return level;
    }

    public void setShovel(ImageIcon shovel) {
        this.shovel = shovel;
    }

    public void setSunNum(int sunNum) {
        this.sunNum = sunNum;
    }

    public ImageIcon getMenu() {
        return menu;
    }

    public ImageIcon getMenuBar() {
        return menuBar;
    }
}
