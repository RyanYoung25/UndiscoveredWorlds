import java.util.Vector;

/**
 * This class is used to represent a trading port on or within a planet, moon, ring or belt.
 * @author Bryant, Ryan
 */
public class TradePort extends Location implements Merchant
{
	public static final int BASE_MONEY = 1000;
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
		genMoney();
    inventory = genInventory();
	}
	
	/**
	 * Constructor. Generates full TradePort for use in "recently visited ports" references in the Player class.
	 * @param port = desired port to be generated. TAKES THE PORT THAT WAS STORED IN PLAYER
	 */
	public TradePort(TradePort port)
	{
		locale = port.getLocale();
		money = (int) port.getMoney();
		inventory = getInventory();
	}
	/**
	 * Generates the starting credit balance of this TradePort based on its orbital's stability and hazard ratings
	 */
	public void genMoney()
	{
		money = BASE_MONEY + BASE_MONEY * (locale.getOrbitalClass().GetStabilityRating()/locale.getOrbitalClass().GetHZRating());
	}
	
	/* (non-Javadoc)
	 * @see Merchant#getMoney()
	 */
	public double getMoney()
	{
		return money;
	}
	
	public Vector<Item> genInventory()
	{
		Vector<Item> temp = new Vector<Item>();
		for ( Item x : Ops.GetItems())
		{
			if (locale.getOrbitalClass().GetProperties()[x.getClassification()] >= 6)
			{
				temp.addElement(x);
			}
		}
		return temp;
	}//Why are we returning a vector instead of just putting it on the inventory vector?? RY
	
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
