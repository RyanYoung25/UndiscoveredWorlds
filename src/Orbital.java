import java.awt.Image;
import java.awt.image.BufferedImage;


/**
 * The Orbital class provides the next structural tier after the StarSystem class for galaxy generation and manipulation.
 * @author Bryant
 */
public class Orbital extends Location 
{
	protected static final int OFFSET = 25;
	protected static final int MULTI = 25;
	protected static final int MIN_DISTANCE = 100;
	
	protected int Radius;					// Relative distance from parent, be it a star or planet.
	protected OrbitalRecord OrbitalClass;	// References information specific to this Orbital's classification.
	protected StarRecord ParentStar;		// References information specific to this Orbital's parent star.
	protected TradePort port;				// References port on or orbiting this Orbital if applicable.

	/**
	 * Constructor. Randomly determines if a port is attached to this Orbital.
	 * @param radius = relative distance from parent, be it a star or planet.
	 * @param oclass = orbital classification. Corresponds to an OrbitalRecord.
	 * @param parent = stellar classification. Corresponds to a StarRecord.
	 */
	public Orbital(int radius, byte oclass, int star,Location target) 
	{
		Radius = radius;
		setOrbitalClass(oclass);
		Picture = GetOrbitalPic();
		ParentStar = Ops.getStarDetails(star);
		parent = target;
		if (rand.nextInt(TradePort.CHANCE) >= OrbitalClass.GetHazard()) 
		{
			port = new TradePort(this);
		}
	}

	/**
	 * 
	 * Returns OrbitalRecord information specific to this Orbital.
	 * @return
	 */
	public OrbitalRecord getOrbitalClass() 
	{
		return OrbitalClass;
	}

	/**
	 * Returns the relative distance of this Orbital from its parent.
	 * @return int
	 */
	public int getRadius() 
	{
		return Radius;
	}

	/**
	 * Used in conjunction with the planetary classification to produce a full String value (i.e. Hell Planet, Dust Ring, etc.)
	 * @return String
	 */
	public String getOrbital() 
	{
		switch (OrbitalClass.GetType()) {
		case 0:
			return "Planet";
		case 1:
			return "Gas Giant";
		case 2:
			return "Ring";
		case 3:
			return "Belt";
		default:
			return "";
		}
	}

	/**
	 * Sets the Orbital to the desired classification
	 * @param oclass = value corresponds with an OrbitalRecord.
	 */
	public void setOrbitalClass(byte oclass) 
	{
		OrbitalClass = Ops.getOrbitalClasses(oclass);
	}
	
	public Location getParent()
	{
	  return parent;
	}

	/**
	 * Returns reference of the TradePort object attached to this Orbital if applicable.
	 * @return TradePort
	 */
	public TradePort getTradePort() 
	{
		return port;
	}

	/**
	 * Returns full classification of this Orbital. Appends "Trade Port" when applicable.
	 * @return String
	 */
	public String GetDetails() 
	{
		return String.format("Class: %s - %s %s",
				OrbitalClass.GetID(), OrbitalClass.GetClassification(),
				getOrbital());
	}
	
	/**
	 * Assigns an appropriate image to this Orbital based on classification and type.
	 * @return
	 */
	public BufferedImage GetOrbitalPic()
	{
		switch(OrbitalClass.GetType())
		{
			case 0:
				switch(OrbitalClass.GetAppearance())
				{
					case 1:
						return Pics.getHellPic(rand.nextInt(5));
					case 2:
						return Pics.getEarthyPic(rand.nextInt(5));
					case 3:
						return Pics.getBarrenPic(rand.nextInt(15));
					case 4:
						return Pics.getOceanPic(rand.nextInt(5));
					case 5:
						return Pics.getLavaPic(rand.nextInt(5));
					case 6:
						return Pics.getSlushPic(rand.nextInt(5));
				}
			case 1:
				return Pics.getGasPic(rand.nextInt(10));
			case 2:
				return Pics.getRingPic(rand.nextInt(10));
			case 3:
				return Pics.getBeltPic(rand.nextInt(3));
			default:
				return Pics.getRingPic(9);
		}
	}
	
	public int whatAmI()
	{
	  return 4;
	}
	
	public int GetX()
	  {
		for (int x = 0; x < getParent().getChild().length; x++)
		{
			if(this == getParent().getChild(x))
			{
				return (int)(  ((x*MULTI)+MIN_DISTANCE) * Math.cos(Math.toRadians((x+1)*(360/getParent().getChild().length))))-OFFSET;
			}
		}
	    return 0;
	  }
	  
	  public int GetY()
	  {
		  for (int x = 0; x < getParent().getChild().length; x++)
			{
				if(this == getParent().getChild(x))
				{
					return (int)( ((x*MULTI)+MIN_DISTANCE) * Math.sin(Math.toRadians((x+1)*(360/getParent().getChild().length))))-OFFSET;
				}
			} 
	    return 0;
	  }
	  
	  @Override
	  public String toString()
	  {
		  for(int i = 0; i < getParent().getChild().length; i++)
		  {
			  if(getParent().getChild(i) == this)
			  {
				  return getParent() + " " + FileOps.RomanNum(i+1);
			  }
		  }
		  return getParent().toString();
	  }
	  
	@Override
	public BufferedImage GetCenterImage()
	{
		if(getParent().getClass() == StarSystem.class)
		{
			return getParent().GetPic();
		}
		return super.GetCenterImage();
	}
	
	@Override
	public Image GetPic(int x)
	  {
		  return PictureAlbum.getScaledSquareImage(Picture, x);
	  }
	
	@Override
	public Image GetNavImage()
	{
		if(this.getTradePort() == null)
		{
			return PictureAlbum.getScaledSquareImage(Pics.getPort(),1); 
		}
		return PictureAlbum.getScaledSquareImage(Pics.getPort(),20);
	}
}
