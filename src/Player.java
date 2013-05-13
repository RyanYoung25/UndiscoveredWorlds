


import java.util.Vector;

public class Player implements Merchant
{

  private String       name;
  private double       bank;
  private Vector<Item> inventory;
  private double       fuel;
  private Location		currentlocation;
  
  private Vector<TradePort> recentLocation;

  public Player()
  {
    this("Player", new Vector<Item>());
  }

  public Player(String name, Vector<Item> inventory)
  {
    this.name = name;
    bank = 500;
    fuel = 2.0;
    this.inventory = inventory;
  }

  @Override
  public double getMoney()
  {
    // TODO Auto-generated method stub
    return bank;
  }

  @Override
  public Vector<Item> getInventory()
  {
    // TODO Auto-generated method stub
    return inventory;
  }

  @Override
  public void modifyPrice()
  {
    //WE DON'T MODIFY THE PLAYER'S PRICE
    
  }

  @Override
  public void sale(double price, Item item)
  {
    bank = bank + price;
    inventory.remove(item);
    
  }

  @Override
  public void purchase(double price, Item item)
  {
    bank = bank - price;
    inventory.add(item);
  }

  @Override
  public String toString()
  {
    return inventory.toString();
  }
  
  public Location getLoc()
  {
	  return currentlocation;
  }
  
  public void setLoc(Location locale)
  {
	  currentlocation = locale;
  }
}
