import java.awt.image.BufferedImage;

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
		Picture = Pics.getSectorPic(rand.nextInt(10));
		StarSystems = new StarSystem[1 + rand.nextInt(MAX_SYSTEMS)];
		for (int z = 0; z < StarSystems.length; z++)
		{
			StarSystems[z] = new StarSystem((byte)z,Name,(byte)rand.nextInt(7));
		}
	}
	
	public void Generate()
	{
		StarSystems = new StarSystem[1 + rand.nextInt(MAX_SYSTEMS)];
		double spacing = 360.0 / StarSystems.length;
		int radius = 100;
		for (int z = 0; z < StarSystems.length; z++)
		{
			StarSystems[z] = new StarSystem((byte)z,Name,(byte)rand.nextInt(7));
			StarSystems[z].SetX((int)(((radius + rand.nextInt(250)) * Math.cos(Math.toRadians(spacing*z)))));
			StarSystems[z].SetY((int)(((radius + rand.nextInt(250)) * Math.sin(Math.toRadians(spacing*z)))));
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
	
	public void SetX(int X)
	{
		x = X;
	}
	
	public void SetY(int Y)
	{
		y = Y;
	}
}
