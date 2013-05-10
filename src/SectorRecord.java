
/**
 * Simple class that stores two String variables. Used for Sector name information in Ops.
 * @author Bryant
 */
public class SectorRecord 
{
	public String[] Names = new String[2];
	
	/**
	 * Constructor. Takes two String variables in an array.
	 * @param names = two related String variables in an array
	 */
	public SectorRecord(String[] names)
	{
		for(int x = 0; x < Names.length;x++ )
		{
			Names[x] = names[x];
		}
	}
}
