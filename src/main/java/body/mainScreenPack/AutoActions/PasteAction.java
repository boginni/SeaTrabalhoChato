/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package body.mainScreenPack.AutoActions;

import java.awt.Robot;
import java.awt.event.KeyEvent;


public class PasteAction extends KeyType {
    
    public PasteAction() {
        super(KeyEvent.VK_V, 0,2);
    }

    @Override
    public void run(Robot b) {
        b.keyPress(KeyEvent.VK_CONTROL);
        super.run(b); //To change body of generated methods, choose Tools | Templates.
        b.keyRelease(KeyEvent.VK_CONTROL);
    }

    @Override
    public String toString() {
        return "Paste Action";
    }
    
    
    
    
    
}
