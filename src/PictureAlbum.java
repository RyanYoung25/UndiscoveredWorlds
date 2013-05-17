import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class PictureAlbum 
{

	public static final String IMG_FOLDER = "Art" + File.separator;
	
	private BufferedImage[] ClusterPics;
	private BufferedImage[] SectorPics;
	private BufferedImage[] StarHaloPics;
	private BufferedImage[] StarPics;
	private BufferedImage[] RingPics;
	private BufferedImage[] BeltPics;
	private BufferedImage[] BarrenPics;
	private BufferedImage[] EarthyPics;
	private BufferedImage[] GasPics;
	private BufferedImage[] HellPics;
	private BufferedImage[] LavaPics;
	private BufferedImage[] OceanPics;
	private BufferedImage[] SlushPics;
	
	
	
	public PictureAlbum ()
	{

		try
		{
			loadImages();
		}
		catch (Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	private BufferedImage[] loadImage(String name, int number) throws IOException
	{
		BufferedImage[] temp = new BufferedImage[number];
		for(int x = 0; x < temp.length; x++)
		{
			temp[x] = ImageIO.read(new File(IMG_FOLDER + name + String.format("%02d.png", x+1)));
		}
		return temp;
	}
	
	private void loadImages()
	{
		try
		{
			ClusterPics = loadImage("Cluster",10);
			SectorPics = loadImage("Sector",10);
			StarPics = loadImage("Star",7);
			StarHaloPics = loadImage("StarHalo",7);
			RingPics = loadImage("Ring",10);
			BeltPics = loadImage("Belt",3);
			BarrenPics = loadImage("Barren",15);
			EarthyPics = loadImage("Earthy",5);
			GasPics = loadImage("Gas",10);
			HellPics = loadImage("Hell",5);
			LavaPics = loadImage("Lava",5);
			OceanPics = loadImage("Ocean",5);
			SlushPics = loadImage("Slush",5);
			
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	public BufferedImage getClusterPic(int index)
	{
		return ClusterPics[index];
	}
	
	public BufferedImage getClusterPic(int index, int scale)
	{
		return ClusterPics[index];
	}
	
	public BufferedImage getSectorPic(int index)
	{
		return SectorPics[index];
	}
	
	public BufferedImage getRingPic(int index)
	{
		return RingPics[index];
	}
	
	public BufferedImage getBeltPic(int index)
	{
		return BeltPics[index];
	}
	
	public BufferedImage getBarrenPic(int index)
	{
		return BarrenPics[index];
	}
	
	public BufferedImage getEarthyPic(int index)
	{
		return EarthyPics[index];
	}
	
	public BufferedImage getGasPic(int index)
	{
		return GasPics[index];
	}
	
	public BufferedImage getHellPic(int index)
	{
		return HellPics[index];
	}
	
	public BufferedImage getLavaPic(int index)
	{
		return LavaPics[index];
	}
	
	public BufferedImage getOceanPic(int index)
	{
		return OceanPics[index];
	}
	
	public BufferedImage getSlushPic(int index)
	{
		return SlushPics[index];
	}
	
	public BufferedImage getStarPic(int index)
	{
		return StarPics[index];
	}
	
	public BufferedImage getStarHaloPic(int index)
	{
		return StarHaloPics[index];
	}
/*	
	public static BufferedImage getScaledSquareImage(BufferedImage image)
	{
		BufferedImage temp = new BufferedImage(DEFAULT_SIZE,DEFAULT_SIZE,BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g = temp.createGraphics();
		g.drawImage(image, DEFAULT_SIZE, DEFAULT_SIZE, null);
		g.dispose();
		return temp;
	}
*/
	public static Image getScaledSquareImage(BufferedImage pic, int scale)
	{
/*		int length = image.getWidth();
		BufferedImage temp = new BufferedImage(length, length, BufferedImage.TYPE_4BYTE_ABGR);
		AffineTransform mapper = new AffineTransform();
		mapper.scale(scale, scale);
		AffineTransformOp Op = new AffineTransformOp(mapper, AffineTransformOp.TYPE_BILINEAR);
		return Op.filter(image, temp);
*/		return pic.getScaledInstance(scale, scale, BufferedImage.SCALE_AREA_AVERAGING);
	}
}
