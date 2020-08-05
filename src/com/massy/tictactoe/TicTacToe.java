package com.massy.tictactoe;

import com.massy.tictactoe.menu.MenuItemProp;

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class TicTacToe extends Applet implements MouseListener,ActionListener {

    private Cell[][] board;
    private BoardTic boardTic;
    private int turn, numturn;
    private MenuItem resetMenu;
    private MenuItem dim3x3,dim4x4,dim5x5;
    private int dimension = 3;


    public void init() {
        board = new Cell[3][3];
        removeAll();

        boardTic = new BoardTic(dimension);
        boardTic.initBoard(this);

        setLayout(boardTic.getBoardLayout());
        setBackground(new Color(102, 153, 102));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Cell();
                board[i][j].addMouseListener(this);
                add(board[i][j]);
            }
        }
        addMouseListener(this);
        turn = -1;
        buildMenu();
    }

    private void buildMenu(){
        Object f = getParent ();
        while (! (f instanceof Frame))
            f = ((Component) f).getParent ();
        Frame frame = (Frame) f;

        MenuBar mb = new MenuBar();
        Menu fm;
        MenuItem ol;

        mb.add(fm = new Menu("dimension"));
        dim3x3 = new MenuItem("3 x 3");
        dim4x4 = new MenuItem("4 x 4");
        dim5x5 = new MenuItem("5 x 5");

        fm.add(dim3x3);
        fm.add(dim4x4);
        fm.add(dim5x5);

        dim3x3.addActionListener(this::actionPerformed);
        dim4x4.addActionListener(this::actionPerformed);
        dim5x5.addActionListener(this::actionPerformed);

        mb.add(fm = new Menu("setting"));
        resetMenu = new MenuItem("reset");
        fm.add(resetMenu);
        resetMenu.addActionListener(this::actionPerformed);

        frame.setMenuBar(mb);
    }

    public boolean play(int c) {
        return play(board[c/3][c%3]);
    }

    public boolean play(Cell cell) {
        boolean goon = true;
        cell.setState(turn);

        if (turn == board[0][0].getState() && board[0][0].getState() == board[0][1].getState() && board[0][1].getState() == board[0][2].getState()) {
            board[0][0].highlight(); board[0][1].highlight(); board[0][2].highlight();
            reset(1000);
            goon = false;
        }
        else if (turn == board[1][0].getState() && board[1][0].getState() == board[1][1].getState() && board[1][1].getState() == board[1][2].getState()) {
            board[1][0].highlight(); board[1][1].highlight(); board[1][2].highlight();
            reset(1000);
            goon = false;
        }
        else if (turn == board[2][0].getState() && board[2][0].getState() == board[2][1].getState() && board[2][1].getState() == board[2][2].getState()) {
            board[2][0].highlight(); board[2][1].highlight(); board[2][2].highlight();
            reset(1000);
            goon = false;
        }
        else if (turn == board[0][0].getState() && board[0][0].getState() == board[1][0].getState() && board[1][0].getState() == board[2][0].getState()) {
            board[0][0].highlight(); board[1][0].highlight(); board[2][0].highlight();
            reset(1000);
            goon = false;
        }
        else if (turn == board[0][1].getState() && board[0][1].getState() == board[1][1].getState() && board[1][1].getState() == board[2][1].getState()) {
            board[0][1].highlight(); board[1][1].highlight(); board[2][1].highlight();
            reset(1000);
            goon = false;
        }
        else if (turn == board[0][2].getState() && board[0][2].getState() == board[1][2].getState() && board[1][2].getState() == board[2][2].getState()) {
            board[0][2].highlight(); board[1][2].highlight(); board[2][2].highlight();
            reset(1000);
            goon = false;
        }
        else if (turn == board[0][0].getState() && board[0][0].getState() == board[1][1].getState() && board[1][1].getState() == board[2][2].getState()) {
            board[0][0].highlight(); board[1][1].highlight(); board[2][2].highlight();
            reset(1000);
            goon = false;
        }
        else if (turn == board[2][0].getState() && board[2][0].getState() == board[1][1].getState() && board[1][1].getState() == board[0][2].getState()) {
            board[2][0].highlight(); board[1][1].highlight(); board[0][2].highlight();
            reset(1000);
            goon = false;
        }
        else {
            numturn++;
            turn *= -1;
            if (numturn == 9) {
                reset(1000);
                goon = false;
            }
        }

        repaint();
        return goon;
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
        try { Thread.sleep(w); } catch (InterruptedException ie) {}
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setState(0);
            }
        }
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
            ((MenuItemProp)e.getSource()).perform(this);
        }
    }
}