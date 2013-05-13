
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
	 * Effectively used to override Orbital's getOrbital method (Note: does not actually do so)
	 * @param type = number corresponds to the Type variable within an OrbitalClass.
	 * @return String
	 */
	public static String getOrbital(int type)
	{
		if (type == 0)
			return "Moon";
		if (type == 1)
			return "Not Possible";
		if (type == 2)
			return "Ring";
		if (type == 3)
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
		if (port == null)
		{
			return getOrbitalClass().GetClassification() + " " + getOrbital(getOrbitalClass().GetType());
		}
		else
		{
			return getOrbitalClass().GetClassification() + " " + getOrbital(getOrbitalClass().GetType()) + " - Trade Port";
		}
	}
}