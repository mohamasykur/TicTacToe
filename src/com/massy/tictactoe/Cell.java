package com.massy.tictactoe;

import java.awt.*;

public class Cell extends Component {

    private int state;
    private boolean highl;

    public Cell() {
        state = 0;
        setBackground(Color.white);
    }

    public void paint(Graphics g) {
        int w = getSize().width, h = getSize().height, l = Math.min(w, h);
        Color bg = Color.white;
        Color fg = (state == 1)?Color.red:Color.blue;
        if (highl) bg = new Color((bg.getRed()+fg.getRed())/2, (bg.getGreen()+fg.getGreen())/2, (bg.getBlue()+fg.getBlue())/2);
        g.setColor(bg);
        g.fillRect(0, 0, w, h);
        int dw = w/10, dh = h/10;
        if (dh < 1) dh = 1;
        if (dw < 1) dw = 1;
        if (state == 1) {
            int sw = 71*w/1000, sh = 71*h/1000;
            if (sh < 1) sh = 1;
            if (sw < 1) sw = 1;
            g.setColor(fg);
            int[] xs = {dw+sw, w/2, w-dw-sw, w-dw, w/2+sw, w-dw, w-dw-sw, w/2, dw+sw, dw, w/2-sw, dw};
            int[] ys = {dh, h/2-sh, dh, dh+sh, h/2, h-dh-sh, h-dh, h/2+sh, h-dh, h-dh-sh, h/2, dh+sh};
            g.fillPolygon(xs, ys, 12);
        }
        else if (state == -1) {
            g.setColor(fg);
            g.fillOval(dw, dh, w-2*dw, h-2*dh);
            g.setColor(bg);
            g.fillOval(2*dw, 2*dh, w-4*dw, h-4*dh);
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void setState(int s) {
        if (s == 0 || s == -1 || s == 1) {
            state = s;
            highl = false;
            update(getGraphics());
        }
    }

    public void highlight() {
        highl = true;
        update(getGraphics());
    }

    public int getState() {
        return state;
    }

    public Dimension getPreferredSize() {
        return new Dimension(60, 60);
    }

    public Dimension getMinimumSize() {
        return new Dimension(10, 10);
    }

}
