
/**
 * The Planet class replaces an Orbital class within a StarSystem when indicated.
 * @author Bryant
 */
public class Planet extends Orbital
{
	private Moon[] Moons;	// Contains all information on orbitals orbiting this planet.
	
	/**
	 * Constructor. Handles moon generation.
	 * @param radius = relative distance of this planet from its parent star.
	 * @param oclass = classification of this planet.
	 * @param parent = classification of this planet's star.
	 */
	public Planet(int radius, byte oclass, int parent)
	{
		super(radius, oclass, parent);
		Moons = new Moon[rand.nextInt(MAX_MOONS)];
		for(int x = 0; x < Moons.length; x++)
		{
			Moons[x] = new Moon(radius,oclass,parent);
		}
	}
	
	/**
	 * Returns Moon object indicated from this Planet.
	 * @param index = index of desired moon.
	 * @return Moon
	 */
	public Moon GetMoon(int index)
	{
		return GetMoon()[index];
	}
	
	/**
	 * If no index value is provided, GetMoon will return a reference of all Moons within this Planet
	 * @return Moon[]
	 */
	public Moon[] GetMoon()
	{
		return Moons;
	}
	
	/**
	 * Returns a string representation of the orbit indicated for naming moons.
	 * @param orbit = number corresponds to an alphabetic character
	 * @return String
	 */
	public static String getMoonDetails(int orbit)
	{
		if (orbit < 26)
		{
			return String.format("-%s",String.valueOf((char) (orbit + 65)));
		}
		else
		{
			return String.format("-%d", orbit);
		}
	}
}
