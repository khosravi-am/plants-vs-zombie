package khosro.controller;

import khosro.model.User;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * handel file operations
 */
public class Save {
    private static String fileName = "";
    // Write objects to file


    public static void saveGame(User user) {

        try {
            File file=new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }

            FileOutputStream f = new FileOutputStream(file,false);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(user);
            System.out.println("File already exists.");

            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void setFileName() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd,HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        String s=dtf.format(now);
        fileName = "./game/"+ s+".txt";
    }

    public static User loadGame(String fName){
        fileName="./game/"+fName;
        User user=null;
        try {


            FileInputStream fi = new FileInputStream(new File(fileName));
            ObjectInputStream oi = new ObjectInputStream(fi);
            user=(User) oi.readObject();
            oi.close();
            fi.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }
}
