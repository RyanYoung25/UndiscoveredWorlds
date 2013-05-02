
public class Galaxy extends Location
{
	private Cluster CurrentCluster;
	private Cluster[] Clusters;
	
	public Galaxy()
	{
		super();
		Clusters = new Cluster[MAX_CLUSTERS];
		
		for (int x = 0; x < Clusters.length; x++)
		{
			Clusters[x] = new Cluster(rand.nextLong());
		}
		PlaceClusters();
	}
	
	public Galaxy(long[] clusters)
	{
		super();
		Clusters = new Cluster[clusters.length-1];
		
		for (int x = 0; x < Clusters.length; x++)
		{
			Clusters[x] = new Cluster(rand.nextLong());
		}
		PlaceClusters();
	}
	
	public void Generate(int index)
	{
		StarSystem.SortOrbitals();
		CurrentCluster = new Cluster(Clusters[index].GetSeed(),Clusters[index].GetName(),Clusters[index].GetName2());
	}

	public void PlaceClusters()
	{
		double spacing = 360.0 / Clusters.length;
		int radius = 100;
		for (int z = 0; z < Clusters.length; z++)
		{
			Clusters[z].SetX((int)((radius + rand.nextInt(250)) * Math.cos(Math.toRadians(spacing*z))));
			Clusters[z].SetY((int)((radius + rand.nextInt(250)) * Math.sin(Math.toRadians(spacing*z))));
		}
	}
	
	public Cluster GetCluster()
	{
		return CurrentCluster;
	}
	
	public Cluster[] GetClusters()
	{
		return Clusters;
	}
	
	public Sector GetSector(int index)
	{
		return CurrentCluster.GetSector(index);
	}
	
	public StarSystem GetStar(Sector sector, int index)
	{
		return sector.GetStar(index);
	}
	
	public StarSystem GetStar(int sector, int index)
	{
		return GetSector(sector).GetStar(index);
	}
	
	public Orbital GetOrbital(StarSystem star, int index)
	{
		return star.GetOrbital(index);
	}
	
	public Orbital GetOrbital(int sector, int star, int index)
	{
		return GetOrbital(GetStar(sector,star),index);
	}
	
	public Moon GetMoon(Planet planet, int index)
	{
		return planet.GetMoon(index);
	}
	
	public String SectorDetails(int sector)
	{
		return Ops.getSector(sector, 0) + " Sector";
	}
	
	public Sector[] GetSectors ()
	{
		return CurrentCluster.GetSector();
	}
	
	public String StarDetails(int sector, int star)
	{
		return Ops.getSystem(sector,star) + "\n" + GetStar(sector,star).GetDetails();
	}
	
	public String OrbitalDetails(int sector, int star, int orbital)
	{
		return Ops.getPlanet(sector,star,orbital) + "\n" +GetOrbital(sector,star,orbital).GetDetails();
	}
	
	public String MoonDetails(int sector, int star, int orbital, int moon)
	{
		return Ops.getMoon(sector, star, orbital, moon) + " - " + GetMoon((Planet) GetOrbital(sector,star,orbital),moon);
	}
	
	public void DisplayCluster()
	{
		System.out.printf("%s\n", CurrentCluster.toString());
		for (int x = 0; x < GetCluster().GetSector().length; x++)
		{
			System.out.printf("%s\n", SectorDetails(x));
			for (int y = 0; y < GetSector(x).GetStar().length; y++)
			{
				System.out.printf("%s\n", StarDetails(x,y));
				for (int z = 0; z < GetStar(x,y).GetOrbital().length; z++)
				{
					System.out.printf("%s\n", OrbitalDetails(x,y,z));
					
					if (GetOrbital(x,y,z).getClass() == Planet.class)
					{
						for (int u = 0; u < ((Planet) GetOrbital(x,y,z)).GetMoon().length; u++)
						{
							System.out.printf("\t%s\n", MoonDetails(x, y, z, u));
						}
					}
					System.out.println();
				}
			}
		}
	}
}
