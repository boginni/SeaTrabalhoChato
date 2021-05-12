package body.mainScreenPack.Cells.Buttons;

import body.mainScreenPack.Ults.TableController;

public class LastRow extends ButtonCell{
    public LastRow(String header) {
        super(header);
    }

    @Override
    public void updateValue() {
        btn.setEnabled(TableController.getRow() > 0);
    }

    @Override
    public void click() {
        TableController.sumRow(-1);
    }
}
