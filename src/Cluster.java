public class Cluster extends Location
{
	private Sector[] Sectors;
	private long seed;
	private short Name;
	private short Name2;
	private int x;
	private int y;

	public Cluster(long s)
	{
		super();
		seed = s;
		Name = (short) rand.nextInt(Ops.getCluster1NameCount());
		Name2 = (short) rand.nextInt(Ops.getCluster2NameCount());
	}
	
	public Cluster(long s, short name, short name2)
	{
		super();
		seed = s;
		Name = name;
		Name2 = name2;
		Generate();
	}
	
	public void Generate()
	{
		Sectors = new Sector[1 + rand.nextInt(MAX_SECTORS)];
		double spacing = 360.0 / Sectors.length;
		int radius = 100;
		for (int z = 0; z < Sectors.length; z++)
		{
			Sectors[z] = new Sector(z);
			Sectors[z].SetX((byte)(((radius + rand.nextInt(250)) * Math.cos(Math.toRadians(spacing*z)))));
			Sectors[z].SetY((byte)(((radius + rand.nextInt(250)) * Math.sin(Math.toRadians(spacing*z)))));
		}
	}
	
	@Override
	public String toString()
	{
		return Ops.getCluster(Name, Name2);
	}
	
	public Sector GetSector(int index)
	{
		return GetSector()[index];
	}
	
	public Sector[] GetSector()
	{
		return Sectors;
	}
	
	public long GetSeed()
	{
		return seed;
	}
	
	public short GetName()
	{
		return Name;
	}
	
	public short GetName2()
	{
		return Name2;
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
