
public class StarRecord {
	public static final double MIN_ORBITAL_FORMATION = 0.3;
	public static final double HZ_BAND_START = 0.725;
	public static final double HZ_BAND_END = 3.0;
	public static final double MAX_ORBITAL_FORMATION = 120;
	
	private int StarID;
	private char Classification;
	private String Color;
	private int MinTemp;
	private int MaxTemp;
	private double MinMass;
	private double MaxMass;
	private double MinRadius;
	private double MaxRadius;
	private double MinLuminosity;
	private double MaxLuminosity;
	private int Freq;
	
	StarRecord(int id, char classification, String color, int mintemp, int maxtemp, double minmass, double maxmass, double minrad, double maxrad,double minlum,double maxlum,int freq)
	{
		StarID = id;
		Classification = classification;
		Color = color;
		MinTemp = mintemp;
		MaxTemp = maxtemp;
		MinMass = minmass;
		MaxMass = maxmass;
		MinRadius = minrad;
		MaxRadius = maxrad;
		MinLuminosity = minlum;
		MaxLuminosity = maxlum;
		Freq = freq;
	}
	
	public double getExactOrbit(byte starclass, int orbit)
	{
		double x = Math.sqrt(getExactLuminosity(starclass));
		double y = orbit/100;
		switch(GetHabitability(orbit))
		{
			case 0: 
				return  (x * MIN_ORBITAL_FORMATION) + (HZ_BAND_START * y);
			case 1:
				return  (x * HZ_BAND_START) + (HZ_BAND_END * y);
			case 2:
				return  (x * HZ_BAND_END) + (MAX_ORBITAL_FORMATION * y);
		}
		return 0.0;
	}
	
	public double getExactLuminosity(byte starsubclass)
	{
		return  (MinLuminosity + ((MaxLuminosity - MinLuminosity) / (starsubclass)));
	}
	
	public int getID()
	{
		return StarID;
	}
	
	public char getClassification()
	{
		return Classification;
	}
	
	public String getColor()
	{
		return Color;
	}
	
	public int getMinTemp()
	{
		return MinTemp;
	}
	
	public int getMaxTemp()
	{
		return MaxTemp;
	}
	
	public double getMinMass()
	{
		return MinMass;
	}
	
	public double getMaxMass()
	{
		return MaxMass;
	}
	public double getMinRadius()
	{
		return MinRadius;
	}
	public double getMaxRadius()
	{
		return MaxRadius;
	}
	public double getMinLuminosity()
	{
		return MinLuminosity;
	}
	public double getMaxLuminosity()
	{
		return MaxLuminosity;
	}
	
	public int getFreq()
	{
		return Freq;
	}
	
	public static int GetHabitability(int o)
	{
		if (o < StarSystem.MIN_HZ)
		{
			return 0;
		}
		else if (o > StarSystem.MAX_HZ)
		{
			return 1;
		}
		else
		{
			return 2;
		}
	}
}
