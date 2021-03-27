package main;

import body.*;
import head.BackgroundListener;
import head.ClipBoard;
import head.Bot;
import head.Table;

public class Main {

    public static FloatingGui fg;
    public static Table tr;
    public static TableTester tt;

    public static void main(String[] args) {
//        new Formatador().setVisible(true);
        new MainScreen().setVisible(true);
        new BackgroundListener().start();

    }

}
