/**
 * This class provides information specific to individual Orbital types.
 * @author Bryant
 */
public class OrbitalRecord 
{	
	private char OrbitalID;			// Orbital ID; corresponds to a letter of the alphabet.
	private String Classification; 	// contains the name of an orbital classification.
	private int HZRating; 			// 1 = hot zone; 2 = habitable zone; 3 = cold zone; 4 = any zone; 5 = last orbit
	private int Type;				// 0 = planet/moon; 1 = gas giant; 2 = ring; 3 = belt
	private boolean Planetary;		// whether or not orbital can orbit a planet.
	private int Hazard;			// 1 = habitable; 2 = requires domes; 3 = aerostat only; 4 = dangerous; 5 = uninhabitable
	private int Appearance;		// 1 = Hell; 2 = Terrestrial; 3 = Barren; 4 = Ocean; 5 = Volcanic; 6 = Slush
	private int[] Properties;		// values for various attributes on planet;
	
	/**
	 * Constructor. Unless you're masochistic, it's probably better to just feed an array of Strings in the other constructor.
	 * @param id = Orbital ID number.
	 * @param Class = Orbital classification.
	 * @param HZ = Orbital habitability information.
	 * @param type = Orbital type.
	 * @param moony = Determines whether or not an orbital can orbit a planet.
	 * @param haz = hazard rating
	 * @param appearance = used for determining image information.
	 */
	public OrbitalRecord(char id,String Class, int HZ, int type, int moony, int haz, int appearance)
	{
		OrbitalID = id;
		Classification = Class;
		HZRating = HZ;
		Type = type;
		if(moony == 0)
		{
			Planetary = false;
		}
		else
		{
			Planetary = true;
		}	
		Hazard = haz;
		Appearance = appearance;
	}
	
	/**
	 * Constructor. Takes an array of String variables. Used in conjunction with the FileOps class.
	 * @param record = array of strings used for parsing into the record instance.
	 */
	public OrbitalRecord(String[] record)
	{
		this	(record[0].charAt(0),
				record[1],
				Integer.parseInt(record[2]),
				Integer.parseInt(record[3]),
				Integer.parseInt(record[4]),
				Integer.parseInt(record[5]),
				Integer.parseInt(record[6]));
		Properties = new int[record.length-6];
		for(int x = 7; x < record.length; x++)
		{
			Properties[x-7] = Integer.parseInt(record[x]);
		}

		//System.out.printf("%s\n", this);
	}
	
	/**
	 * Returns Orbital's ID.
	 * @return char
	 */
	public char GetID()
	{
		return OrbitalID;
	}
	
	/**
	 * Returns Orbital classification in String form (i.e. Terrestrial, Ocean, etc.)
	 * @return String
	 */
	public String GetClassification()
	{
		return Classification;
	}
	
	/**
	 * Returns habitability rating for OrbitalClass.
	 * @return byte
	 */
	public int GetHZRating()
	{
		return HZRating;
	}
	
	/**
	 * Returns type of this Orbital. 0 = planet/moon; 1 = gas giant; 2 = ring; 3 = belt
	 * @return byte
	 */
	public int GetType()
	{
		return Type;
	}
	
	/**
	 * Returns whether or not Orbital can orbit a planet.
	 * @return boolean
	 */
	public boolean IsPlanetary()
	{
		return Planetary;
	}
	
	/**
	 * Returns hazard rating for this Orbital.
	 * 	1 = habitable; 2 = requires domes; 3 = aerostat only; 4 = dangerous; 5 = uninhabitable
	 * @return byte
	 */
	public int GetHazard()
	{
		return Hazard;
	}
	
	/**
	 * Returns value that corresponds to an image or range of images for determining the graphical representation of an orbital
	 * @return byte
	 */
	public int GetAppearance()
	{
		return Appearance;
	}
	
	/**
	 * Returns availability of antimatter within this orbital.
	 * @return byte
	 */
	public int GetAMRating()
	{
		return Properties[0];
	}
	
	/**
	 * Returns availability of organic materials within this orbital.
	 * @return byte
	 */
	public int GetOrganicRating()
	{
		return Properties[1];
	}
	
	/**
	 * Returns availability of common ores within this orbital.
	 * @return byte
	 */
	public int GetCommonRating()
	{
		return Properties[2];
	}
	
	/**
	 * Returns availability of uncommon ores within this orbital.
	 * @return byte
	 */
	public int GetUncommonRating()
	{
		return Properties[3];
	}
	
	/**
	 * Returns availability of rare ores within this orbital.
	 * @return byte
	 */
	public int GetRareRating()
	{
		return Properties[4];
	}
	
	/**
	 * Returns stability rating of this orbital.
	 * @return byte
	 */
	public int GetStabilityRating()
	{
		return Properties[5];
	}
	
	/**
	 * Returns availability of silicate material within this orbital.
	 * @return byte
	 */
	public int GetSilicateRating()
	{
		return Properties[6];
	}
	
	/**
	 * Returns availability of materials for use in fusion power generation within this orbital.
	 * @return byte
	 */
	public int GetFusionRating()
	{
		return Properties[7];
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return Classification;
	}
	
	/**
	 * Returns availability of all materials within this orbital.
	 * @return byte[]
	 */
	public int[] GetProperties()
	{
		return Properties;
	}
}


