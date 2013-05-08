public class OrbitalRecord 
{	
	private char OrbitalID;
	private String Classification; 	// contains the name of an orbital classification
	private byte HZRating; 			// 1 = hot zone; 2 = habitable zone; 3 = cold zone; 4 = any zone; 5 = last orbit
	private byte Type;				// 0 = planet/moon; 1 = gas giant; 2 = ring; 3 = belt
	private boolean Planetary;	// whether or not orbital can orbit a planet
	private byte Hazard;			// 1 = habitable; 2 = requires domes; 3 = aerostat only; 4 = dangerous; 5 = uninhabitable
	private byte Appearance;		// 1 = Hell; 2 = Terrestrial; 3 = Barren; 4 = Ocean; 5 = Volcanic; 6 = Slush
	private byte[] Properties;		// values for various attributes on planet;
	
	public OrbitalRecord(char id,String Class, byte HZ, byte type, byte moony, byte haz, byte appearance)
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
	
	public OrbitalRecord(String[] record)
	{
		this	(record[0].charAt(0),
				record[1],
				Byte.parseByte(record[2]),
				Byte.parseByte(record[3]),
				Byte.parseByte(record[4]),
				Byte.parseByte(record[5]),
				Byte.parseByte(record[6]));
		Properties = new byte[record.length];
		for(int x = 6; x < record.length; x++)
		{
			Properties[x] = Byte.parseByte(record[x]);
		}

		//System.out.printf("%s\n", this);
	}
	
	public char GetID()
	{
		return OrbitalID;
	}
	
	public String GetClassification()
	{
		return Classification;
	}
	
	public byte GetHZRating()
	{
		return HZRating;
	}
	
	public byte GetType()
	{
		return Type;
	}
	
	public boolean IsPlanetary()
	{
		return Planetary;
	}
	
	public byte GetHazard()
	{
		return Hazard;
	}
	
	public byte GetAppearance()
	{
		return Appearance;
	}
	
	public byte GetAMRating()
	{
		return Properties[0];
	}
	
	public byte GetOrganicRating()
	{
		return Properties[1];
	}
	
	public byte GetCommonRating()
	{
		return Properties[2];
	}
	
	public byte GetUncommonRating()
	{
		return Properties[3];
	}
	
	public byte GetRareRating()
	{
		return Properties[4];
	}
	
	public byte GetStabilityRating()
	{
		return Properties[5];
	}
	
	public byte GetSilicateRating()
	{
		return Properties[6];
	}
	
	public byte GetFusionRating()
	{
		return Properties[7];
	}
	
	@Override
	public String toString()
	{
		return Classification;
	}
	
	public byte[] GetProperties()
	{
		return Properties;
	}
}


