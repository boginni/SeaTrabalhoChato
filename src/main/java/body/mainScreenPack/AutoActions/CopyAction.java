/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package body.mainScreenPack.AutoActions;

import java.awt.Robot;
import java.awt.event.KeyEvent;


public class CopyAction extends KeyType {

    public CopyAction() {
        super(KeyEvent.VK_C, 0,2);
    }
    
    @Override
    public void run(Robot b){
        b.keyPress(KeyEvent.VK_CONTROL);
        super.run(b);
        b.keyRelease(KeyEvent.VK_CONTROL);
    }
    
    @Override
    public String toString() {
        return "Copy Action";
    }
    
    
    
    
}
