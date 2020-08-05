package com.massy.tictactoe;

import com.sun.xml.internal.bind.v2.TODO;

import java.applet.Applet;
import java.awt.event.MouseListener;

public class BoardTic {

    private Cell[][] board;
    private int dimension;
    MouseListener mouseListener;
    private BoardLayout boardLayout;

    public BoardTic(int dimension){
        this.dimension = dimension;
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
                } else {
                    if(compareState != board[i][j].getState())
                        bingoLoop = false;
                }
            }
            
            if(bingoLoop){
                //TODO highlight current row
                return true;
            }
        }

        //checking column
        for(int i=0;i<dimension;i++){
            boolean bingoLoop = true;
            for(int j=0;j<dimension;j++){
                if(j==0){
                    compareState = board[j][i].getState();
                } else {
                    if(compareState != board[j][i].getState())
                        bingoLoop = false;
                }
            }

            if(bingoLoop){
                //TODO highlight current column
                return true;
            }
        }

        boolean bingo = true;
        // checking diagonal
        for(int i=0;i<dimension;i++){
            if(i==0){
                compareState=board[i][i].getState();
            }
            else {
                if(compareState != board[i][i].getState())
                    bingo = false;
            }
        }

        if(bingo)
            //TODO highlight diagonal
            return bingo;

        // checking diagonal
        for(int i=0;i<dimension;i++){
            if(i==0){
                compareState=board[dimension-1][i].getState();
            }
            else {
                if(compareState != board[dimension-i-1][i].getState())
                    bingo = false;
            }
        }

        if(bingo){
            //TODO highlight current diagonal
        }
        return bingo;
    }
}
