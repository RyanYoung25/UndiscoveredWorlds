import java.awt.image.BufferedImage;
import java.util.Random;

public class Location 
{
	public static Random rand = new Random();
	public static FileOps Ops = new FileOps();
	public static PictureAlbum Pics = new PictureAlbum();
	
	public static int MAX_CLUSTERS = 10;
	public static int MAX_SECTORS = 10;
	public static int MAX_SYSTEMS = 10;
	public static int MAX_PLANETS = 10;
	public static int MAX_MOONS = 10;
	
	protected BufferedImage Picture;
	
	public Location()
	{
		
	}
	
	public BufferedImage GetPic()
	{
		return Picture;
	}
}
