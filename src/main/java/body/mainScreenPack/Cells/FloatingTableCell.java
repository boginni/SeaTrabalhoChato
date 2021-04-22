package body.mainScreenPack.Cells;

import javax.swing.*;

public abstract class FloatingTableCell extends JPanel {

    String value;

    //int id;
    public FloatingTableCell(String value) {
        this.value = value;
    }

    public abstract void updateValue();

    public void setValue(String v) {
        value = v;
    }

    public String getValue() {
        return value;
    }
}