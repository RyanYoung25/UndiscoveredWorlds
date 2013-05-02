import java.util.Vector;

public class TradePort extends Location implements Merchant
{
	public static final int BASE_MONEY = 1000;
	public static final int CHANCE = 5;
	private Orbital locale;
	private int money;
	
	public TradePort(Orbital loc)
	{
		locale = loc;
		genMoney();
	}
	
	public void genMoney()
	{
		money = BASE_MONEY * (locale.getOrbitalClass().GetStabilityRating()/locale.getOrbitalClass().GetHZRating());
	}
	
	public double getMoney()
	{
		return money;
	}
	
	public Vector<Item> getInventory()
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
	}
	
	public void modifyPrice()
	{
		
	}
	
	public void sale(double price, Item item)
	{
		
	}
	
	public void purchase(double price, Item item)
	{
		
	}
}
