package com.massy.tictactoe.menu.item;

import com.massy.tictactoe.TicTacToe;

import java.awt.*;

public abstract class MenuItemProp extends MenuItem {
    public MenuItemProp(String text) {
        super(text);
    }

    public abstract void perform(TicTacToe parent);
}
