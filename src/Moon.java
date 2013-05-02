
public class Moon extends Orbital
{
	public Moon(int radius, byte oclass, int parent)
	{
		super(radius,oclass,parent);
		setOrbitalClass((byte) StarSystem.GenOrbitalClass(radius));
		if(getOrbitalClass().GetType()==1)
		{
			setOrbitalClass((byte)(rand.nextInt(4) + 15));
		}
	}

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