package body;

import head.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class FloatingGui2 extends JFrame implements BackgroundInputListener {

    public interface ChangeRowListener {
        void onRowChange(int newRow);
    }

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
                if (!isRunning)
                    return;



                switch (e.getButton()) {
                    case 1:
                        new ColorFade(CommonCell.this, selectedColor, new Color(220, 220, 255), 1000);
                        leftClick();
                        break;
                    case 2:
                        new ColorFade(CommonCell.this,null, Color.white, 1000);
                        middleClick();
                        break;
                    case 3:
                        new ColorFade(CommonCell.this,Color.green, new Color(220, 255, 220), 1000);
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
            id = cells.size();
            setBackground(backgroundColor);
            lblTitle = new JLabel(cellTitle);
            lblTitle.setBackground(colorShift ? primaryColor : secondaryColor);
            lblTitle.setOpaque(true);

            lblValue = new JLabel(String.format("ID{%d}",this.id));
            lblValue.setBackground(backgroundColor);
            lblValue.setOpaque(true);
            lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            setLayout(new java.awt.GridLayout(2, 0, 2, 0));
            add(lblTitle);
            add(lblValue);
            addMouseListener(standartClick);

            cells.add(this);

        }

        void leftClick() {
            ClipBoard.setClipBoard(value, true);
        }

        void middleClick() {
            pasteCell(this.id, "");

        }

        void rightClick() {
            String v = ClipBoard.getClipBoard();
            pasteCell(id, v);
        }

        void setValue(String v) {
            value = v;
            lblValue.setText(v);

        }

    }

    public static class ColorFade{
        Color startColor;
        double pass;
        double r,g,b;
        double rs,gs,bs;
        public CommonCell tarCell;

        public ColorFade(CommonCell c, Color newColor,  Color target, int interval){
            tarCell = c;
            if(newColor != null){
                tarCell.lblValue.setBackground(newColor);
            }
            startColor = c.lblValue.getBackground();
            r = target.getRed() - startColor.getRed();
            g = target.getGreen() - startColor.getGreen();
            b = target.getBlue() - startColor.getBlue();

            pass = (interval * 60.0d) / 1000.0;
            if(pass < 3.0){
                pass = 0;
                return;
            }

            rs = r / pass;
            gs = g / pass;
            bs = b / pass;

            r = startColor.getRed();
            g = startColor.getGreen();
            b = startColor.getBlue();
            colorFades.add(this);
        }


        public void tick(){
            Color newCor = new Color((int)r,(int)g,(int)b);
            tarCell.lblValue.setBackground(newCor);
            r += rs;
            g += gs;
            b += bs;
            pass--;
            if(r > 255 || r < 0){
                pass = 0;
            }

            if(g > 255 || g < 0){
                pass = 0;
            }

            if(b > 255 || b < 0){
                pass = 0;
            }
        }


    }
    static ArrayList<ColorFade> colorFades = new ArrayList<>();

    static TimerTask colorFadeTick = new TimerTask(){
        @Override
        public void run() {

            for (int i = colorFades.size() - 1; i >= 0; i--){
                ColorFade c = colorFades.get(i);
                if(c.pass > 1)
                    c.tick();
                else {
                    colorFades.remove(i);
                }

            }

        }

    };



    boolean isRunning = false;

    public void setPause(boolean b) {

        setAlwaysOnTop(!b);
        isRunning = !b;
    }

    public FloatingGui2() {
        super();
        ClipBoard.setClipBoard("made by: boginni\nSource code: github/boginni/SeaTrabalhoChato", true);

        boolean colorShifter = false;

        String row1[] = {"Client", "Contrato", "Nome", "Vendedor", "Plano", "Data", "CPF/CNPJ", "PlaceHolder"};
        String row2[] = {"TELEFONE", "CORD", "Email", "Rua", "Bairro", "Numero", "Complemento", "PlaceHolder"};
        String row3[] = {"Porta", "CTO", "Cabo", "Physical Addres", "MAC", "Sinal", "PlaceHolder", "PlaceHolder"};

        setLayout(new java.awt.GridLayout(3, row1.length, 2, 2));

        for (int i = 0; i < row1.length; i++) {
            add(new CommonCell(row1[i], colorShifter));
        }

        for (int i = 0; i < row1.length; i++) {
            add(new CommonCell(row2[i], !colorShifter));
        }

        for (int i = 0; i < row1.length; i++) {
            add(new CommonCell(row3[i], !colorShifter));
        }

        setAlwaysOnTop(true);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BackgroundListener.addListener(this);
        setPause(false);

        new Timer().schedule(colorFadeTick, 100l, (long) (1000.0/60.0));

    }

    static ArrayList<Object> rowChangeListeners = new ArrayList<>();

    public static void addRowListener(Object target) {
        if(target instanceof ChangeRowListener)
            rowChangeListeners.add(target);
    }

    HashMap<Integer, Integer> tableConfig = new HashMap<>();
    ArrayList<CommonCell> cells = new ArrayList<CommonCell>();
    Table curTable;

    String values[][];

    public void setTable(Table t, HashMap<Integer, Integer> tableConfig) {
        if (t == null) {
            return;
        }

        curTable = t;

        if (tableConfig == null) {
            this.tableConfig.clear();
        } else {
            this.tableConfig = tableConfig;
        }
        values = new String[curTable.getRowCount()][cells.size()];
        currentRow = 0;
        if (curTable != null)
            setRow(0);
    }

    private int currentRow = 0;
    boolean colorSwitch = false;

    public void setRow(int row) {
        if (curTable == null)
            return;
        if (row < 0 || row > curTable.getRowCount()) {
            return;
        }
        if (!isRunning) {
            return;
        }

        for (CommonCell c: cells) {
            c.lblTitle.setBackground(colorSwitch ? primaryColor : secondaryColor);
        }
        colorSwitch = !colorSwitch;

        currentRow = row;

        for (Object e: rowChangeListeners
        ) {
            ((ChangeRowListener) e).onRowChange(currentRow);
        }

        for (int i = 0; i < cells.size(); i++) {
            CommonCell cell = cells.get(i);
            String var = "";
            try {

                if (values[row][i] == null) {
                    if (tableConfig.containsKey(i)) {
                        var = curTable.getCell(row, tableConfig.get(i));
                        values[row][i] = var;
                    }
                } else {
                    var = values[row][i];
                }

            } catch (Exception e) {

            }
            cell.setValue(var);
        }

    }

    public CommonCell getCell(int cellID){
        if (curTable == null)
            return null;
        try {
            return cells.get(cellID);
        } catch (Exception e) {
            return  null;
        }
    }

    public void pasteCell(int cellID, String var) {
        if (curTable == null)
            return;
        try {
            values[currentRow][cellID] = var;
            cells.get(cellID).setValue(var);
        } catch (Exception e) {

        }
    }

    public String copyCell(int cellID) {
        if (curTable == null) {
            return "";
        }
        try {
            return cells.get(cellID).value;
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public void keyEvent(boolean b, int i) {
        if (b && isRunning) {
            if (i == 62) {
                setRow(currentRow + 1);
            }

            if (i == 60) {
                setRow(currentRow - 1);
            }
        }
    }

}
