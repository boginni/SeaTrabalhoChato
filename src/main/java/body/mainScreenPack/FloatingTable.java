package body.mainScreenPack;

import body.mainScreenPack.Cells.*;
import body.mainScreenPack.Cells.Buttons.ButtonCell;
import body.mainScreenPack.Ults.TableController;
import body.mainScreenPack.Ults.TableControllerTarget;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.TableModel;

import static body.mainScreenPack.Ults.ColorFade.AllCellsColor;

/**
 *
 * @author boginni
 */
public class FloatingTable extends JFrame implements TableControllerTarget {

    @Override
    public void doCellsUpdate() {
        System.out.println("Update Requested");

        cells.forEach(cell -> {
            cell.updateValue();

        });
    }

    @Override
    public void doButtonsUpdate() {
        btnCells.forEach(ButtonCell::updateValue);
    }

    public FloatingTable(TableModel layout, int startRow, LayoutManager gridLay) {
        super();

        setLayout(gridLay);

        for (int i = 0; i < layout.getRowCount(); i++) {

            String header = layout.getValueAt(i, 1).toString();
            String value = layout.getValueAt(i, 2).toString();
            String columID = layout.getValueAt(i, 3).toString();
            FloatingTableCell curCell;

            if (columID.equals("N/A")) {
                curCell = new StaticCell(header, value);
            } else if (columID.equals("button")) {
                curCell = ButtonCell.getButton(header, Integer.parseInt(value.toString()));
                btnCells.add((ButtonCell) curCell);
            } else {
                int hID = Integer.parseInt(columID);
                curCell = new HeaderCell(header, "===========", hID);
            }

            cells.add(curCell);

            add(curCell);
        }

        pack();
        TableController.setRow(startRow);
        setVisible(true);

        AllCellsColor(getCells(), Color.BLACK, Color.white, 2000);
    }

    public FloatingTableCell[] getCells() {
        return cells.toArray(new FloatingTableCell[]{});
    }
    
    public void importValue(int cellID, String value){
        try{
            ((HeaderCell)cells.get(cellID)).setValue(value);
        } catch(Exception e){
            
        }
    }

    ArrayList<FloatingTableCell> cells = new ArrayList<>();
    ArrayList<ButtonCell> btnCells = new ArrayList<>();

}



