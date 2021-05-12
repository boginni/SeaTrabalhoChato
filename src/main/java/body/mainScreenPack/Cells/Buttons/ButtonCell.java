package body.mainScreenPack.Cells.Buttons;

import body.mainScreenPack.Cells.FloatingTableCell;
import body.mainScreenPack.Ults.TableController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class ButtonCell extends FloatingTableCell {

    public static final int BTN_NEXT = 0,
            BTN_LAST = 1;

    JButton btn;

    public static ButtonCell getButton(String header, int ID){
        switch (ID){
            case BTN_NEXT:
                return new NextRow(header);
            case BTN_LAST:
                return new LastRow(header);
            default:
                return null;
        }
    }

    public ButtonCell(String header) {

        super("");
        this.setLayout(new GridLayout());

        btn = new JButton(header);

        MouseAdapter m = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                click();
            }
        };
        btn.addMouseListener(m);

        this.add(btn);
        setMinimumSize(new Dimension(30, 20));
    }

    @Override
    public abstract void updateValue() ;

    public abstract void click();

}