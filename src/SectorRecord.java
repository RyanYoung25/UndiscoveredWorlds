
public class SectorRecord 
{
	public String[] Names = new String[2];
	
	public SectorRecord(String[] names)
	{
		for(int x = 0; x < Names.length;x++ )
		{
			Names[x] = names[x];
		}
	}
}
