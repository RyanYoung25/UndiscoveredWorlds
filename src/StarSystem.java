import java.util.ArrayList;

public class StarSystem extends Location
{	
	public static int MIN_HZ = 470;
	public static int MAX_HZ = 1800;
	private static ArrayList<Integer> HotOrbitals = new ArrayList<Integer>();
	private static ArrayList<Integer> HZOrbitals = new ArrayList<Integer>();
	private static ArrayList<Integer> ColdOrbitals = new ArrayList<Integer>();
	
	private Orbital[] Orbitals;
	private byte Star;
	private StarRecord StarClass;
	private byte x = (byte)((rand.nextInt(200))-100);
	private byte y = (byte)((rand.nextInt(200))-100);
	
	public StarSystem(byte star, int sector, byte starclass)
	{
		super();
		Orbitals = new Orbital[1 + rand.nextInt(MAX_PLANETS)];
		Star = star;
		StarClass = Ops.getStarDetails(starclass);
		int u = 10000 / Orbitals.length;
		int z = u;
		for (int v = 0; v < Orbitals.length; v++)
		{
			GenOrbital(rand.nextInt(z), v);
			z += u;
		}
	}

	public void GenOrbital(int radius, int index)
	{
		byte oclass = (byte) GenOrbitalClass(radius);	
		if (Ops.getOrbitalClasses(oclass).GetType() < 2) // is planet
		{
			Orbitals[index] = new Planet(radius, oclass, StarClass.getID());
		}
		else // is ring or belt
		{
			Orbitals[index] = new Ring(radius,oclass,StarClass.getID());
		}
	}
	
	public static int GenOrbitalClass(int radius)
	{
		if (radius < MIN_HZ)
		{
			return (HotOrbitals.get(rand.nextInt(HotOrbitals.size())));
		}
		else if ( radius > MAX_HZ)
		{
			return (ColdOrbitals.get(rand.nextInt(ColdOrbitals.size())));
		}
		else
		{
			return (HZOrbitals.get(rand.nextInt(HZOrbitals.size())));
		}
	}
	
	@Override
	public String toString()
	{
		return Ops.getSystem(Star);
	}
	
	public Orbital GetOrbital(int index)
	{
		return GetOrbital()[index];
	}
	
	public Orbital[] GetOrbital()
	{
		return Orbitals;
	}
	
	public byte GetX()
	{
		return x;
	}
	
	public byte GetY()
	{
		return y;
	}
	
	public StarRecord GetStarClass()
	{
		return StarClass;
	}
	
	public String GetDetails()
	{
		int subclass = (Math.abs(x) % 10);
		return String.format("Class: %s%d - %s Star\n" +
							 "Mass: %.2f solar masses\n" +
							 "Radius: %.2f solar radii\n" +
							 "Temp: %d K\n" +
							 "Luminosity: %.2f",
							 StarClass.getClassification(), GetSubClass(subclass), StarClass.getColor(),
							 StarClass.getMinMass() + ((StarClass.getMaxMass() - StarClass.getMinMass())/(subclass+1)),
							 StarClass.getMinRadius() + ((StarClass.getMaxRadius() - StarClass.getMinRadius())/(subclass+1)),
							 StarClass.getMinTemp() + ((StarClass.getMaxTemp() - StarClass.getMinTemp())/(subclass+1)),
							 StarClass.getExactLuminosity((byte)subclass));
	}
	
	public static int GetSubClass(int subclass)
	{
		return 9 - ((subclass) % 10);
	}
	
	public static byte GenStarClass()
	{
		return (byte) ((Ops.getStarClass(rand.nextInt(Ops.getStarFreqCeiling()))) + rand.nextInt(10)); 
	}
	
	public static void SortOrbitals()
	{
		for (int x = 0; x < Ops.getOrbitalClasses().size(); x++)
		{
			switch (Ops.getOrbitalClasses(x).GetHZRating())
			{
				case 1:
					HotOrbitals.add(x);
					break;
				case 2:
					HZOrbitals.add(x);
					break;
				case 3:
					ColdOrbitals.add(x);
					break;
				case 4:
					HotOrbitals.add(x);
					HZOrbitals.add(x);
					ColdOrbitals.add(x);
					break;
			}
		}
	}
}
