package khosro.controller;

import khosro.Main;
import khosro.model.component.Sun;
import khosro.model.component.plant.*;
import khosro.model.component.zombie.Zombie;
import khosro.model.component.zombie.Zombies;
import khosro.model.map.Map;
import khosro.model.map.MapHome;
import khosro.views.GamePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameController implements MouseListener, MouseMotionListener {
    private Boolean clickPea = false;
    private Boolean clickSunflower = false;
    private Boolean gameOver = false;
    private Boolean clickFreeze = false;
    private Boolean clickPotato = false;
    private Boolean clickCherry = false;
    private Boolean cherry = false;
    private Boolean over = false;
    private Boolean resume = false;
    private Boolean save = false;
    private Boolean exit = false;
    private Boolean restart = false;
    private Map map;
    private Plants plant;
    private Sun sun;
    private Zombies zombie;
    private Zombie zombies;
    private Graphics2D g2d;
    private GamePage gamePage;
    private ExecutorService executorService;

    Long start, gameStart, t;
    private int mouseX, mouseY, round = 0, type = 1;
    /*private BufferedImage image;*/

    private BufferStrategy bufferStrategy;
    private boolean clickShovel = false;
    private boolean car[] = {false, false, false, false, false};
    private long startCar;
    private boolean win = false;
    private boolean clickMenu = false;

    public GameController(Map map, GamePage gamePage, Zombies zombie) {
        this.map = map;
        this.zombie = zombie;
        this.gamePage = gamePage;
        start = System.currentTimeMillis();
        gameStart = System.currentTimeMillis();
        gamePage.addMouseListener(this);
        gamePage.addMouseMotionListener(this);
        executorService = Executors.newFixedThreadPool(10);

        setImage();
        initBufferStrategy();
        //(addMouseListener();
    }

    private void addMouseListener() {
      /*  for (int i=0;i<5;i++)
            for (int j=0;j<9;j++)
      */          //map.getMapRows().get(i).getMapHomes().get(j).addMouseListener(this);
    }


    /**
     * This must be called once after the JFrame is shown:
     * frame.setVisible(true);
     * and before any rendering is started.
     */
    public void initBufferStrategy() {
        // Triple-buffering
        gamePage.createBufferStrategy(3);
        bufferStrategy = gamePage.getBufferStrategy();
        g2d = (Graphics2D) bufferStrategy.getDrawGraphics();

    }


    /**
     * Game rendering with triple-buffering using BufferStrategy.
     *
     * @param cf
     * @param cc
     * @param cp
     * @param f
     * @param p
     * @param l
     */
    public void render(int cf, int cc, int cp, int f, int p, int l) {
        // Render single frame
        do {
            // The following loop ensures that the contents of the drawing buffer
            // are consistent in case the underlying surface was recreated
            do {
                // Get a new graphics context every time through the loop
                // to make sure the strategy is validated
                g2d = (Graphics2D) bufferStrategy.getDrawGraphics();
                try {
                    if (clickMenu) {
                        start = System.currentTimeMillis();
                        gameStart = System.currentTimeMillis();
                        g2d.drawImage(new ImageIcon("./src/khosro/model/res/pause.png").getImage(), 300, 100, 450, 550, null);
                        g2d.setFont(g2d.getFont().deriveFont(45.0f));
                        if (!resume)
                            g2d.setColor(Color.black);
                        else g2d.setColor(Color.green);
                        g2d.drawString("Back to game", 385, 600);

                        g2d.setFont(g2d.getFont().deriveFont(25.0f));
                        if (!save)
                            g2d.setColor(Color.black);
                        else g2d.setColor(Color.green);
                        g2d.drawString("Restart the game", 425, 410);

                        if (!restart)
                            g2d.setColor(Color.black);
                        else g2d.setColor(Color.green);
                        g2d.drawString("Restart the game", 425, 410);

                        if (!save)
                            g2d.setColor(Color.black);
                        else
                            g2d.setColor(Color.green);
                        g2d.drawString("Save the game", 440, 457);
                        if (!exit)
                            g2d.setColor(Color.black);
                        else
                            g2d.setColor(Color.green);

                        g2d.drawString("Main menu", 460, 505);
                        continue;
                    }
                    if (over) {
                        g2d.drawImage(new ImageIcon("./src/khosro/model/res/gameOver.jpg").getImage(), 0, 0, 1080, 770, null);
                        g2d.setColor(Color.GREEN);
                        g2d.setFont(g2d.getFont().deriveFont(60.0f));
                        g2d.drawString(String.valueOf(gamePage.getSunNum()), 600, 730);
                        if (System.currentTimeMillis() - start > 10000)
                            gameOver = true;
                    } else if (win && !map.getMapRows().get(0).getHaveZombie() && !map.getMapRows().get(1).getHaveZombie() && !map.getMapRows().get(2).getHaveZombie() && !map.getMapRows().get(3).getHaveZombie() && !map.getMapRows().get(4).getHaveZombie()) {
                        g2d.drawImage(new ImageIcon("./src/khosro/model/res/background.jpg").getImage(), 0, 0, 1080, 770, null);
                        g2d.setColor(Color.BLACK);
                        g2d.setFont(g2d.getFont().deriveFont(90.0f));
                        System.out.println("bordi");
                        g2d.drawString("WIN", 600, 430);

                        if (System.currentTimeMillis() - start > 60000)
                            gameOver = true;
                    } else {
                        if (l == 6)
                            round = 1;
                        if (l == 56)
                            round = 2;
                        if (l == 117)
                            round = 3;
                        gamePage.paintBack(g2d);
                        gamePage.setRows(g2d, map.getMapRows());
                        gamePage.paintMenu(g2d);
                        gamePage.delayCardFlower(g2d, cf);
                        gamePage.delayCardCherry(g2d, cc);
                        gamePage.delayCardPea(g2d, cp);
                        gamePage.delayCardFreeze(g2d, f);
                        gamePage.delayCardPotato(g2d, p);
                        gamePage.paintLevel(g2d, l);

                        if (clickShovel) {
                            gamePage.setClickShovel(true);
                            gamePage.paintMouseImg(g2d, map.getShovel().getImage(), mouseX - 40, mouseY - 80);
                            clickSunflower = false;
                            clickPea = false;
                            clickFreeze = false;
                            clickCherry = false;
                            clickPotato = false;
                        }
                        if (clickSunflower) {
                            paintPlants(new SunFlower());
                            clickSunflower = false;
                        }
                        if (clickPea) {
                            paintPlants(new Peashooter());
                            clickPea = false;
                        }
                        if (clickFreeze) {
                            paintPlants(new SnowPea());
                            clickFreeze = false;
                        }
                        if (clickCherry) {
                            paintPlants(new Cherry());
                            clickCherry = false;
                        }
                        if (clickPotato) {
                            paintPlants(new Potato());
                            clickPotato = false;
                        }

                        if (System.currentTimeMillis() - start > 25000) {
                            start = System.currentTimeMillis();
                            executorService.execute(sun = new Sun());
                        }


                        if (plant != null && plant.isUse())
                            gamePage.paintMouseImg(g2d, plant.getImage().getImage(), mouseX - 50, mouseY - 80);

                        for (int i = 0; i < 5; i++) {
                            map.getMapRows().get(i).setHaveZombie();
                            for (int j = 0; j < 9; j++) {
                                if (map.getMapRows().get(i).getMapHomes().get(j).getHaveZombie()) {
                                    if (map.getMapRows().get(i).getHaveCar() && map.getMapRows().get(i).getMapHomes().get(j).getZombie().getX() - 30 < map.getMapRows().get(i).getX())
                                        car[i] = true;

                                    checkAndInsertZombie(map.getMapRows().get(i).getMapHomes().get(j));
                                }

                                if (map.getMapRows().get(i).getMapHomes().get(j).isPor())
                                    checkAndInsert(map.getMapRows().get(i).getMapHomes().get(j));

                                if ((map.getMapRows().get(i).getMapHomes().get(j).getHaveBullet() || map.getMapRows().get(i).getMapHomes().get(j).getHaveSnow()))
                                    checkAndInsertBullet(map.getMapRows().get(i).getMapHomes().get(j));

                            }


                        }


                        if (round == 1 && System.currentTimeMillis() - gameStart > 30000) {
                            zombieBede();
                            gameStart = System.currentTimeMillis();
                        }
                        if (round == 2 && !win) {
                            if (System.currentTimeMillis() - gameStart > 30000) {
                                zombieBede();
                                gameStart = System.currentTimeMillis();
                                t = System.currentTimeMillis();
                            }
                            if (t != null && System.currentTimeMillis() - t > 4000) {
                                zombieBede();
                                t = System.currentTimeMillis() + 30000;
                            }
                        }
                        if (round == 3 && !win) {
                            if (System.currentTimeMillis() - gameStart > 25000) {
                                zombieBede();
                                t = System.currentTimeMillis();
                                gameStart = System.currentTimeMillis();
                            }
                            if (t != null && System.currentTimeMillis() - t > 4000) {
                                zombieBede();
                                t = System.currentTimeMillis() + 25000;
                            }
                        }


                        if ((car[0] || car[1] || car[2] || car[3] || car[4]))
                            checkAndInsertCar();

                        paintSun(this.sun, null);
                    }
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

    private void checkAndInsertCar() {
        for (int i = 0; i < 5; i++)
            if (car[i]) {
                gamePage.drawImage(g2d, new ImageIcon("./src/khosro/model/res/lawn_mower.gif").getImage(), map.getMapRows().get(i).getX(), map.getMapRows().get(i).getY() + 20);
                map.getMapRows().get(i).setHaveCar(false);
                map.getMapRows().get(i).setX(map.getMapRows().get(i).getX() + 3);
                if (map.getMapRows().get(i).getX() > 1080)
                    car[i] = false;
            }
    }

    private void zombieBede() {
        zombies = zombie.checkConBede(gamePage.getSunNum(), map.getMapRows(), round);
        if (zombies == null) {
            win = true;
            System.out.println(win);
            start = System.currentTimeMillis();
        }
        if (zombies != null) {
            zombies.setRun(true);
            map.getMapRows().get(zombies.getRow()).getMapHomes().get(8).setZombie(zombies);
        }
    }

    private void checkAndInsertBullet(MapHome mapHome) {
        if (mapHome.getHaveZombie()) {
            if (mapHome.getHaveBullet()) {
                mapHome.getBullet().setRun(false);
                mapHome.getZombie().setLife(mapHome.getZombie().getLife() - mapHome.getBullet().getPower());
                mapHome.setBullet(null);
            }
            if (mapHome.getHaveSnow()) {
                mapHome.getSnowBullet().setRun(false);
                mapHome.getZombie().setLife(mapHome.getZombie().getLife() - mapHome.getSnowBullet().getPower());
                mapHome.getZombie().setSpeed(1);
                mapHome.setSnowBullet(null);
            }

        } else {
            if (mapHome.getHaveBullet()) {
                gamePage.drawImage(g2d, mapHome.getBullet().getImage().getImage(), mapHome.getBullet().getX(), mapHome.getBullet().getY());
                if (mapHome.getX2() + 65 < mapHome.getBullet().getX()) {
                    if (mapHome.getCount() != 9) {
                        map.getMapRows().get(mapHome.getRow()).getMapHomes().get(mapHome.getCount()).setBullet(mapHome.getBullet());
                    }
                    if (mapHome.getCount() == 9)
                        mapHome.getBullet().setRun(false);
                    mapHome.setBullet(null);
                }
                /*if (mapHome.getBullet().getRun() && System.currentTimeMillis() - mapHome.getZombie().getBornTime() > 50) {
                    mapHome.getZombie().setRun(true);
                    mapHome.getZombie().setX(mapHome.getZombie().getX() - mapHome.getZombie().getSpeed());
                    mapHome.getZombie().setBornTime(System.currentTimeMillis());
                }*/
            }
            if (mapHome.getHaveSnow()) {
                gamePage.drawImage(g2d, mapHome.getSnowBullet().getImage().getImage(), mapHome.getSnowBullet().getX(), mapHome.getSnowBullet().getY());
                if (mapHome.getX2() + 65 < mapHome.getSnowBullet().getX()) {
                    if (mapHome.getCount() != 9)
                        map.getMapRows().get(mapHome.getRow()).getMapHomes().get(mapHome.getCount()).setSnowBullet(mapHome.getSnowBullet());
                    if (mapHome.getCount() == 9)
                        mapHome.getSnowBullet().setRun(false);
                    mapHome.setSnowBullet(null);

                }
            }
        }


    }

    private void checkAndInsertZombie(MapHome mapHome) {
        if (mapHome.getZombie().getLife() <= 0)
            mapHome.getZombie().setLive(false);


        if (mapHome.getZombie().getX() < map.getMapRows().get(mapHome.getRow()).getX() && car[mapHome.getRow()])
            mapHome.getZombie().setLive(false);

        if (!mapHome.getZombie().getLive()) {
            mapHome.setZombie(null);
            return;
        }

        if (mapHome.getZombie().getLife() <= mapHome.getZombie().getHeadLife())
            mapHome.getZombie().setGif(new ImageIcon("./src/khosro/model/res/z1.gif"));
        gamePage.paintZombie(g2d, mapHome.getZombie().getGif().getImage(), mapHome.getZombie().getX(), mapHome.getZombie().getY());
        if (mapHome.getZombie().getRun() && System.currentTimeMillis() - mapHome.getZombie().getBornTime() > 100) {
            mapHome.getZombie().setRun(true);
            if (type == 2 && !mapHome.getZombie().getClass().getSimpleName().equals("NormZombie"))
                mapHome.getZombie().setSpeed(4);
            mapHome.getZombie().setX(mapHome.getZombie().getX() - mapHome.getZombie().getSpeed());
            mapHome.getZombie().setBornTime(System.currentTimeMillis());
        }
        if (mapHome.isPor() && mapHome.getX() + 60 > mapHome.getZombie().getX() && mapHome.getZombie().getRun()) {
            mapHome.getZombie().setRun(false);
            // if (System.currentTimeMillis() - mapHome.getPlant().getBornTime() > 150) {
            //   mapHome.getPlant().setBornTime(System.currentTimeMillis());
            //}
        }
        //System.out.println(mapHome.getZombie().getGif().getImage());
        if (mapHome.getX() + 13 > mapHome.getZombie().getX() && mapHome.getCount() >= 2) {
            map.getMapRows().get(mapHome.getZombie().getRow()).getMapHomes().get(mapHome.getCount() - 2).setZombie(mapHome.getZombie());
            mapHome.setZombie(null);
        }

        if (mapHome.getCount() == 1 && mapHome.getX() - 110 > mapHome.getZombie().getX()) {
            mapHome.setZombie(null);
            over = true;
            start = System.currentTimeMillis();
        }


    }


    private void paintSun(Plants sun, Plants sun2) {


        try {
            setSunNum(sun);
            if (((Sun) sun).getOver()) sun = null;
            this.sun = (Sun) sun;
            gamePage.drawImage(g2d, sun.getImage().getImage(), sun.getX(), sun.getY());
            //System.out.println("n");
        } catch (NullPointerException e) {

        }
    }

    private void setSunNum(Plants sun) {
        if (((Sun) sun).getSun()) {
            ((Sun) sun).setSun(false);
            ((Sun) sun).setClick(false);
            ((Sun) sun).setOver(true);
            gamePage.setSunNum(gamePage.getSunNum() + 25);

        }
    }

    private void paintPlants(Plants plant) {
        plant.setUse(true);
        this.plant = plant;
        gamePage.paintMouseImg(g2d, plant.getImage().getImage(), mouseX - 50, mouseY - 80);
    }

    public void setPlant(Plants plant) {
        this.plant = plant;
    }

    private void setImage() {
        gamePage.setBackground(map.getBackground().getImage());
        gamePage.setMenu(map.getMenu().getImage());
        gamePage.setMenuBar(map.getMenuBar().getImage());
        gamePage.setShovel(map.getShovel().getImage());
        gamePage.setCardFlower(map.getCardFlower().getImage());
        gamePage.setLevel(map.getLevel().getImage());
        gamePage.setCardCherry(map.getCardCherry().getImage());
        gamePage.setCardFreeze(map.getCardFreeze().getImage());
        gamePage.setCardPea(map.getCardPea().getImage());
        gamePage.setCardPotato(map.getCardPotato().getImage());
        //gamePage.setVisible(true);
    }

    private void checkAndInsert(MapHome mapHome) {
        if (mapHome.getHaveZombie() && !mapHome.getZombie().getRun())
            if (System.currentTimeMillis() - mapHome.getPlant().getBornTime() > 200) {
                System.out.println(mapHome.getPlant().getLife());
                if (type == 2 && !mapHome.getZombie().getClass().getSimpleName().equals("ConeHeadZombie"))
                    mapHome.getZombie().setPower(3);
                if (type == 2 && !mapHome.getZombie().getClass().getSimpleName().equals("BucketHeadZombie"))
                    mapHome.getZombie().setPower(5);

                mapHome.getPlant().setLife(mapHome.getPlant().getLife() - mapHome.getZombie().getPower());
                mapHome.getPlant().setBornTime(System.currentTimeMillis());
            }

        if (mapHome.getPlant().getLife() <= 0)
            mapHome.getPlant().setLive(false);
        if (!mapHome.getPlant().isLive()) {
            if (mapHome.getPlant().getClass().getSimpleName().equals("Cherry"))
                cherry = false;
            mapHome.getPlant().setRunnable(null);
            mapHome.setPlant(null);
            if (mapHome.getHaveZombie())
                mapHome.getZombie().setRun(true);
            return;
        }
        if (mapHome.getPlant().getClass().getSimpleName().equals("Cherry")) {
            gamePage.paintHomeImage(g2d, mapHome.getPlant().getGif().getImage(), mapHome.getX() - 130, mapHome.getY() - 130, 350, 350);
            if (!cherry) {
                mapHome.getPlant().setBornTime(System.currentTimeMillis());
                ((Cherry) mapHome.getPlant()).explode(mapHome, map);
                cherry = true;
            }

        } else gamePage.paintHomeImage(g2d, mapHome.getPlant().getGif().getImage(), mapHome.getX(), mapHome.getY());

        if (mapHome.getPlant().getClass().getSimpleName().equals("SunFlower")) {
            if (type == 2)
                mapHome.getPlant().setPreparingTime(20000);
            if (System.currentTimeMillis() - mapHome.getPlant().getBornTime() > mapHome.getPlant().getPreparingTime() / 2) {
                if (((SunFlower) mapHome.getPlant()).getHaveSun()) {
                    ((SunFlower) mapHome.getPlant()).setHaveSun(false);
                    mapHome.getPlant().setBornTime(System.currentTimeMillis());
                    return;
                }
                if (System.currentTimeMillis() - mapHome.getPlant().getBornTime() > mapHome.getPlant().getPreparingTime() && !((SunFlower) mapHome.getPlant()).getHaveSun()) {
                    ((SunFlower) mapHome.getPlant()).setHaveSun(true);

                    mapHome.getPlant().setBornTime(System.currentTimeMillis());
                }
            }
            if (((SunFlower) mapHome.getPlant()).getHaveSun())
                gamePage.drawImage(g2d, ((SunFlower) mapHome.getPlant()).getSun().getImage().getImage(), ((SunFlower) mapHome.getPlant()).getSun().getX(), ((SunFlower) mapHome.getPlant()).getSun().getY());
            else if (((SunFlower) mapHome.getPlant()).getSun().getClick()) {
                gamePage.drawImage(g2d, ((SunFlower) mapHome.getPlant()).getSun().getImage().getImage(), ((SunFlower) mapHome.getPlant()).getSun().getX(), ((SunFlower) mapHome.getPlant()).getSun().getY());
                setSunNum(((SunFlower) mapHome.getPlant()).getSun());

            }

        }
        // System.out.println(map.getMapRows().get(mapHome.getRow()).getHaveZombie());
        //   System.out.println(mapHome.getRow());
        if (map.getMapRows().get(mapHome.getRow()).getHaveZombie() && mapHome.getPlant().getClass().getSimpleName().equals("Peashooter")) {
            if (!((Peashooter) mapHome.getPlant()).getPeaBullet().getRun() && System.currentTimeMillis() - ((Peashooter) mapHome.getPlant()).getPeaBullet().getBornTime() > 900) {
                mapHome.setBullet(((Peashooter) mapHome.getPlant()).getPeaBullet());
                executorService.execute(((Peashooter) mapHome.getPlant()).shoot());
            }
            //gamePage.drawImage(g2d, ((Peashooter) mapHome.getPlant()).getPeaBullet().getImage().getImage(), ((Peashooter) mapHome.getPlant()).getPeaBullet().getX(), ((Peashooter) mapHome.getPlant()).getPeaBullet().getY());


        }

        if (map.getMapRows().get(mapHome.getRow()).getHaveZombie() && mapHome.getPlant().getClass().getSimpleName().equals("SnowPea")) {
            if (!((SnowPea) mapHome.getPlant()).getSnowBullet().getRun() && System.currentTimeMillis() - ((SnowPea) mapHome.getPlant()).getSnowBullet().getBornTime() > 900) {
                mapHome.setSnowBullet(((SnowPea) mapHome.getPlant()).getSnowBullet());
                executorService.execute(((SnowPea) mapHome.getPlant()).shoot());
            }
            //gamePage.drawImage(g2d, ((Peashooter) mapHome.getPlant()).getPeaBullet().getImage().getImage(), ((Peashooter) mapHome.getPlant()).getPeaBullet().getX(), ((Peashooter) mapHome.getPlant()).getPeaBullet().getY());


        }


    }


    public Boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Boolean getMenu() {
        return clickMenu;
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("x:" + e.getX() + " y:" + e.getY());

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (clickMenu && e.getX() > 351 && e.getY() > 556 && e.getX() < 708 && e.getY() < 631)
            clickMenu = false;
        if (e.getX() > 620 && e.getY() > 40 && e.getX() < 696 && e.getY() < 110)
            clickShovel = true;
        else clickShovel = false;
        if (e.getX() > 930 && e.getY() > 30 && e.getX() < 1055 && e.getY() < 60)
            clickMenu = true;
        if (e.getX() > 132 && e.getY() > 40 && e.getX() < 196 && e.getY() < 125 && Main.card[0] && gamePage.getSunNum() >= 50)
            clickSunflower = true;
        if (e.getX() > 210 && e.getY() > 40 && e.getX() < 275 && e.getY() < 125 && Main.card[1] && gamePage.getSunNum() >= 100)
            clickPea = true;
        if (e.getX() > 290 && e.getY() > 40 && e.getX() < 360 && e.getY() < 125 && Main.card[2] && gamePage.getSunNum() >= 175)
            clickFreeze = true;
        if (e.getX() > 370 && e.getY() > 40 && e.getX() < 440 && e.getY() < 125 && Main.card[3] && gamePage.getSunNum() >= 150)
            clickCherry = true;
        if (e.getX() > 450 && e.getY() > 40 && e.getX() < 515 && e.getY() < 125 && Main.card[4] && gamePage.getSunNum() >= 50)
            clickPotato = true;
        if (e.getX() > 45 && e.getY() > 137 && e.getX() < 1042 && e.getY() < 734)
            checkHome(e);
        if (sun != null && e.getX() > sun.getX() && e.getY() > sun.getY() && e.getX() < sun.getX() + 50 && e.getY() < sun.getY() + 50)
            executorService.execute(sun.dispose());


    }

    private void checkHome(MouseEvent e) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 9; j++)
                if (e.getX() > map.getMapRows().get(i).getMapHomes().get(j).getX() && e.getY() > map.getMapRows().get(i).getMapHomes().get(j).getY() && e.getX() < map.getMapRows().get(i).getMapHomes().get(j).getX2() && e.getY() < map.getMapRows().get(i).getMapHomes().get(j).getY2()) {
                    // System.out.println(map.getMapRows().get(i).getMapHomes().get(j).getPlant().getClass().getSimpleName());
                    try {
                        if (gamePage.getClickShovel()) {
                            map.getMapRows().get(i).getMapHomes().get(j).setPlant(null);
                            gamePage.setClickShovel(false);
                            break;
                        }
                        if (!map.getMapRows().get(i).getMapHomes().get(j).isPor()) {
                            if (plant.isUse()) {
                                plant.setUse(false);
                                plant.setX(map.getMapRows().get(i).getMapHomes().get(j).getX());
                                plant.setY(map.getMapRows().get(i).getMapHomes().get(j).getY());
                                map.getMapRows().get(i).getMapHomes().get(j).setPlant(plant);
                                map.getMapRows().get(i).getMapHomes().get(j).getPlant().setBornTime(System.currentTimeMillis());
                                gamePage.setSunNum(gamePage.getSunNum() - map.getMapRows().get(i).getMapHomes().get(j).getPlant().getCost());


                            }
                        } else if (map.getMapRows().get(i).getMapHomes().get(j).getPlant().getClass().getSimpleName().equals("SunFlower") && ((SunFlower) map.getMapRows().get(i).getMapHomes().get(j).getPlant()).getHaveSun()) {
                            ((SunFlower) map.getMapRows().get(i).getMapHomes().get(j).getPlant()).setHaveSun(false);
                            ((SunFlower) map.getMapRows().get(i).getMapHomes().get(j).getPlant()).getSun().setClick(true);
                            map.getMapRows().get(i).getMapHomes().get(j).getPlant().setBornTime(System.currentTimeMillis());
                            executorService.execute(((SunFlower) map.getMapRows().get(i).getMapHomes().get(j).getPlant()).getSun().dispose());

                        }

                        break;
                    } catch (NullPointerException n) {
                        System.out.println(n);
                    }
                }
    }

    public Boolean getClickSunflower() {
        return clickSunflower;
    }


    public Plants getPlant() {
        return plant;
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        //checkHome(e);
    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  {@code MOUSE_DRAGGED} events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * {@code MOUSE_DRAGGED} events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if (clickMenu && e.getX() > 418 && e.getY() > 381 && e.getX() < 633 && e.getY() < 422)
            restart = true;
        else restart = false;
        if (clickMenu && e.getX() > 418 && e.getY() > 431 && e.getX() < 633 && e.getY() < 471)
            save = true;
        else save = false;
        if (clickMenu && e.getX() > 418 && e.getY() > 479 && e.getX() < 633 && e.getY() < 519)
            exit = true;
        else exit = false;
        if (clickMenu && e.getX() > 351 && e.getY() > 556 && e.getX() < 708 && e.getY() < 631)
            resume = true;
        else resume = false;


    }
}
