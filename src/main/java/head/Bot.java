/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package head;


import main.Main;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Bot {


    class HotKey{

        public HotKey(int id){
            curKey = KeyEvent.VK_ESCAPE;
            hotKeys[id] = this;
        }

        int oldKey = 0;
        int curKey;

        public HotKey update(int newKey) {
            oldKey = curKey;
            curKey = newKey;
            return this;
        }

        public boolean isFristUpdate() {
            return curKey == KeyEvent.VK_ESCAPE;
        }
    }

    HashMap<Integer, HotKey> indexedKey = new HashMap<>();
    HotKey[] hotKeys = new HotKey[7];

    Robot bot;
    
    public boolean onAction = false;
    public boolean ctrlMod = false;
    public boolean ctrlAction = false;
    public boolean shiftMod = false;
    public boolean shiftAction = false;
    public boolean onPause = false;

    int curRow = 0;
    int curCol = 0;

    boolean wantsCopy = true;

    public Bot() {
        for (int i = 0; i < 7; i++) {
            new HotKey(i);
        }

        try {
            bot = new Robot();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

    }

    public void keyEvent(boolean b, int i) {
        if (onAction) {
            return;
        }
        if (i == 29) {
            ctrlMod = b;
        }
        if (b) {
            onAction = true;

            if (i == 57421) {
                if (curRow + 1 < Main.tr.getRowCount()) {
                    Main.fg.setRow(++curRow);
                    curCol = 0;
                }
            }

            if (i == 57419) {
                if (curRow - 1 >= 0) {
                    Main.fg.setRow(--curRow);
                    curCol = 0;
                }
            }

            if (i >= 2 && i <= 6) {
                String str = Main.fg.getTable().getCell(curRow, i - 2);
                ClipBoard.setClipBoard(str, false);
                typeKey(KeyEvent.VK_BACK_SPACE);
                twoKeys(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
            }

            onAction = false;
        }
    }

    public void copyAction(String str, boolean newCopy) {
        if(newCopy && wantsCopy){
            setCell(str);
        }

    }

    public void setHotKey(int id, int newKey) {
        if(indexedKey.containsKey(newKey)){
            return;
        }

        indexedKey.put(newKey, hotKeys[id].update(newKey));
        if(hotKeys[id].isFristUpdate()){
            return;
        }
        indexedKey.remove(hotKeys[id].oldKey);
    }

    @Deprecated
    private void setCell(String str){
        Main.tr.setCell(curRow, curCol, str);
        System.out.println(String.format("Copied \"%s\" to cell[%d][%d]", str, curRow, curCol));
        curCol++;
        Main.tt.updateTable();
    }

    private void setCell(int row, int cell, String str){
        Main.tr.setCell(row, cell, str);
        System.out.println(String.format("Copied \"%s\" to cell[%d][%d]", str, row, cell));
        Main.tt.updateTable();
    }

    public void typeKey(int key) {
        bot.keyPress(key);
        bot.keyRelease(key);
    }

    public void twoKeys(int fristKey, int secondKey) {
        bot.keyPress(fristKey);
        typeKey(secondKey);
        bot.keyRelease(fristKey);
    }

    public void selectAll() {
        twoKeys(KeyEvent.VK_CONTROL, KeyEvent.VK_A);
    }

    public void pasteClipBoard() {
        twoKeys(KeyEvent.VK_CONTROL, KeyEvent.VK_V);
    }

    public void copyToClipBoard() {
        twoKeys(KeyEvent.VK_CONTROL, KeyEvent.VK_C);
    }


}
