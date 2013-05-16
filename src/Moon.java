
/**
 * Moons provide the last structural tier after the Planet class for galaxy generation and manipulation.
 * @author Bryant
 *
 */
public class Moon extends Orbital
{
	/**
	 * Constructor.
	 * @param radius = relative distance of this moon from its planet.
	 * @param oclass = orbital classification of this moon.
	 * @param parent = classification of this moon's star.
	 */
	public Moon(int radius, byte oclass, int parent, Location target)
	{
		super(radius,oclass,parent, target);
		setOrbitalClass((byte) StarSystem.GenOrbitalClass(radius));
		if(getOrbitalClass().GetType()==1)
		{
			setOrbitalClass((byte)(rand.nextInt(4) + 15));
		}
	}

	/**
	 * Override Orbital's getOrbital method
	 * @param type = number corresponds to the Type variable within an OrbitalClass.
	 * @return String
	 */
	public String getOrbital()
	{
		if (getOrbitalClass().GetType() == 0)
			return "Moon";
		if (getOrbitalClass().GetType() == 1)
			return "Not Possible";
		if (getOrbitalClass().GetType() == 2)
			return "Ring";
		if (getOrbitalClass().GetType() == 3)
			return "Ring";
		else
			return "";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		String planet = getParent().toString();
		for(int j = 0; j <getParent().getChild().length; j++)
		{
			return planet + "-" + Planet.getMoonDetails(j);
		}
		return planet;
	}
}