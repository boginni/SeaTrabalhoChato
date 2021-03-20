package main;

import body.FloatingGui;
import body.FloatingGui2;
import body.MainScreen;
import body.TableTester;
import head.BackgroundListener;
import head.ClipBoard;
import head.Bot;
import head.Table;

public class Main {

    public static FloatingGui fg;
    public static Table tr;
    public static TableTester tt;

    public static void main(String[] args) {

        new MainScreen().setVisible(true);
        new BackgroundListener().start();
        if (true) {
            return;
        }
        new FloatingGui2();
    }

}
