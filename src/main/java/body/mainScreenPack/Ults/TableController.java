package body.mainScreenPack.Ults;

import body.mainScreenPack.Cells.Buttons.ButtonCell;
import body.mainScreenPack.Cells.ClickListenerCell;
import body.mainScreenPack.Cells.HeaderCell;
import body.mainScreenPack.Cells.StaticCell;
import body.mainScreenPack.ControllerHandlerPanel;
import body.mainScreenPack.FloatingTable;
import body.mainScreenPack.TableHandlerPanel;
import head.ClipBoard;

import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static body.mainScreenPack.Ults.ColorFade.AllCellsColor;
import head.ExportListener;

public class TableController implements ControllerHandlerPanel.TableHandlerPanelButtons, ExportListener {
    private static TableModel originalTable;
    private static ArrayList<TableModel> layerTables = new ArrayList<>();
    static private int currentTable = 0;
    public static FloatingTable floatingTable;

    public static TableModel getCurrentLayer(){
        return layerTables.get(currentTable);
    }

    public static TableModel getLayeredTable(int layer){
        if(layer < 0){
            return getCurrentLayer();
        }

        if(layerTables.size() > layer + 1){
            return layerTables.get(layer);
        }
        return getCurrentLayer();

    }
    private static int curRow = 0;

    public static int getRow(){
        return curRow;
    }

    public static void setRow(int newRow) {

        int maxSize = getCurrentLayer().getRowCount();

        if (newRow >= maxSize) {

            return;
        }

        if (newRow < 0) {

            return;
        }

        curRow = newRow;
        requestCellsUpdate();
        requestButtonsUpdate();
    }

    public static void sumRow(int sum) {
        setRow(curRow + sum);
    }

    public static boolean hasNextRow(){
        int maxSize = getCurrentLayer().getRowCount();
        int newRow = curRow+1;
        return !(newRow >= maxSize);
    }

    public static void requestCellsUpdate(){
        controllerTargets.forEach(TableControllerTarget::doCellsUpdate);
    }

    private static final ArrayList<TableControllerTarget> controllerTargets = new ArrayList<>();

    public static Object getCellValue(int headerID) {
        return getCurrentLayer().getValueAt(getRow(), headerID);
    }

    public static void uptadeLayerValueFromCell(String v, int headerID) {
        TableModelCell cell = (TableModelCell) getCurrentLayer().getValueAt(curRow, headerID);
        cell.value = v;
    }

    public static void addTableControllerTarget(TableControllerTarget t){
        controllerTargets.add(t);
    }

    public static void mouseClick(ClickListenerCell cell, int click, boolean isPressed) {
        String concat = " ";
        if(cell == null){
            return;
        }
        if (cell instanceof HeaderCell) {

            switch(click){

                case 1:
                    String newVar = cell.getValue();

                    if(isPressed){
                        newVar = String.format("%s%s%s", ClipBoard.getClipBoard(), concat, newVar);
                    }

                    ClipBoard.setClipBoardFormatted(newVar, true);
                    new ColorFade((StaticCell)cell, Color.blue, new Color(200, 220, 240), 1500);
                    break;
                case 2:

                    cell.setValue("");
                    new ColorFade((StaticCell)cell, Color.red, new Color(240, 220, 220), 1500);
                    break;
                case 3:

                    cell.setValue(ClipBoard.getClipBoardFormated());
                    new ColorFade((StaticCell)cell, Color.green, new Color(200, 240, 220), 1500);
                    break;
            }

            cell.updateValue();
            return;
        }

        if (cell instanceof StaticCell && click == 1) {
            String newVar = cell.getValue();
            if(isPressed){
                newVar = String.format("%s%s%s", ClipBoard.getClipBoard(), concat, newVar);
            }

            ClipBoard.setClipBoardFormatted(newVar, true);
            new ColorFade((StaticCell)cell, Color.blue, new Color(200, 220, 240), 1500);
            return;
        }

    }

    public static void boundTable(TableModel tableModel, String[] tableHeader) {
        currentTable = 0;
        originalTable = tableModel;
        layerTables.clear();
        layerTables.add(originalTable);


    }

    public static void requestButtonsUpdate() {
        controllerTargets.forEach(TableControllerTarget::doButtonsUpdate);
    }

    @Override
    public void ckbFixActionPerformed(boolean curState) {

    }

    @Override
    public void btnLastActionPerformed() {
        AllCellsColor(floatingTable.getCells(), new Color(255, 220, 220), getSwappedCor(), 1000);
        sumRow(-1);
    }

    @Override
    public void btnNextActionPerformed() {
        AllCellsColor(floatingTable.getCells(), new Color(230, 255, 255), getSwappedCor(), 1000);
        sumRow(+1);
    }

    @Override
    public void ckbShowDecActionPerformed(boolean curState) {
        floatingTable.dispose();
        floatingTable.setUndecorated(curState);
        floatingTable.setVisible(true);
    }



    static boolean swapColor = false;
    static private Color getSwappedCor(){
        int r,g,b, change = 35;
        r = 220 + (swapColor? change:0);
        g = 220 + (swapColor? change:0);
        b = 220 + (swapColor? change:0);
        swapColor = !swapColor;
        return new Color(r,g,b);
    }


    public static void createNewTable(int startRow, LayoutManager gridLayout){

        if (TableController.floatingTable != null) {
            System.out.println("closed");
            floatingTable.dispatchEvent(new WindowEvent(floatingTable, WindowEvent.WINDOW_CLOSING));
        }
        if(controllerTargets.contains(floatingTable))
            controllerTargets.remove(floatingTable);
        floatingTable = null;
        requestButtonsUpdate();
        floatingTable = new FloatingTable(tableLayout, 0, gridLayout);
        floatingTable.setBackground(Color.blue);
        floatingTable.repaint();
        floatingTable.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                controllerTargets.remove(floatingTable);
                requestButtonsUpdate();
            }
        });


        addTableControllerTarget(floatingTable);
            
    }

    private static TableModel tableLayout;

    public static void boundTableLayout(TableModel l){
        tableLayout = l;
    }

    @Override
    public void onExport(String[] vars, int[] targets) {
        if(floatingTable == null)
            return;
        for (int i = 0; i < vars.length; i++) {
            floatingTable.importValue(targets[i],vars[i]);
        }
    }
}

