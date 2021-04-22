package body.mainScreenPack.Cells;

import javax.swing.*;
import java.awt.*;

public class StaticCell extends ClickListenerCell {

    String Header;

    JLabel lblHeader;
    JLabel lblValue;

    public StaticCell(String header, String value) {
        super(value);
        this.Header = header;
        StaticCell.this.setLayout(new GridLayout(2, 1));

        lblHeader = new JLabel(header);
        lblValue = new JLabel(value);

        add(lblHeader);
        add(lblValue);

        setMinimumSize(new Dimension(30, 20));
        lblHeader.setBackground(getBackground().darker());
        lblHeader.setOpaque(true);
        lblValue.setOpaque(true);
    }

    public void setBackgrundColor(Color cor){
        lblValue.setBackground(cor);
        lblHeader.setBackground(cor.darker());
    }

    public Color getBackgroundColor(){
        return lblValue.getBackground();
    }

    @Override
    public void updateValue() {

    }

    @Override
    public void setValue(String v) {
        super.setValue(v); //To change body of generated methods, choose Tools | Templates.
        lblValue.setText(v);
    }

}