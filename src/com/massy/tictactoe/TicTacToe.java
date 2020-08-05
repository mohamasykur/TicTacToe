package com.massy.tictactoe;

import com.massy.tictactoe.menu.factory.MenuGenerator;
import com.massy.tictactoe.menu.item.MenuItemProp;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class TicTacToe extends Applet implements MouseListener,ActionListener {

    private BoardTic boardTic;
    private int turn, numturn;
    private int dimension = 3;

    public void init() {
        removeAll();
        boardTic = new BoardTic(dimension);
        boardTic.initBoard(this);

        setLayout(boardTic.getBoardLayout());
        setBackground(new Color(102, 153, 102));
        addMouseListener(this);
        turn = -1;
        buildMenu();
    }

    private void buildMenu(){
        Component f = getParent ();
        while (! (f instanceof Frame))
            f = f.getParent ();
        Frame frame = (Frame) f;

        MenuBar mb = new MenuBar();
        Menu fm;

        mb.add(fm = new Menu("setting"));
        List<MenuItemProp> menuItemPropList = MenuGenerator.generateAllMenu();

        for(MenuItemProp menuItemProp:menuItemPropList){
            fm.add(menuItemProp);
            menuItemProp.addActionListener(this);
        }

        frame.setMenuBar(mb);
    }

    public void play(Cell cell) {
        cell.setState(turn);

        if(boardTic.isBingo()){
            reset(1000);
        } else {
            numturn++;
            turn *= -1;
            if (numturn == boardTic.getTotalBoard()) {
                reset(1000);
            }
        }

        repaint();
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() instanceof Cell) {
            Cell cell = (Cell)e.getSource();
            if (cell.getState() == 0) {
                play(cell);
            }
        }
    }

    public void reset(int w) {
        try { Thread.sleep(w); } catch (InterruptedException ignored) {}
        boardTic.reset();
        turn = -1;
        numturn = 0;
    }

    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof MenuItemProp){
            MenuItemProp source = (MenuItemProp) e.getSource();
            source.perform(this);
        }
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
}