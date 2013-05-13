
/**
 * This class provides information specific to individual star types as well as habitability information.
 * @author Bryant
 */
public class StarRecord 
{
	public static final double MIN_ORBITAL_FORMATION = 0.3;	// used for determining the actual minimum distance and orbital can form
	public static final double HZ_BAND_START = 0.725;		// used for calculating actual minimum HZ distance
	public static final double HZ_BAND_END = 3.0;			// used for calculating actual maximum HZ distance
	public static final double MAX_ORBITAL_FORMATION = 120;	// used for calculating maximum orbital formation from parent star
	
	private int StarID;				// Star ID number.
	private char Classification;	// Stellar classification (based of actual classification system).
	private String Color;			// Star color.
	private int MinTemp;			// Minimum temperature that a star in this class can have.
	private int MaxTemp;			// Maximum temperature that a star in this class can have.
	private double MinMass;			// Minimum mass that a star in this class can have.
	private double MaxMass;			// Maximum mass that a star in this class can have.
	private double MinRadius;		// Minimum radius that a star in this class can have.
	private double MaxRadius;		// Maximum radius that a star in this class can have.
	private double MinLuminosity;	// Minimum luminosity that a star in this class can have.
	private double MaxLuminosity;	// Maximum luminosity that a star in this class can have.
	private int Freq;				// Frequency that this particular class of star will appear in the galaxy. 
	
	/**
	 * Constructor.
	 * @param id = Star ID number.
	 * @param classification = Stellar classification
	 * @param color = Star color.
	 * @param mintemp = Minimum temperature that a star in this class can have.
	 * @param maxtemp = Maximum temperature that a star in this class can have.
	 * @param minmass = Minimum mass that a star in this class can have.
	 * @param maxmass = Maximum mass that a star in this class can have.
	 * @param minrad = Minimum radius that a star in this class can have.
	 * @param maxrad = Maximum radius that a star in this class can have.
	 * @param minlum = Minimum luminosity that a star in this class can have.
	 * @param maxlum = Maximum luminosity that a star in this class can have.
	 * @param freq = Frequency that this particular class of star will appear in the galaxy. 
	 */
	StarRecord(	int id, char classification, String color, int mintemp, int maxtemp, 
				double minmass, double maxmass, double minrad, double maxrad,double minlum,
				double maxlum,int freq)
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
	
	/**
	 * Used for calculating the orbit of an orbital around this class of star. Returns a double value reflecting distance in AU.
	 * @param starsubclass = a number from 0 to 9 reflecting subclassifications within a particular classification
	 * @param orbit = relative orbit value
	 * @return double
	 */
	public double getExactOrbit(byte starsubclass, int orbit)
	{
		double x = Math.sqrt(getExactLuminosity(starsubclass));
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
	
	/**
	 * Calculates and returns exact luminosity of this classification of star.
	 * @param starsubclass = a number from 0 to 9 reflecting subclassifications within a particular classification
	 * @return double
	 */
	public double getExactLuminosity(byte starsubclass)
	{
		return  (MinLuminosity + ((MaxLuminosity - MinLuminosity) / (starsubclass+1)));
	}
	
	/**
	 * Returns ID number of this classification of star.
	 * @return
	 */
	public int getID()
	{
		return StarID;
	}
	
	/**
	 * Returns classification letter of this star.
	 * @return char
	 */
	public char getClassification()
	{
		return Classification;
	}
	
	/**
	 * Returns a String representation of the color of this classification of star.
	 * @return String
	 */
	public String getColor()
	{
		return Color;
	}
	
	/**
	 * Returns minimum temperature of this star classification.
	 * @return int
	 */
	public int getMinTemp()
	{
		return MinTemp;
	}
	
	/**
	 * Returns maximum temperature of this star classification.
	 * @return int
	 */
	public int getMaxTemp()
	{
		return MaxTemp;
	}
	
	/**
	 * Returns minimum mass of this star classification.
	 * @return double
	 */
	public double getMinMass()
	{
		return MinMass;
	}
	
	/**
	 * Returns maximum mass of this star classification.
	 * @return double
	 */
	public double getMaxMass()
	{
		return MaxMass;
	}
	
	/**
	 * Returns minimum radius of this star classification.
	 * @return double
	 */
	public double getMinRadius()
	{
		return MinRadius;
	}
	
	/**
	 * Returns maximum radius of this star classification.
	 * @return double
	 */
	public double getMaxRadius()
	{
		return MaxRadius;
	}
	
	/**
	 * Returns minimum luminosity of this star classification.
	 * @return double
	 */
	public double getMinLuminosity()
	{
		return MinLuminosity;
	}
	
	/**
	 * Returns maximum luminosity of this star classification.
	 * @return double
	 */
	public double getMaxLuminosity()
	{
		return MaxLuminosity;
	}
	
	/**
	 * Returns likelihood that this star classification will appear during generation. Likelihood is a ratio of this number to the sum of all freq values.
	 * @return int
	 */
	public int getFreq()
	{
		return Freq;
	}
	
	/**
	 * Returns the habitability rating of the provided relative orbit.
	 * @param o = orbit (radius from this star)
	 * @return int
	 */
	public static int GetHabitability(int o)
	{
		if (o < StarSystem.MIN_HZ) 		// too hot
		{
			return 0;
		}
		else if (o > StarSystem.MAX_HZ) // too cold
		{
			return 1;
		}
		else							// just right
		{
			return 2;
		}
	}
}
