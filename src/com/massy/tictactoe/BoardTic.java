package com.massy.tictactoe;

import java.applet.Applet;
import java.awt.event.MouseListener;

public class BoardTic {

    private Cell[][] board;
    private int dimension;
    private BoardLayout boardLayout;

    public BoardTic(int dimension){
        this.dimension = dimension;
        board = new Cell[dimension][dimension];
    }

    public void initBoard(Applet parent){
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j] = new Cell();
                board[i][j].addMouseListener((MouseListener) parent);
                parent.add(board[i][j]);
            }
        }

        boardLayout = new BoardLayout(dimension, dimension, 1f/34, 1f/34, 1, 1);
    }

    public BoardLayout getBoardLayout() {
        return boardLayout;
    }

    public void reset(){
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                board[i][j].setState(0);
            }
        }
    }

    public boolean isBingo(){
        int compareState = 0;

        // checking row
        for(int i=0;i<dimension;i++){
            boolean bingoLoop = true;
            for(int j=0;j<dimension;j++){
                if(j==0){
                    compareState = board[i][j].getState();
                    if(compareState==0) {
                        bingoLoop = false;
                        break;
                    }
                } else {
                    if(compareState != board[i][j].getState()) {
                        bingoLoop = false;
                        break;
                    }
                }
            }
            
            if(bingoLoop){
                //TODO highlight current row
                for(int j=0;j<dimension;j++){
                    board[i][j].highlight();
                }
                return true;
            }
        }

        //checking column
        for(int i=0;i<dimension;i++){
            boolean bingoLoop = true;
            for(int j=0;j<dimension;j++){
                if(j==0){
                    compareState = board[j][i].getState();
                    if(compareState==0) {
                        bingoLoop = false;
                        break;
                    }
                } else {
                    if(compareState != board[j][i].getState()) {
                        bingoLoop = false;
                        break;
                    }
                }
            }

            if(bingoLoop){
                //TODO highlight current column
                for(int j=0;j<dimension;j++){
                    board[j][i].highlight();
                }
                return true;
            }
        }

        boolean bingo = true;
        // checking diagonal
        for(int i=0;i<dimension;i++){
            if(i==0){
                compareState=board[i][i].getState();
                if(compareState==0) {
                    bingo = false;
                    break;
                }
            }
            else {
                if(compareState != board[i][i].getState()) {
                    bingo = false;
                    break;
                }
            }
        }

        if(bingo) {
            //TODO highlight diagonal
            for (int i=0;i<dimension;i++){
                board[i][i].highlight();
            }
            return bingo;
        }

        bingo = true;

        // checking diagonal
        for(int i=0;i<dimension;i++){
            if(i==0){
                compareState=board[dimension-1][i].getState();
                if(compareState==0) {
                    bingo = false;
                    break;
                }
            }
            else {
                if(compareState != board[dimension-i-1][i].getState()) {
                    bingo = false;
                    break;
                }
            }
        }

        if(bingo){
            //TODO highlight current diagonal
            for (int i=0;i<dimension;i++){
                board[dimension-i-1][i].highlight();
            }
        }
        return bingo;
    }

    public int getTotalBoard() {
        return dimension*dimension;
    }
}
