package com.massy.tictactoe.menu;

import java.applet.Applet;

public class ResetMenu extends MenuItemProp {
    @Override
    public void perform(Applet parent) {
        parent.destroy();
        parent.init();
    }
}
