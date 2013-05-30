import java.util.Collections;
import java.util.Vector;

public class Player implements Merchant
{

  private String            name;
  private double            bank;
  private Vector<Item>      inventory;
  private Vector<Item>      brokenDrive;
  private int               fuel;
  private Location          currentlocation;
  private static final int  MAX_INVENTORY_NUMBER = 50;
  private static final int  MAX_PORT_HISTORY = 15;
  private static final int  FUEL_CONSTANT    = 25;
  private static final int  MAX_FUEL_LEVEL   = 100;
  private static final int  FUEL_CONSUMPTION = 5;

  private Vector<TradePort> recentLocations;

  /**
   * default constructor
   */
  public Player()
  {
    this("Player", new Vector<Item>());
  }

  /**
   * The constructor that passes an actual inventory and name
   * @param name name of player
   * @param inventory  inventory
   */
  public Player(String name, Vector<Item> inventory)
  {
    this.name = name;
    bank = 500;
    fuel = 50;
    this.inventory = inventory;
    Collections.sort(this.inventory);
    recentLocations = new Vector<TradePort>();
    brokenDrive = new Vector<Item>();

  }

  /**
   * @return The player's money
   */
  @Override
  public double getMoney()
  {
    return bank;
  }

  /**
   * Returns the players inventory
   * @return The Vector inventory
   */
  @Override
  public Vector<Item> getInventory()
  {
    return inventory;
  }

  /**
   * does nothing in player
   */
  @Override
  public void modifyPrice()
  {
    // WE DON'T MODIFY THE PLAYER'S PRICE

  }

  /**
   * @param price The price the item was sold for
   * @param item  The item sold
   */
  @Override
  public void sale(double price, Item item)
  {
    bank = bank + price;
    inventory.remove(item);

  }

  /**
   * @param price The price the item was bought for
   * @param item  The item bought
   */
  @Override
  public void purchase(double price, Item item)
  {
    bank = bank - price;
    inventory.add(item);
  }
  
  /**
   * Checks to see if another item can fit in the inventory.
   * @return true if there is room for another item.
   */
  public boolean canFitAnotherItem()
  {
    if(inventory.size() + 1 >= MAX_INVENTORY_NUMBER)
    {
      return false;
    }
    
    return true;
  }

  /**
   * @return A string representation of the inventory.
   */
  @Override
  public String toString()
  {
    return inventory.toString();
  }

  public void addPort(TradePort port)
  {
    if (recentLocations.size() < MAX_PORT_HISTORY
        && !recentLocations.contains(port))
    {
      recentLocations.add(port);
    } else
    {
      recentLocations.remove(0);
      recentLocations.add(port);
    }
  }
  
  /**
   * checks if the player has been to this location
   * @param locale the location to be checked
   * @return True if the player has been to that location. false if they haven't.
   */
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

  /**
   * Try to get a trade port from a passed location
   * @param locale the current location you are at
   * @return The trade port that you were previously at
   */
  public TradePort getPort(Location locale)
  {
    TradePort port = null;
    for (int i = 0; i < recentLocations.size(); i++)
    {
      if (recentLocations.get(i).getLocale().equals(locale))
      {
        port = recentLocations.get(i);
      }
    }
    return port;
  }

  /**
   * Decrements the fuel by a constant.
   */
  public void useFuel()
  {
    fuel -= FUEL_CONSUMPTION;
  }

  /**
   * Try to use the item passed. Different things will happen with different used items
   * 
   * @param item - the item to be used
   * @return A string message to be displayed elsewhere.
   */
  public String use(Item item)
  {
    if (item.getIDNumber() == 4)
    {
      if (fuel + FUEL_CONSTANT <= MAX_FUEL_LEVEL)
      {
        fuel += FUEL_CONSTANT;
      } else
      {
        fuel = MAX_FUEL_LEVEL;
      }
      inventory.remove(item);

      return String.format("Fuel Level was increased to: %d", getFuelLevel());
    }
    if (item.getIDNumber() == 28) // Antimatter fully refuels the ship
    {
    	fuel = MAX_FUEL_LEVEL;
    	inventory.remove(item);
    	return String.format("Fuel Level was increased to: %d", getFuelLevel());
    }
    if (item.getIDNumber() == 32 && !brokenDrive.contains(item))
    {
      brokenDrive.add(item);
      inventory.remove(item);
      if(tryToWin())
      {
        return "You have rebuilt your drive click the back button in the main panel to win the game!!";
      }
      return String.format("You added %s to your broken drive", item.getName());
    }
    if (item.getIDNumber() == 31 && !brokenDrive.contains(item))
    {
      brokenDrive.add(item);
      inventory.remove(item);
      if(tryToWin())
      {
        return "You have rebuilt your drive click the back button in the main panel to win the game!!";
      }
      return String.format("You added %s to your broken drive", item.getName());
    }
    if (item.getIDNumber() == 30 && !brokenDrive.contains(item))
    {
      brokenDrive.add(item);
      inventory.remove(item);
      if(tryToWin())
      {
        return "You have rebuilt your drive click the back button in the main panel to win the game!!";
      }
      return String.format("You added %s to your broken drive", item.getName());
    }
    
    return "You already have that in your drive";
  }

  /**
   * check to see if the player can win
   * @return true if the player wins, false if they don't
   */
  public boolean tryToWin()
  {
    if (brokenDrive.size() == 3)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  
  /**
   * Drops the item from the inventory
   * @param item item to be dropped 
   */
  public void drop(Item item)
  {
    inventory.remove(item);
  }


  /**
   * Returns the fuel level of the player
   * @return an integer fuel level of the player
   */
  public int getFuelLevel()
  {	  
    return fuel;
  }
  
  /**
   * Checks fuel then uses fuel in inventory if needed.
   */
  public boolean hasFuel()
  {
	  if (fuel>0)
	  {
		  return true;
	  }
	  for(Item x : inventory)
	  {
		  if(x.getIDNumber() == 4)
		  {
			  this.use(x);
			  return true;
		  }
	  }
	  return false;
  }
  
  public void revertPrices()
  {
	  for(int x = 0; x < inventory.size(); x++)
	  {
		  inventory.get(x).unModify();
	  }
  }

  public boolean canUseThisPart(Item item)
  {
    
    return !brokenDrive.contains(item);
  }
  
}
