package com.tap.daoImplementation;

import java.util.Iterator;
import java.util.List;

import com.tap.model.Menu;

public class Test {

	public static void main(String[] args) {

     MenuDAOImplementation m = new MenuDAOImplementation();
     List<Menu> menuListByRestaurantId = m.getMenuListByRestaurantId(101);
//     System.out.println(m.getAllMenu());
     System.out.println(menuListByRestaurantId);
     
     for(Menu me:menuListByRestaurantId) {
    	 System.out.println(me);
     }
//     List<Menu> menuList =m.getAllMenu();
//     for(Menu me:menuList) {
//    	 System.out.println(me);
//    	 
////     }
//     Menu mm = new Menu(5, 103,"Noodles", "Popular oriental dish made with noodles and vegetables", 80, 4.8f, true, "https://tse3.mm.bing.net/th?id=OIP.C3Ep0grJrP7VQASs-3ocnwHaFY&pid=Api&P=0&h=180");
//     
//    int i =  m.addMenu(mm);
//    System.out.println(i);

	}

}
