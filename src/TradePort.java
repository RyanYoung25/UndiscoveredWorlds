import java.util.Collections;
import java.util.Vector;

/**
 * This class is used to represent a trading port on or within a planet, moon, ring or belt.
 * @author Bryant, Ryan
 */
public class TradePort extends Location implements Merchant
{
	public static final int BASE_MONEY = 2500;
	public static final int CHANCE = 5;
	private Vector<Item> inventory;
	private Orbital locale;
	private int money;
	
	/**
	 * Constructor. Generates "lite" version of TradePort.
	 * @param loc = Orbital that this TradePort is based on.
	 */
	public TradePort(Location loc)
	{
		locale = (Orbital) loc;
		genInventory();
		genMoney();
	}
	
	/**
	 * Constructor. Generates full TradePort for use in "recently visited ports" references in the Player class.
	 * @param port = desired port to be generated. TAKES THE PORT THAT WAS STORED IN PLAYER
	 */
	public TradePort(TradePort port)
	{
		locale = port.getLocale();
		money = (int) port.getMoney();
		inventory = port.getInventory();
	}
	/**
	 * Generates the starting credit balance of this TradePort based on its orbital's stability and hazard ratings
	 */
	public void genMoney()
	{
	//	money = (int)(BASE_MONEY *  (((float)locale.getOrbitalClass().GetStabilityRating()+10+locale.getOrbitalClass().GetOrganicRating())   /
	// 								((float)locale.getOrbitalClass().GetHZRating()))) + (this.hashCode()%1000);
		money = Collections.max(inventory).getModifiedPrice();
	}
	
	/* (non-Javadoc)
	 * @see Merchant#getMoney()
	 */
	public double getMoney()
	{
		return money;
	}
	
	public void genInventory()
	{
		Item.modifyAll(locale);
		for (int x = 0; x < locale.getOrbitalClass().GetProperties().length-1; x++)
		{
			if(inventory == null)
			{
				inventory = new Vector<Item>();
			}
			for(int y = 0; y < locale.getOrbitalClass().GetProperties()[x]+1; y++)
			{
				if (y == 0)
				{
					if (rand.nextInt(10) < 3)
					{
						inventory.add(Item.getRandomItem(x));
					}
				}
				else
				{
					inventory.add(Item.getRandomItem(x));
				}
			}
			
		}
		
/*
		for ( Item x : Ops.GetItems())
		{
			if (locale.getOrbitalClass().GetProperties()[x.getClassification()] <= 6)  //I changed from > to < now there are more items
			{
				if(inventory == null)
				{
					inventory = new Vector<Item>();
				}
				inventory.add(x);
			}
		}
*/	
	}
	
	/* (non-Javadoc)
	 * @see Merchant#getInventory()
	 */
	public Vector<Item> getInventory()
	{
	  
		return inventory;
	}
	
	/* (non-Javadoc)
	 * @see Merchant#modifyPrice()
	 */
	public void modifyPrice()
	{
		//I'm still thinking of a way to implement this RY
	}
	
	/* (non-Javadoc)
	 * @see Merchant#sale(double, Item)
	 */
	public void sale(double price, Item item)
	{
		money += price;
		inventory.removeElement(item);
	}
	
	/* (non-Javadoc)
	 * @see Merchant#purchase(double, Item)
	 */
	public void purchase(double price, Item item)
	{
		money -= price;
		inventory.add(item);
	}
	
	public Orbital getLocale()
	{
		return locale;
	}
}
