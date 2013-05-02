
public class Sector extends Location
{
	private StarSystem[] StarSystems;
	private int Name;
	private int x;
	private int y;

	public Sector(int name)
	{
		super();
		Name = name;
		StarSystems = new StarSystem[1 + rand.nextInt(MAX_SYSTEMS)];
		for (int z = 0; z < StarSystems.length; z++)
		{
			StarSystems[z] = new StarSystem((byte)z,Name,(byte)rand.nextInt(7));
		}
	}
	
	@Override
	public String toString()
	{
		return Ops.getSector(Name,0) + " Sector";
	}
	
	public StarSystem GetStar(int index)
	{
		return GetStar()[index];
	}
	
	public StarSystem[] GetStar()
	{
		return StarSystems;
	}
	
	
	public int GetX()
	{
		return x;
	}
	
	public int GetY()
	{
		return y;
	}
	
	public void SetX(byte X)
	{
		x = X;
	}
	
	public void SetY(byte Y)
	{
		y = Y;
	}
}
