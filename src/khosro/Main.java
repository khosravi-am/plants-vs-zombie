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
    public static Boolean card1=false;
    public static Boolean card2=false;
    public static Boolean card3=false;
    public static Boolean card4=false;
    public static Boolean card5=false;
    public static Boolean mainMenu=true;
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
        if (gameHandler.getSunflower().size()>0&&gameHandler.getSunflower().get(gameHandler.getSunflower().size()-1).isUse()) {
            card1 = false;
            if (cf==0)
                start2 = System.currentTimeMillis();
            cf=85;
        }
        if (gameHandler.getPea().size()>0&&gameHandler.getPea().get(gameHandler.getPea().size()-1).isUse()) {
            card2 = false;
            if (cp==0)
                start3 = System.currentTimeMillis();
            cp=85;
        }

    }

    private static void level() {
        if (System.currentTimeMillis() - start7 > 1000 && l < 205) {
            l++;
            start7 = System.currentTimeMillis();
        }

    }

    private static void check() {
        if (System.currentTimeMillis() - start2 > 100&&!card1) {
            cf--;
            start2 = System.currentTimeMillis();
        }
        if (cf == 0)
            card1=true;
        if (System.currentTimeMillis() - start3 > 130) {
            cp--;
            start3 = System.currentTimeMillis();

        }
        if (cp == 0)
            cp = 85;
        if (System.currentTimeMillis() - start4 > 350) {
            cc--;
            start4 = System.currentTimeMillis();

        }
        if (cc == 0)
            cc = 85;
        if (System.currentTimeMillis() - start5 > 280) {
            p--;
            start5 = System.currentTimeMillis();

        }
        if (p == 0)
            p = 85;
        if (System.currentTimeMillis() - start6 > 200) {
            f--;
            start6 = System.currentTimeMillis();

        }
        if (f == 0)
            f = 85;
    }
}
