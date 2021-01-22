package khosro.map;

import javax.swing.*;
import java.util.ArrayList;

public class Map {
    private ArrayList<MapRow> mapRows;
    private ImageIcon background;
    private ImageIcon cardCherry;
    private ImageIcon cardFreeze;
    private ImageIcon cardPea;
    private ImageIcon cardFlower;
    private ImageIcon cardPotato;
    private ImageIcon shovel;
    private ImageIcon level;
    private ImageIcon menuBar;
    private ImageIcon menu;
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
        background = new ImageIcon("./src/khosro/model/res/images/Cards/bg.png");
        cardCherry = new ImageIcon("./src/khosro/model/res/images/Cards/card_cherrybomb.png");
        cardFreeze = new ImageIcon("./src/khosro/model/res/images/Cards/card_freezepeashooter.png");
        cardPea = new ImageIcon("./src/khosro/model/res/images/Cards/card_peashooter.png");
        cardFlower = new ImageIcon("./src/khosro/model/res/images/Cards/card_sunflower.png");
        cardPotato = new ImageIcon("./src/khosro/model/res/images/Cards/card_wallnut.png");
        shovel = new ImageIcon("./src/khosro/model/res/images/Cards/shovel.png");
        level = new ImageIcon("./src/khosro/model/res/images/Cards/level.png");
        menuBar = new ImageIcon("./src/khosro/model/res/images/Cards/menu_bar.png");
        menu = new ImageIcon("./src/khosro/model/res/images/Cards/menu.png");

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
