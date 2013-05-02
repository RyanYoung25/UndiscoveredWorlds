


import java.util.Vector;

import javax.swing.JFrame;



public class MainItemCreator
{
  
  private static Vector<Item> items1;
  private static Vector<Item> items2;

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    
    items1= new Vector<Item>();
    items2 = new Vector<Item>();
    items1.add(new Item("name", "Description", 0, 50, 500, 5000, 50000));
    items2.add(new Item("SecondName", "Description", 4, 4, 4, 4, 4));
    
    /*
    ItemMaker maker = new ItemMaker();
    maker.setSize(400, 600);
    maker.setVisible(true);
    maker.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    **/
    
    TradingMenu menu = new TradingMenu(new Player("Person1", items1), new Player("Person2", items2));
    menu.setSize(300, 200);
    menu.setVisible(true);
    menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

}
