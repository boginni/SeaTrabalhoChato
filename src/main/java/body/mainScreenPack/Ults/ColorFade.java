package body.mainScreenPack.Ults;

import body.mainScreenPack.Cells.FloatingTableCell;
import body.mainScreenPack.Cells.StaticCell;

import java.awt.*;
import java.util.ArrayList;
import java.util.TimerTask;

public class ColorFade {

    static ArrayList<ColorFade> colorFades = new ArrayList<>();



    public static TimerTask colorFadeTick = new TimerTask() {
        @Override
        public void run() {
            for (int i = colorFades.size() - 1; i >= 0; i--) {
                ColorFade c = colorFades.get(i);
                if (c != null && c.pass > 0) {
                    c.tick();
                } else {
                    colorFades.remove(i);
                }

            }

        }

    };

    public static void AllCellsColor(FloatingTableCell[] list, Color StarColor, Color endColor, int time) {
        colorFades.clear();
        for (FloatingTableCell cell : list) {
            if (cell instanceof StaticCell) {
                new ColorFade((StaticCell) cell, StarColor, endColor, time);
            }
        }

    }



    Color startColor;
    double pass;
    double r, g, b;
    double rs, gs, bs;
    public StaticCell tarCell;
    private Color tarColor;

    public ColorFade(StaticCell c, Color newColor, Color target, int interval) {
        tarColor = new Color(target.getRGB());
        tarCell = c;
        if (newColor != null) {
            tarCell.setBackgrundColor(newColor);
        }
        startColor = c.getBackgroundColor();
        r = target.getRed() - startColor.getRed();
        g = target.getGreen() - startColor.getGreen();
        b = target.getBlue() - startColor.getBlue();

        pass = (interval * 60.0d) / 1000.0;
        if (pass < 3.0) {
            pass = 0;
            return;
        }

        rs = r / pass;
        gs = g / pass;
        bs = b / pass;

        r = startColor.getRed();
        g = startColor.getGreen();
        b = startColor.getBlue();

        removeRunning();

        colorFades.add(this);
    }

    @Deprecated
    void removeRunning() {
        for (int i = 0; i < colorFades.size(); i++) {
            if (colorFades.get(i).tarCell == this.tarCell) {
                colorFades.remove(i);
                return;
            }
        }
    }

    public void tick() {
        Color newCor = new Color((int) r, (int) g, (int) b);
        tarCell.setBackgrundColor(newCor);
        r += rs;
        g += gs;
        b += bs;
        pass--;
        if (r > 255 || r < 0) {
            pass = 0;
        }

        if (g > 255 || g < 0) {
            pass = 0;
        }

        if (b > 255 || b < 0) {
            pass = 0;
        }
    }

    private void terminate() {
        tarCell.setBackgrundColor(tarColor);
        pass = 0;
    }

}
