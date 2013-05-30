import java.io.File;
import java.io.FileNotFoundException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;

/**
 * @author Bryant
 * This class handles text based file resources.
 */

public class FileOps 
{
	private Scanner input;
	private ArrayList<String> CivShipNames = new ArrayList<String>(); // Stores Civilian ship names. Not used.
	private ArrayList<String> CivShipAbbr = new ArrayList<String>(); // Stores abbreviations of Civilian ship names. Not used.
	private ArrayList<String> ClusterNames = new ArrayList<String>(); // Stores first part of Cluster names.
	private ArrayList<String> ClusterNames2 = new ArrayList<String>(); // Stores second part of Cluster names.
	private ArrayList<String> GovNames = new ArrayList<String>(); // Stores government names. Not Used.
	private ArrayList<String> GovAbbr = new ArrayList<String>(); // Stores abbreviations of government names. Not used.
	private ArrayList<String> MilShipNames = new ArrayList<String>(); // Stores Military ship names. Not used.
	private ArrayList<String> MilShipAbbr = new ArrayList<String>(); // Stores abbreviations of Military ship names. Not used.
	private ArrayList<SectorRecord> SectorNames = new ArrayList<SectorRecord>(); // Stores sector names.
	private ArrayList<String> ShipNames = new ArrayList<String>(); // Stores names of ships. Not used.
	private ArrayList<String> SystemNames = new ArrayList<String>(); // Stores system names. Currently corresponds to letters in Greek alphabet.
	private ArrayList<String> WeaponNames = new ArrayList<String>(); // Stores weapon names. Not used.
	private ArrayList<OrbitalRecord> OrbitalClasses = new ArrayList<OrbitalRecord>(); // Stores orbital reference information.
	private ArrayList<StarRecord> StarClasses = new ArrayList<StarRecord>(); // Stores star reference information
	private ArrayList<Item> Items = new ArrayList<Item>(); // Stores available commodities for trading.
	public static final String NAMES_PATH = "Names" + File.separator;  // Constant used for determining file location
	private int StarFreqCeiling; // Used in star system generation.
	

	/**
	 * Constructor loads text files the game uses.
	 */
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
	
	
	/**
	 * Shuffles the sector names to effectively randomize name generation.
	 * @param seed = seed that collections uses to shuffle.
	 */
	public void SectorRandomizer(long seed)
	{
		Collections.shuffle(SectorNames, new Random(seed));
	}
	
	/**
	 * Shuffles the system names to effectively randomize name generation.
	 * @param seed = seed that Collections uses to shuffle.
	 */
	public void SystemRandomizer(long seed)
	{
		Collections.shuffle(SystemNames, new Random(seed));
	}
	
	/**
	 * Takes strings from a sequential text file and puts them in an ArrayList.
	 * @param fileName = File to be loaded.
	 * @param Strings = ArrayList that is used to store information from the file.
	 */
	public void loadNames(String fileName, ArrayList<String> Strings)
	{
		openFile(fileName);
		readNames(Strings);
		closeFile();
	}
	
	/**
	 * Takes strings from a sequential text file and puts them in an ArrayList. This is specific to Sector names.
	 * @param fileName = File to be loaded.
	 * @param Sectors = ArrayList that is used to store information from the file.
	 */
	public void load2Names(String fileName, ArrayList<SectorRecord> Sectors)
	{
		openFile(fileName);
		read2Names(Sectors);
		closeFile();
	}
	
	/**
	 * Takes strings from a sequential text file and puts them in an ArrayList of StarRecords.
	 * @param fileName = File to be loaded.
	 * @param records = ArrayList that is used to store information from the file.
	 */
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

	/**
	 * Takes strings from a sequential text file and puts them in an ArrayList of StarRecords.
	 * @param fileName = File to be loaded.
	 * @param records = ArrayList that is used to store information from the file.
	 */
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
	
	/**
	 * Takes strings from a sequential text file and puts them in an ArrayList of Items.
	 * @param fileName = File to be loaded.
	 */
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
	
	/**
	 * Returns the max star frequency value. Used in generation algorithm.
	 * @return int
	 */
	public int getStarFreqCeiling()
	{
		return StarFreqCeiling;
	}
	
	/**
	 * Opens selected file for input.
	 * @param file = file to be loaded.
	 */
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
	
	/**
	 * This method reads Scanner input and outputs to an ArrayList.
	 * @param names = ArrayList to store input.
	 */
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
	
	/**
	 * This method reads Scanner input and outputs to an ArrayList. Specific to Sector names.
	 * @param names = ArrayList to store input.
	 */
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
	
	/**
	 * Closes currently opened file.
	 */
	public void closeFile()
	{
		if (input != null)
		{
			input.close();
		}
	}
	
	/**
	 * Get a specific name from the Cluster name list.
	 * @param first = index of desired name.
	 * @return String
	 */
	public String getCluster(int first)
	{
		return ClusterNames.get(first);
	}
	
	/**
	 * Gets a full cluster name using index parameters.
	 * @param first = index of first part of desired name.
	 * @param second = index of second part of desired name.
	 * @return
	 */
	public String getCluster(int first, int second)
	{
		return ClusterNames.get(first) + " " + ClusterNames2.get(second);
	}

