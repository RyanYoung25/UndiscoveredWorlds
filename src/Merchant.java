


import java.util.Vector;


public abstract interface Merchant
{
  
  public abstract double getMoney();
  
  public abstract Vector<Item> getInventory();
  
  public abstract void modifyPrice();
  
  public abstract void sale(double price, Item item);
  
  public abstract void purchase(double price, Item item);
}
