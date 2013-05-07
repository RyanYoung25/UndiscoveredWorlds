import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JFrame;

public class GalaxyTest {
  
  //More of Ryan's junk
  private static Vector<Item> items1;
  private static Vector<Item> items2;

	public static void main(String[] args) 
	{
		Galaxy Andromeda = new Galaxy();

		Andromeda.Generate(1);
		Andromeda.DisplayCluster();
		
		JFrame frame = new JFrame("Galaxy Test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUITest yay = new GUITest(Andromeda.GetSectors());
		frame.add(yay);
		frame.setSize(700, 700);
		frame.setVisible(true);
		
		//Code for trading interface
	/*items1= new Vector<Item>();
    items2 = new Vector<Item>();
    items1.add(new Item("name", "Description", 0, 50, 500, 5000, 50000));
    items2.add(new Item("SecondName", "Description", 4, 4, 4, 4, 4));
    
    TradingMenu menu = new TradingMenu(new Player("Person1", items1), );
    menu.setSize(300, 200);
    menu.setVisible(true);
    menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/  //Commented out because of changes
		
	}

}
