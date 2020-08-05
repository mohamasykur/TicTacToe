package com.massy.tictactoe.menu.item;

import com.massy.tictactoe.TicTacToe;

public class ResetMenu extends MenuItemProp {
    public ResetMenu(String reset) {
        super(reset);
    }

    @Override
    public void perform(TicTacToe parent) {
        parent.reset(10);
        parent.destroy();
        parent.init();
    }
}
