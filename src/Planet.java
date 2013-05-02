
public class Planet extends Orbital
{
	private Moon[] Moons;
	
	public Planet(int radius, byte oclass, int parent)
	{
		super(radius, oclass, parent);
		Moons = new Moon[rand.nextInt(MAX_MOONS)];
		for(int x = 0; x < Moons.length; x++)
		{
			Moons[x] = new Moon(radius,oclass,parent);
		}
	}
	
	public Moon GetMoon(int index)
	{
		return GetMoon()[index];
	}
	
	public Moon[] GetMoon()
	{
		return Moons;
	}
	
	public static String getMoon(int orbit)
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
