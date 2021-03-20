package body;

import head.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class FloatingGui2 extends JFrame implements BackgroundInputListener {

    Bot bot;

    static Color backgroundColor = new Color(255, 255, 255);
    static Color primaryColor = new Color(200, 200, 200);
    static Color secondaryColor = new Color(230, 230, 230);
    static Color selectedColor = Color.BLUE;

    static CommonCell lastSelected = null;

    public class CommonCell extends JPanel {

        MouseAdapter standartClick = new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                CommonCell.this.lblValue.setBackground(selectedColor);
                if (lastSelected != null) {
                    lastSelected.lblValue.setBackground(backgroundColor);
                }
                lastSelected = CommonCell.this;
                switch (e.getButton()) {
                    case 1:
                        leftClick();
                        break;
                    case 2:
                        middleClick();
                        break;
                    case 3:
                        rightClick();
                        break;
                    default:
                        break;
                }
            }
        };

        JLabel lblTitle;
        JLabel lblValue;

        String value = "";
        private int id;
        public CommonCell(String cellTitle, boolean colorShift) {
            super();
            setBackground(backgroundColor);
            lblTitle = new JLabel(cellTitle);
            lblTitle.setBackground(colorShift ? primaryColor : secondaryColor);
            lblTitle.setOpaque(true);

            lblValue = new JLabel();
            lblValue.setBackground(backgroundColor);
            lblValue.setOpaque(true);
            lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            setLayout(new java.awt.GridLayout(2, 0, 2, 0));
            add(lblTitle);
            add(lblValue);
            addMouseListener(standartClick);
            id = cells.size();
            cells.add(this);

        }

        void leftClick() {
            ClipBoard.setClipBoard(value, true);
        }

        void middleClick() {
            setValue("");

        }

        void rightClick() {
            String v = ClipBoard.getClipBoard();
            setValue(v);
            setCellValue(currentRow,id, v);
        }

        void setValue(String v) {
            value = v;
            lblValue.setText(v);

        }

    }

    public FloatingGui2() {
        super();
        ClipBoard.setClipBoard("made by: boginni\nSource code: github/boginni/SeaTabalhoChato", false);

        boolean colorShifter = false;
        String row1[] = {"Client", "Contrato", "Nome", "Rua", "Bairro", "Numero", "Complemento"};
        String row2[] = {"Coordenadas", "CPF/CNPJ", "TELEFONE", "Email", "Physical Addres", "MAC", "Sinal"};
        setLayout(new java.awt.GridLayout(2, row1.length, 2, 2));


        for (int i = 0; i < row1.length; i++) {
            add(new CommonCell(row1[i], colorShifter));
        }
        for (int i = 0; i < row1.length; i++) {
            add(new CommonCell(row2[i], !colorShifter));
        }

        setAlwaysOnTop(true);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BackgroundListener.addListener(this);
    }

    HashMap<Integer, Integer> tableConfig = new HashMap<>();
    ArrayList<CommonCell> cells = new ArrayList<CommonCell>();
    Table curTable;

    String values[][];

    public void setTable(Table t, HashMap<Integer, Integer> tableConfig) {
        if(t == null){
            return;
        }

        curTable = t;

        if(tableConfig == null){
            this.tableConfig.clear();
        } else {
            this.tableConfig = tableConfig;
        }
        values = new String[curTable.getRowCount()][cells.size()];
        currentRow = 0;
        if(curTable != null)
            setRow(0);
    }
    private int currentRow = 0;
    public void setRow(int row) {
        if(curTable == null)
            return;
        if (row < 0 || row > curTable.getRowCount()) {
            return;
        }
        currentRow = row;

        System.out.println("curRow: "+currentRow);
        for (int i = 0; i < cells.size(); i++) {
            CommonCell cell = cells.get(i);
            String var = "";
            try{

                if(values[row][i] == null){
                    if (tableConfig.containsKey(i)) {
                        var = curTable.getCell(row, tableConfig.get(i));
                        values[row][i] = var;
                    }
                } else {
                    var = values[row][i];
                }

            } catch (Exception e){

            }
            cell.setValue(var);
        }

    }

    void setCellValue(int row, int col, String var){
        if(curTable == null)
            return;
        values[currentRow][col] = var;
        System.out.println("Pasted at row, col: "+currentRow+","+col);
    }

    @Override
    public void keyEvent(boolean b, int i) {
        if(b){
            if(i == 57424){
                setRow(currentRow+1);
            }

            if(i == 57416){
                setRow(currentRow-1);
            }
        }
    }

}
