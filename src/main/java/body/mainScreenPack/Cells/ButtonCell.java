package body.mainScreenPack.Cells;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class ButtonCell extends FloatingTableCell {

    public static final int BTN_NEXT = 0,
            BTN_LAST = 1;

    JButton btn;

    public ButtonCell(String value, MouseAdapter mouseEvent) {

        super(value);
        this.setLayout(new GridLayout());

        btn = new JButton(value);
        if (mouseEvent != null) {
            btn.addMouseListener(mouseEvent);
        }
        this.add(btn);
        setMinimumSize(new Dimension(30, 20));
    }

    @Override
    public void updateValue() {

    }

    public void setMouse(MouseAdapter mouseEvent) {
        btn.addMouseListener(mouseEvent);
    }

}