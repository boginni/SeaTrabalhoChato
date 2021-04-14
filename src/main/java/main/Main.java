package main;

import body.mainScreenPack.MainFrame;
import head.BackgroundListener;

public class Main {

    public final static String VERSION = "0.1.16";

    public static void main(String[] args) {
//        new Formatador().setVisible(true);
       new MainFrame().setVisible(true);
       new BackgroundListener().start();

    }

}
