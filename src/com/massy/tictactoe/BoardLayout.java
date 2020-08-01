package com.massy.tictactoe;

import java.awt.*;

public class BoardLayout implements LayoutManager {

    int hgap;
    int vgap;
    int rows;
    int cols;
    float relhgap;
    float relvgap;

    public BoardLayout(int rows, int cols, int hgap, int vgap) {
        if ((rows == 0) && (cols == 0)) {
            throw new IllegalArgumentException("rows and cols cannot both be zero");
        }
        this.rows = rows;
        this.cols = cols;
        this.hgap = hgap;
        this.vgap = vgap;
        this.relhgap = 0;
        this.relvgap = 0;
    }

    public BoardLayout(int rows, int cols, float relhgap, float relvgap, int minhgap, int minvgap) {
        this.rows = rows;
        this.cols = cols;
        this.hgap = minhgap;
        this.vgap = minvgap;
        this.relhgap = relhgap;
        this.relvgap = relvgap;
    }

    public BoardLayout(int rows, int cols, float relhgap, float relvgap) {
        this(rows, cols, relhgap, relvgap, 0, 0);
    }

    public BoardLayout() {
        this(1, 0, 0, 0);
    }

    public BoardLayout(int rows, int cols) {
        this(rows, cols, 0, 0);
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if ((rows == 0) && (this.cols == 0)) {
            throw new IllegalArgumentException("rows and cols cannot both be zero");
        }
        this.rows = rows;
    }

    public int getColumns() {
        return cols;
    }

    public void setColumns(int cols) {
        if ((cols == 0) && (this.rows == 0)) {
            throw new IllegalArgumentException("rows and cols cannot both be zero");
        }
        this.cols = cols;
    }

    public int getHgap() {
        return hgap;
    }

    public float getRelHgap() {
        return relhgap;
    }

    public void setHgap(int hgap) {
        this.hgap = hgap;
    }

    public void setHgap(float relhgap) {
        this.relhgap = relhgap;
    }

    public int getVgap() {
        return vgap;
    }

    public float getRelVgap() {
        return relvgap;
    }

    public void setVgap(int vgap) {
        this.vgap = vgap;
    }

    public void setVgap(float relvgap) {
        this.relvgap = relvgap;
    }

    public void addLayoutComponent(String name, Component comp) {
    }

    public void removeLayoutComponent(Component comp) {
    }

    public Dimension preferredLayoutSize(Container parent) {
        Insets insets = parent.getInsets();
        int ncomponents = parent.getComponentCount();
        int nrows = rows;
        int ncols = cols;

        if (nrows > 0) {
            ncols = (ncomponents + nrows - 1) / nrows;
        } else {
            nrows = (ncomponents + ncols - 1) / ncols;
        }
        int w = 0;
        int h = 0;
        for (int i = 0 ; i < ncomponents ; i++) {
            Component comp = parent.getComponent(i);
            Dimension d = comp.getPreferredSize();
            if (w < d.width) {
                w = d.width;
            }
            if (h < d.height) {
                h = d.height;
            }
        }
        int hgap = this.hgap;
        int vgap = this.vgap;
        if (relhgap != 0) hgap = Math.round(relhgap*ncols*w/(1-relhgap*(ncols+1)));
        if (relvgap != 0) vgap = Math.round(relvgap*nrows*h/(1-relvgap*(nrows+1)));
        return new Dimension(insets.left + insets.right + ncols*w + (ncols+1)*hgap, insets.top + insets.bottom + nrows*h + (nrows+1)*vgap);
    }

    public Dimension minimumLayoutSize(Container parent) {
        Insets insets = parent.getInsets();
        int ncomponents = parent.getComponentCount();
        int nrows = rows;
        int ncols = cols;

        if (nrows > 0) {
            ncols = (ncomponents + nrows - 1) / nrows;
        } else {
            nrows = (ncomponents + ncols - 1) / ncols;
        }
        int w = 0;
        int h = 0;
        for (int i = 0 ; i < ncomponents ; i++) {
            Component comp = parent.getComponent(i);
            Dimension d = comp.getMinimumSize();
            if (w < d.width) {
                w = d.width;
            }
            if (h < d.height) {
                h = d.height;
            }
        }
        int hgap = this.hgap;
        int vgap = this.vgap;
        if (relhgap != 0) hgap = Math.round(relhgap*ncols*w/(1-relhgap*(ncols+1)));
        if (relvgap != 0) vgap = Math.round(relvgap*nrows*h/(1-relvgap*(nrows+1)));
        return new Dimension(insets.left + insets.right + ncols*w + (ncols+1)*hgap, insets.top + insets.bottom + nrows*h + (nrows+1)*vgap);
    }

    public void layoutContainer(Container parent) {
        Insets insets = parent.getInsets();
        int ncomponents = parent.getComponentCount();
        int nrows = rows;
        int ncols = cols;

        if (ncomponents == 0) {
            return;
        }
        if (nrows > 0) {
            ncols = (ncomponents + nrows - 1) / nrows;
        } else {
            nrows = (ncomponents + ncols - 1) / ncols;
        }
        int w = parent.getSize().width - (insets.left + insets.right);
        int h = parent.getSize().height - (insets.top + insets.bottom);
        int minhgap = hgap;
        int hgap = Math.round(relhgap*w);
        if (hgap < minhgap) hgap = minhgap;
        int minvgap = vgap;
        int vgap = Math.round(relvgap*h);
        if (vgap < minvgap) vgap = minvgap;
        w = (w - (ncols + 1) * hgap) / ncols;
        h = (h - (nrows + 1) * vgap) / nrows;

        for (int c = 0, x = insets.left + hgap ; c < ncols ; c++, x += w + hgap) {
            for (int r = 0, y = insets.top + vgap ; r < nrows ; r++, y += h + vgap) {
                int i = r * ncols + c;
                if (i < ncomponents) {
                    parent.getComponent(i).setBounds(x, y, w, h);
                }
            }
        }
    }

    public String toString() {
        return getClass().getName() + "[hgap=" + hgap + ",vgap=" + vgap + ",relhgap=" + relhgap + ",relvgap=" + relvgap + ",rows=" + rows + ",cols=" + cols + "]";
    }

}