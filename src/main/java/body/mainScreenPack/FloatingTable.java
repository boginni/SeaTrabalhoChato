/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package body.mainScreenPack;

import body.mainScreenPack.Cells.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.TableModel;

/**
 *
 * @author boginni
 */
public class FloatingTable extends JFrame {

    interface onClickListener {

        void mouseClick(FloatingTableCell cell, int click, boolean isPressed);

    }
    private static ArrayList<onClickListener> listeners = new ArrayList<>();

    public static void addClickListener(onClickListener object) {
        listeners.add(object);
    }

    public static void mouseClick(FloatingTableCell cell, int click, boolean isPressed) {
        //System.out.println(cell.getValue()+" "+isPressed);
        for (onClickListener curListener : listeners) {
            curListener.mouseClick(cell, click, isPressed);
        }
    }

    class HeaderTableCell extends StaticCell {

        int HeaderID;

        public HeaderTableCell(String header, String value, int hID) {
            super(header, value);
            HeaderID = hID;
            updateValue();
        }

        @Override
        public void updateValue() {
            setValue((String) getCurrentLayer().getValueAt(curRow, HeaderID));
        }

        @Override
        public void setValue(String v) {
            super.setValue(v); //To change body of generated methods, choose Tools | Templates.
            getCurrentLayer().setValueAt(v, curRow, HeaderID);
        }

    }

    public FloatingTable(TableModel originalTable, TableModel layout, int startRow, LayoutManager gridLay) {
        super();

        setLayout(gridLay);

        setOriginalTable(originalTable);

        for (int i = 0; i < layout.getRowCount(); i++) {

            String header = layout.getValueAt(i, 1).toString();
            String value = layout.getValueAt(i, 2).toString();
            String columID = layout.getValueAt(i, 3).toString();
            FloatingTableCell curCell;

            if (columID.equals("N/A")) {
                curCell = new StaticCell(header, value);
            } else if (columID.equals("button")) {

                curCell = new ButtonCell(header, null);
                MouseAdapter m = new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        FloatingTable.mouseClick(curCell, Integer.parseInt(value), false);
                    }
                };
                ((ButtonCell) curCell).setMouse(m);
                btnCells.add((ButtonCell) curCell);
            } else {
                int hID = Integer.parseInt(columID);
                curCell = new HeaderTableCell(header, "===========", hID);
            }

            cells.add(curCell);

            add(curCell);
        }

        pack();
        setVisible(true);
    }

    private int curRow = 0;
    private int curLayTable = 0;

    public void setRow(int newRow) {
        int maxSize = getCurrentLayer().getRowCount();
        if (newRow >= maxSize) {

            return;
        }
        if (newRow < 0) {

            return;
        }

        curRow = newRow;

        cells.forEach(cell -> {
            cell.updateValue();
        });

    }

    public void sumRow(int sum) {
        setRow(curRow + sum);
    }

    public int getRow() {
        return curRow;
    }

    public FloatingTableCell[] getCells() {
        return cells.toArray(new FloatingTableCell[]{});
    }

    public TableModel getCurrentLayer() {
        return layerTables.get(curLayTable);
    }

    public int getCurrentLayerIndex() {
        return curLayTable;
    }

    //TODO
    public void setLayer(int newLayer) {

    }

    ArrayList<FloatingTableCell> cells = new ArrayList<>();
    ArrayList<ButtonCell> btnCells = new ArrayList<>();

    public void setOriginalTable(TableModel t) {
        originalTable = t;
        layerTables.clear();
        layerTables.add(originalTable);
    }

    TableModel originalTable;
    ArrayList<TableModel> layerTables = new ArrayList<>();

}



