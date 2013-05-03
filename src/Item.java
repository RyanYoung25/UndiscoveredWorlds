

import java.io.Serializable;


public class Item implements Serializable
{
  private String name;
  private String description; 
  private int IDNumber;
  private int basePrice;
  private double modifier;
  private double volatility;
  private int classification;
  
  public Item()
  {
    this("", " ", 0, 0, 0, 0, 0);
  }
  
  public Item(String name, String description, int IdNumber,int basePrice,double modifier,double volatility, int classification)
  {
    setName(name);
    setDescription(description);
    setIDNumber(IdNumber);
    setBasePrice(basePrice);
    setModifier(modifier);
    setVolatility(volatility);
    setClassification(classification);
  }

  public Item(String[] rec)
  {
	  this	 (rec[0],
			  rec[1],
			  Integer.parseInt(rec[2]),
			  Integer.parseInt(rec[3]),
			  Double.parseDouble(rec[4]),
			  Double.parseDouble(rec[5]),
			  Integer.parseInt(rec[6]));
  }
  
  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public int getIDNumber()
  {
    return IDNumber;
  }

  public void setIDNumber(int iDNumber)
  {
    IDNumber = iDNumber;
  }

  public int getBasePrice()
  {
    return basePrice;
  }

  public void setBasePrice(int basePrice)
  {
    this.basePrice = basePrice;
  }

  public double getModifier()
  {
    return modifier;
  }

  public void setModifier(double modifier)
  {
    this.modifier = modifier;
  }

  public double getVolatility()
  {
    return volatility;
  }

  public void setVolatility(double volatility)
  {
    this.volatility = volatility;
  }

  public String getDescription()
  {
    return description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public int getClassification()
  {
    return classification;
  }

  public void setClassification(int classification)
  {
    this.classification = classification;
  }
  
  public String toString()
  {
    return name;
  }
}