	/**
	 * Returns a sector name or the modified sector name used in star system names.
	 * @param first = index of desired name.
	 * @param modifier = determines which version of the name is used.
	 * @return String
	 */
	public String getSector(int first, int modifier)
	{
		return SectorNames.get(first).Names[modifier];
	}
	
	/**
	 * Returns a sector name.
	 * @param first = index of desired name.
	 * @return String
	 */
	public String getSystem(int first)
	{
		return SystemNames.get(first);
	}
	
	/**
	 * Returns the name of a star system.
	 * @param sector = Uses sector name integer value as index.
	 * @param star = Uses starsystem name integer value as index.
	 * @return String
	 */
	public String getSystem(int sector, int star)
	{
		return getSystem(star) + " " + getSector(sector,1);
	}
	
	/**
	 * Converts desired orbit to roman numeral.
	 * @param orbit = desired orbit
	 * @return String
	 */
	public static String getPlanet(int orbit)
	{
		return RomanNum(orbit+1);
	}
	
	/**
	 * Returns name of orbital.
	 * @param sector = Uses sector name integer value as index.
	 * @param star = Uses starsystem name integer value as index.
	 * @param planet = Uses orbital index.
	 * @return String
	 */
	public String getPlanet(int sector, int star, int planet)
	{
		return getSystem(sector,star) + " " + getPlanet(planet);
	}
	
	/**
	 * Returns name of moon
	 * @param sector = Uses sector name integer value as index.
	 * @param star = Uses starsystem name integer value as index.
	 * @param planet = Uses planet's orbital index.
	 * @param moon = Uses orbital index
	 * @return
	 */
	public String getMoon(int sector, int star, int planet, int moon)
	{
		return getSystem(sector, star)+ " " + getPlanet(planet) + Planet.getMoonDetails(moon);
	}
	
	/**
	 * Not Used. Returns desired government name.
	 * @param domain
	 * @param second
	 * @return
	 */
	public String getGov (String domain, int second)
	{
		return domain + " " + GovNames.get(second);
	}
	
	/**
	 * Not Used. Returns desired government abbreviation.
	 * @param domain
	 * @param second
	 * @return
	 */
	public String getGovAbbr (String domain, int second)
	{
		return domain.substring(0,1) + GovNames.get(second).substring(0,1);
	}
	
	/**
	 * Not Used. Returns desired ship name and designation.
	 * @param affiliation
	 * @param isMil
	 * @param shipType
	 * @param shipName
	 * @return
	 */
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
	
	/**
	 * Converts integer value to roman numeral equivalent.
	 * @param number = integer to be converted
	 * @return String
	 */
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
	
	/**
	 * Not Used. Number of available civilian ship classifications.
	 * @return
	 */
	public int getCivNameCount()
	{
		return CivShipNames.size();
	}
	
	/**
	 * Returns the number of available first-part cluster names.
	 * @return
	 */
	public int getCluster1NameCount()
	{
		return ClusterNames.size();
	}
	
	/**
	 * Returns the number of available second-part cluster names.
	 * @return
	 */
	public int getCluster2NameCount()
	{
		return ClusterNames2.size();
	}
	
	/**
	 * Not Used. Returns number of available government names.
	 * @return
	 */
	public int getGovNameCount()
	{
		return GovNames.size();
	}
	
	/**
	 * Not Used. Returns number of military ship classifications.
	 * @return
	 */
	public int getMilShipCount()
	{
		return MilShipNames.size();
	}
	
	/**
	 * Returns number of available sector names.
	 * @return
	 */
	public int getSectorNameCount()
	{
		return SectorNames.size();
	}
	
	/**
	 * Not Used. Returns number of available ship names.
	 * @return
	 */
	public int getShipNameCount()
	{
		return ShipNames.size();
	}
	
	/** 
	 * Returns number of available system names. Should be 24 because they correspond to letters in the Greek alphabet.
	 * @return
	 */
	public int getSystemNameCount()
	{
		return SystemNames.size();
	}
	
	/** Not Used. Returns number of available weapon names.
	 * @return
	 */
	public int getWeaponNameCount()
	{
		return WeaponNames.size();
	}
	
	/**
	 * Get desired StarRecord.
	 * @param index = index of desired StarRecord.
	 * @return
	 */
	public StarRecord getStarDetails(int index)
	{
		return StarClasses.get(index);
	}
	
	/**
	 * Returns a star class based on the frequency value.
	 * @param freq
	 * @return
	 */
	public byte getStarClass(int freq)
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
	
	/**
	 * Returns desired Orbital Record.
	 * @param index
	 * @return
	 */
	public OrbitalRecord getOrbitalClasses(int index)
	{
		return OrbitalClasses.get(index);
	}
	
	/**
	 * Returns available OrbitalRecords.
	 * @return
	 */
	public ArrayList<OrbitalRecord> getOrbitalClasses()
	{
		return OrbitalClasses;
	}
	
	/**
	 * Returns available Items.
	 * @return
	 */
	public ArrayList<Item> GetItems()
	{
		return Items;
	}
}
