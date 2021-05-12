package body.mainScreenPack.Cells;

import body.mainScreenPack.Ults.TableController;

public class HeaderCell extends StaticCell {

    int HeaderID;

    public HeaderCell(String header, String value, int hID) {
        super(header, value);
        HeaderID = hID;
        updateValue();
    }

    @Override
    public void updateValue() {
        setValue(TableController.getCellValue(HeaderID).toString());
    }

    @Override
    public void setValue(String v) {
        super.setValue(v); //To change body of generated methods, choose Tools | Templates.
        TableController.uptadeLayerValueFromCell(v, HeaderID);

    }

}