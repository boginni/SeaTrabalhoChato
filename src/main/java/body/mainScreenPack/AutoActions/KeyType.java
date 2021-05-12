/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package body.mainScreenPack.AutoActions;

import body.mainScreenPack.AutoActions.Properities.PanProp;
import body.mainScreenPack.AutoActions.Properities.panPropKey;
import java.awt.Robot;
import java.awt.event.KeyEvent;


public class KeyType extends AutomatedAction {

    int key;
    int action;
    int pressTime;
    
    public KeyType(int key,int pressTime,  int ac){
        this.key = key;
        action = ac;
        this.pressTime  = pressTime;
        
    }

    @Override
    public void run(Robot b) {
        if(action == 0 || action == 2){
           b.keyPress(key); 
        }
        if(action == 1 || action == 2){
           b.keyRelease(key);
        }
        
        
    }
    
    public String getKeyName(){
        return KeyEvent.getKeyText(key);
    }
    
    @Override
    public String toString() {
        return String.format(getKeyName());
    }

    @Override
    public PanProp getPanel() {
        return new panPropKey();
    }
    
    
    
    
    
}
