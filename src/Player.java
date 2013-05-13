
import java.util.Vector;

public class Player implements Merchant
{

  private String            name;
  private double            bank;
  private Vector<Item>      inventory;
  private double            fuel;
  private Location          currentlocation;
  private static final int  MAX_PORT_HISTORY = 15;

  private Vector<TradePort> recentLocations;

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
    recentLocations = new Vector<TradePort>();
    
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
    // WE DON'T MODIFY THE PLAYER'S PRICE

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

  public void addPort(TradePort port)
  {
    if (recentLocations.size() < MAX_PORT_HISTORY && !recentLocations.contains(port))
    {
      recentLocations.add(port);
    } else
    {
      recentLocations.remove(0);
      recentLocations.add(port);
    }
  }

  public boolean hasBeenHere(Location locale)
  {
    boolean test = false;
    for (int i = 0; i < recentLocations.size(); i++)
    {
      if (recentLocations.get(i).getLocale().equals(locale))
      {
        test = true;
      }
    }
    return test;
  }

  public Location getLoc()
  {
    return currentlocation;
  }

  public void setLoc(Location locale)
  {
    currentlocation = locale;
  }

  public TradePort getPort(Location locale)
  {
    TradePort port = null;
    for(int i = 0; i < recentLocations.size(); i++)
    {
      if (recentLocations.get(i).getLocale().equals(locale))
      {
        port = recentLocations.get(i);
      }
    }
    return port;
  }
}
