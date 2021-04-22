package body.mainScreenPack.Cells;

import body.mainScreenPack.FloatingTable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ClickListenerCell extends FloatingTableCell {





    static boolean isPressed = false;
    static int pressBtn = 0;
    MouseAdapter standartClick = new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {
            isPressed = false;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            click(e.getButton());
            isPressed = true;
            pressBtn = e.getButton();
        }

        @Override
        public void mouseEntered(MouseEvent e) {

            if(isPressed){
                click(pressBtn);
            }

        }


    };

    void click(int click) { 
        FloatingTable.mouseClick(this, click, isPressed);
    }

    public ClickListenerCell(String value) {
        super(value);
        addMouseListener(standartClick);
    }

    @Override
    public void updateValue() {

    }

}
