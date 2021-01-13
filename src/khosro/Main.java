package khosro;

import khosro.handler.GameHandler;
import khosro.map.Map;
import khosro.pages.GamePage;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static final int FPS = 20;
    private static int cf = 85;
    private static int cc = 85;
    private static int cp = 85;
    private static int f = 85;
    private static int p = 85;
    private static int l = 0;
    private static long start2;
    private static long start3;
    private static long start4;
    private static long start5;
    private static long start6;
    private static long start7;


    public static void main(String[] args) {
        Map map = new Map();
        GamePage gamePage = new GamePage();
        GameHandler gameHandler = new GameHandler(map, gamePage);

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

        t.start();

    }

    private static void level() {
        if (System.currentTimeMillis() - start7 > 1000 && l < 205) {
            l++;
            start7 = System.currentTimeMillis();
        }

    }

    private static void check() {
        if (System.currentTimeMillis() - start2 > 100) {

            cf--;
            start2 = System.currentTimeMillis();

        }
        if (cf == 0)
            cf = 85;
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