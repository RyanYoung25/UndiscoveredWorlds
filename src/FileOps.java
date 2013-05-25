import java.io.File;
import java.io.FileNotFoundException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;

public class FileOps 
{
	private Scanner input;
	private ArrayList<String> CivShipNames = new ArrayList<String>();
	private ArrayList<String> CivShipAbbr = new ArrayList<String>();
	private ArrayList<String> ClusterNames = new ArrayList<String>();
	private ArrayList<String> ClusterNames2 = new ArrayList<String>();
	private ArrayList<String> GovNames = new ArrayList<String>();
	private ArrayList<String> GovAbbr = new ArrayList<String>();
	private ArrayList<String> MilShipNames = new ArrayList<String>();
	private ArrayList<String> MilShipAbbr = new ArrayList<String>();
	private ArrayList<SectorRecord> SectorNames = new ArrayList<SectorRecord>();
	private ArrayList<String> ShipNames = new ArrayList<String>();
	private ArrayList<String> SystemNames = new ArrayList<String>();
	private ArrayList<String> WeaponNames = new ArrayList<String>();
	private ArrayList<OrbitalRecord> OrbitalClasses = new ArrayList<OrbitalRecord>();
	private ArrayList<StarRecord> StarClasses = new ArrayList<StarRecord>();
	private ArrayList<Item> Items = new ArrayList<Item>();
	public static final String NAMES_PATH = "Names" + File.separator; 
	private int StarFreqCeiling;
	
	public FileOps()
	{
		loadNames("Civilian.txt", CivShipNames);
		loadNames("CivAbbr.txt", CivShipAbbr);
		loadNames("Clusters.txt", ClusterNames);
		loadNames("Clusters2.txt", ClusterNames2);
		loadNames("Governments.txt", GovNames);
		loadNames("GovAbbr.txt", GovAbbr);
		loadNames("Military.txt", MilShipNames);
		loadNames("MilAbbr.txt", MilShipAbbr);
		load2Names("Sectors.txt", SectorNames);
		loadNames("Ships.txt", ShipNames);
		loadNames("Systems.txt", SystemNames);
		loadNames("Weapons.txt", WeaponNames);
		loadStarClasses("StarClasses.txt",StarClasses);
		loadOrbitalClasses("OrbitClasses.txt", OrbitalClasses);
		loadCommodities("Commodities.txt");
	}
	
	public void SectorRandomizer(long seed)
	{
		Collections.shuffle(SectorNames, new Random(seed));
	}
	
	public void SystemRandomizer(long seed)
	{
		Collections.shuffle(SystemNames, new Random(seed));
	}
	
	public void loadNames(String fileName, ArrayList<String> Strings)
	{
		openFile(fileName);
		readNames(Strings);
		closeFile();
	}
	
	public void load2Names(String fileName, ArrayList<SectorRecord> Sectors)
	{
		openFile(fileName);
		read2Names(Sectors);
		closeFile();
	}
	
	//inputs star classes from file
	public void loadStarClasses(String fileName, ArrayList<StarRecord> records)
	{
		String[] record;
		int count = 0;
		int agg = 0;
		
		openFile(fileName);

		try
		{
			while(input.hasNext())
			{
				record = input.nextLine().split("\t");
				records.add(new StarRecord (Integer.parseInt(record[0]),
											record[1].charAt(0),
											record[2],
											Integer.parseInt(record[3]),
											Integer.parseInt(record[4]),
											Double.parseDouble(record[5]),
											Double.parseDouble(record[6]),
											Double.parseDouble(record[7]),
											Double.parseDouble(record[8]),
											Double.parseDouble(record[9]),
											Double.parseDouble(record[10]),
											Integer.parseInt(record[11])+agg));
				agg += records.get(count).getFreq();
				count++;
			}
		}
		catch (NoSuchElementException elementException)
		{
			System.err.println("File corrupt.");
			closeFile();
		}
		catch (IllegalStateException stateException)
		{
			System.err.println("Cannot read from file.");
		}
		
		StarFreqCeiling = agg;
		
		closeFile();
	}
	
	//inputs orbital classes from file
	public void loadOrbitalClasses(String fileName, ArrayList<OrbitalRecord> records)
	{
		openFile(fileName);
		try
		{
			while(input.hasNext())
			{
				records.add(new OrbitalRecord(input.nextLine().split("\t")));
			}
		}
		catch (NoSuchElementException elementException)
		{
			System.err.println("File corrupt.");
			closeFile();
		}
		catch (IllegalStateException stateException)
		{
			System.err.println("Cannot read from file.");
		}		
		closeFile();
	}
	
	//input commodities from file
	public void loadCommodities(String fileName)
	{
		openFile(fileName);
		try
		{
			while(input.hasNext())
			{
				Items.add(new Item(input.nextLine().split("\t")));
			}
		}
		catch (NoSuchElementException elementException)
		{
			System.err.println("File corrupt.");
			closeFile();
		}
		catch (IllegalStateException stateException)
		{
			System.err.println("Cannot read from file.");
		}		
		closeFile();
		Item.initItems(Items);
	}
	
