/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package body.mainScreenPack.AutoActions;

import java.awt.Robot;
import body.mainScreenPack.AutoActions.Properities.PanProp;


public class AutomatedAction {

    public void run(Robot b){
        
    }
    
    public void start(Robot b){
        if(b != null){
            run(b);
        }
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
    
    
    public PanProp getPanel(){
        return PanProp.getNullPanel();
    }
    
}
