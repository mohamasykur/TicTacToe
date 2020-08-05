package com.massy.tictactoe.menu.item;

import com.massy.tictactoe.TicTacToe;

public class DimMenu extends MenuItemProp {
    int dimension;
    public DimMenu(String text,int dimension){
        super(text);
        this.dimension=dimension;
    }

    @Override
    public void perform(TicTacToe parent) {
        parent.setDimension(dimension);
        parent.reset(10);
        parent.destroy();
        parent.init();
    }
}