	public int getStarFreqCeiling()
	{
		return StarFreqCeiling;
	}
	
	public void openFile(String file)
	{
		try
		{
			input = new Scanner(new File(NAMES_PATH + file));
		}
		catch (FileNotFoundException fileNotFoundException)
		{
			System.err.printf("%s not found.",NAMES_PATH + file);
		}
	}
	
	public void readNames(ArrayList<String> names)
	{
		try
		{
			while(input.hasNext())
			{
				names.add(input.nextLine());	
			}
		}
		catch (NoSuchElementException elementException)
		{
			System.err.println("File corrupt.");
			closeFile();
		}
		catch (IllegalStateException stateException)
		{
			System.err.println("Cannot read from file.");
		}
	}
	
	public void read2Names(ArrayList<SectorRecord> names)
	{
		try
		{
			while(input.hasNext())
			{
				names.add(new SectorRecord(input.nextLine().split("\t")));
			}
		}
		catch (NoSuchElementException elementException)
		{
			System.err.println("File corrupt.");
			closeFile();
		}
		catch (IllegalStateException stateException)
		{
			System.err.println("Cannot read from file.");
		}		
	}
	
	public void closeFile()
	{
		if (input != null)
		{
			input.close();
		}
	}
	
	public String getCluster(int first)
	{
		return ClusterNames.get(first);
	}
	
	public String getCluster(int first, int second)
	{
		return ClusterNames.get(first) + " " + ClusterNames2.get(second);
	}

	public String getSector(int first, int modifier)
	{
		return SectorNames.get(first).Names[modifier];
	}
	
	public String getSystem(int first)
	{
		return SystemNames.get(first);
	}
	
	public String getSystem(int sector, int star)
	{
		return getSystem(star) + " " + getSector(sector,1);
	}
	
	public static String getPlanet(int orbit)
	{
		return RomanNum(orbit+1);
	}
	
	public String getPlanet(int sector, int star, int planet)
	{
		return getSystem(sector,star) + " " + getPlanet(planet);
	}
	
	public String getMoon(int sector, int star, int planet, int moon)
	{
		return getSystem(sector, star)+ " " + getPlanet(planet) + Planet.getMoonDetails(moon);
	}
	
	public String getGov (String domain, int second)
	{
		return domain + " " + GovNames.get(second);
	}
	
	public String getGovAbbr (String domain, int second)
	{
		return domain.substring(0,1) + GovNames.get(second).substring(0,1);
	}
	
	public String getShip(String affiliation, int isMil, int shipType, int shipName)
	{
		if (isMil == 1) //military class ship
		{
			return affiliation + MilShipAbbr.get(shipType) + " " + ShipNames.get(shipName);
		}
		else // non-military ship
		{
			return affiliation + CivShipAbbr.get(shipType) + " " + ShipNames.get(shipName);
		}
	}
	
	public static String RomanNum(int number)
	{
		String Roman = "";
		while (number >= 10)
		{
			Roman += "X";
			number -=10;
		}
		if (number >= 9 )
		{
			Roman += "IX";
			number -= 9;
		}
		if (number >= 5)
		{
			Roman += "V";
			number -= 5;
		}
		if (number >= 4)
		{
			Roman += "IV";
			number -= 4;
		}
		while (number >= 1)
		{
			Roman +="I";
			number -= 1;
		}
		return Roman;
	}
	
	public int getCivNameCount()
	{
		return CivShipNames.size();
	}
	
	public int getCluster1NameCount()
	{
		return ClusterNames.size();
	}
	
	public int getCluster2NameCount()
	{
		return ClusterNames2.size();
	}
	
	public int getGovNameCount()
	{
		return GovNames.size();
	}
	
	public int getMilShipCount()
	{
		return MilShipNames.size();
	}
	
	public int getSectorNameCount()
	{
		return SectorNames.size();
	}
	
	public int getShipNameCount()
	{
		return ShipNames.size();
	}
	
	public int getSystemNameCount()
	{
		return SystemNames.size();
	}
	
	public int getWeaponNameCount()
	{
		return WeaponNames.size();
	}
	
	public StarRecord getStarDetails(int index)
	{
		return StarClasses.get(index);
	}
	
	public byte getStarClass(int freq) //returns arrayList address of record corresponding to a frequency value
	{
		for (int x = 0; x < StarClasses.size(); x++)
		{
			if (freq <= StarClasses.get(x).getFreq())
			{
				return (byte) x;
			}
		}
		return 0;
	}
	
	public OrbitalRecord getOrbitalClasses(int index)
	{
		return OrbitalClasses.get(index);
	}
	
	public ArrayList<OrbitalRecord> getOrbitalClasses()
	{
		return OrbitalClasses;
	}
	
	public ArrayList<Item> GetItems()
	{
		return Items;
	}
}
