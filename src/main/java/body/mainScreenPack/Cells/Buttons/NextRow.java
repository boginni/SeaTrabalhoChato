package body.mainScreenPack.Cells.Buttons;

import body.mainScreenPack.FloatingTable;
import body.mainScreenPack.Ults.TableController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NextRow extends ButtonCell{
    public NextRow(String header) {
        super(header);
    }

    @Override
    public void updateValue() {
        btn.setEnabled(TableController.hasNextRow());
    }

    @Override
    public void click() {
        TableController.sumRow(+1);
    }
}
