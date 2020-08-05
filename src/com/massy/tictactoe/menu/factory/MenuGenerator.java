package com.massy.tictactoe.menu.factory;

import com.massy.tictactoe.menu.MenuEnum;
import com.massy.tictactoe.menu.item.MenuItemProp;

import java.util.ArrayList;
import java.util.List;

public class MenuGenerator {
    public static List<MenuItemProp> generateAllMenu(){
        List<MenuItemProp> result = new ArrayList<>();
        MenuItemPropFactory mipFact = FactoryProducer.getFactory();
        for(MenuEnum menuEnum:MenuEnum.values()){
            MenuItemProp menuItemProp = mipFact.getMenuItemProp(menuEnum);
            if(menuItemProp != null)
                result.add(menuItemProp);
        }
        return result;
    }
}
