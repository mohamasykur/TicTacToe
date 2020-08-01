package com.massy.tictactoe;

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class TicTacToe extends Applet implements MouseListener {

    private Cell[][] board;
    private int turn, numturn;

    public void init() {
        board = new Cell[3][3];
        setLayout(new BoardLayout(3, 3, 1f/34, 1f/34, 1, 1));
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

}