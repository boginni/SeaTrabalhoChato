/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package body.mainScreenPack.AutoActions;

import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import body.mainScreenPack.AutoActions.Properities.PanProp;
import body.mainScreenPack.AutoActions.Properities.panPropMouse;


public class MouseClick extends AutomatedAction {
    
    public static final int 
            ACTION_MOUSE_PRESS = 0,
            ACTION_MOUSE_RELEASE = 1,
            ACTION_MOUSE_CLICK = 2;
    
    int mX, mY, button, action;
    int delay;
    
    Point p1, p2;
    
    public MouseClick(int x, int y, int i, int ac, int delay){
        mX = x;
        mY = y;
        button = i;
        action = ac;
        this.delay = delay;
        System.out.println(ac);
    }

    //<editor-fold desc="getter-seter">

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }
    
    
    public void setDelay(int newDelay){
        delay = newDelay;
    }

    public int getDelay() {
        return delay;
    }
    
    public int getmX() {
        return mX;
    }

    public void setmX(int mX) {
        this.mX = mX;
    }

    public int getmY() {
        return mY;
    }

    public void setmY(int mY) {
        this.mY = mY;
    }

    public int getButton() {
        return button;
    }

    public void setButton(int button) {
        this.button = button;
    }
    
    
    public Point getP1() {
        return p1;
    }

    public void setP1(int x, int y) {
        this.p1 = new Point(x, y);
    }
    
    public Point getP2() {
        return p2;
    }

    public void setP2(int x, int y) {
        this.p2 = new Point(x, y);
    }
    
    //</editor-fold>
    
    @Override
    public void run(Robot b) {
        
        b.mouseMove(mX, mY);
        if(action == ACTION_MOUSE_PRESS || action == ACTION_MOUSE_CLICK)
            b.mousePress(InputEvent.getMaskForButton(button));
        try {
            if(action == ACTION_MOUSE_CLICK)
                Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(action == ACTION_MOUSE_RELEASE || action == ACTION_MOUSE_CLICK)
            b.mouseRelease(InputEvent.getMaskForButton(button));
    }
    
    @Override
    public String toString() {
        String name = getClass().getName();
        name = name.substring(20);

        return String.format("%s: p(%d,%d) b(%d)", name, mX,mY,button);
    }

    @Override
    public PanProp getPanel() {
        return new panPropMouse();
    }

    
    
    
    
}
