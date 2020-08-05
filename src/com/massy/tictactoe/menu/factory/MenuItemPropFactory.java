package com.massy.tictactoe.menu.factory;

import com.massy.tictactoe.menu.MenuEnum;
import com.massy.tictactoe.menu.item.DimMenu;
import com.massy.tictactoe.menu.item.MenuItemProp;
import com.massy.tictactoe.menu.item.ResetMenu;

public class MenuItemPropFactory {
     public MenuItemProp getMenuItemProp(MenuEnum menuType){
         if(menuType.equals(MenuEnum.DIM3)){
             return new DimMenu("3 x 3",3);
         } else if(menuType.equals(MenuEnum.DIM4)){
             return new DimMenu("4 x 4",4);
         } else if(menuType.equals(MenuEnum.DIM5)){
             return new DimMenu("5 x 5",5);
         } else if(menuType.equals(MenuEnum.RESET)){
             return new ResetMenu("reset");
         } else {
             return null;
         }
     }
}
