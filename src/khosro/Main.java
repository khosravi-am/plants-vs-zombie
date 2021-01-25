package khosro;

import khosro.controller.GameController;
import khosro.model.map.Map;
import khosro.views.*;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static final int FPS = 90;
    private static int cf = 85;
    private static int cc = 85;
    private static int cp = 85;
    private static int f = 85;
    private static int p = 85;
    private static int l = 0;
    public static Boolean card1 = false;
    public static Boolean card2 = false;
    public static Boolean card3 = false;
    public static Boolean card4 = false;
    public static Boolean card5 = false;
    public static Boolean mainMenu = true;
    private static long start2;
    private static long start3;
    private static long start4;
    private static long start5;
    private static long start6;
    private static long start7;


    public static void main(String[] args) {

        MainPage mainPage=new MainPage();
        while (!mainPage.getRunGame()) {
            try {
                long start = System.currentTimeMillis();
                mainPage.render();
                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0)
                    Thread.sleep(delay);

            } catch (InterruptedException ex) {
            }
        }


        // Initialize the global thread-pool
        // ThreadPool.init();

        // Show the game menu ...

        // After the player clicks 'PLAY' ...
        Thread t = new Thread() {

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
                Map map = new Map();
                GamePage gamePage = new GamePage();
                GameController gameHandler = new GameController(map, gamePage);

                boolean gameOver = false;
                start2 = System.currentTimeMillis();
                start3 = System.currentTimeMillis();
                start4 = System.currentTimeMillis();
                start5 = System.currentTimeMillis();
                start6 = System.currentTimeMillis();
                start7 = System.currentTimeMillis();
                while (!gameOver) {
                    try {
                        long start = System.currentTimeMillis();
                        //
                        gameHandler.render(cf, cc, cp, f, p, l);
                        //gameHandler.render2();
                        gameHandler.update();
                        //canvas.render(state);
                        gameOver = gameHandler.gameOver;
                        check2(gameHandler);
                        check();
                        level();
                        long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                        if (delay > 0)
                            Thread.sleep(delay);

                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        if (mainPage.getRunGame()) {
            mainPage.dispose();
            t.start();
        }

    }


    private static void check2(GameController gameHandler) {
        try {


            if (!gameHandler.getPlant().isUse() && gameHandler.getPlant().getClass().getSimpleName().equals("SunFlower") && card1) {
                card1 = false;
                if (cf == 0)
                    start2 = System.currentTimeMillis();
                cf = 85;
                gameHandler.setPlant(null);
            }
            if (!gameHandler.getPlant().isUse() && gameHandler.getPlant().getClass().getSimpleName().equals("Peashooter") && card2) {
                card2 = false;
                if (cp == 0)
                    start3 = System.currentTimeMillis();
                cp = 85;
                gameHandler.setPlant(null);
            }
            if (!gameHandler.getPlant().isUse() && gameHandler.getPlant().getClass().getSimpleName().equals("SnowPea") && card3) {
                card3 = false;
                if (f == 0)
                    start4 = System.currentTimeMillis();
                f = 85;
                gameHandler.setPlant(null);
            }
            if (!gameHandler.getPlant().isUse() && gameHandler.getPlant().getClass().getSimpleName().equals("Cherry") && card4) {
                card4 = false;
                if (cc == 0)
                    start5 = System.currentTimeMillis();
                cc = 85;
                gameHandler.setPlant(null);
            }
            if (!gameHandler.getPlant().isUse() && gameHandler.getPlant().getClass().getSimpleName().equals("Potato") && card5) {
                card5 = false;
                if (p == 0)
                    start6 = System.currentTimeMillis();
                p = 85;
                gameHandler.setPlant(null);
            }
        }catch (NullPointerException e){

        }

    }

    private static void level() {
        if (System.currentTimeMillis() - start7 > 4000 && l < 200) {
            l++;
            start7 = System.currentTimeMillis();
            System.out.println(l);
        }

    }

    private static void check() {
        if (System.currentTimeMillis() - start2 > 100 && !card1) {
            cf--;
            start2 = System.currentTimeMillis();

        }
        if (cf == 0)
            card1 = true;
        if (System.currentTimeMillis() - start3 > 150 && !card2) {
            cp--;
            start3 = System.currentTimeMillis();

        }
        if (cp == 0)
            card2 = true;
        if (System.currentTimeMillis() - start4 > 350 && !card4) {
            cc--;
            start4 = System.currentTimeMillis();

        }
        if (cc == 0)
            card4=true;
        if (System.currentTimeMillis() - start5 > 280 && !card5) {
            p--;
            start5 = System.currentTimeMillis();

        }
        if (p == 0)
            card5=true;
        if (System.currentTimeMillis() - start6 > 200 && !card3) {
            f--;
            start6 = System.currentTimeMillis();

        }
        if (f == 0)
            card3=true;
    }
}
