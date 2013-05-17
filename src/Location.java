import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Parent class for all locations.
 * 
 * @author Bryant
 */
public class Location
{

  public static Random       rand         = new Random();      // Static Random
                                                                // object for
                                                                // use in all
                                                                // child
                                                                // classes.
  public static FileOps      Ops          = new FileOps();     // Static
                                                                // FileOps
                                                                // object for
                                                                // use in all
                                                                // child
                                                                // classes.
  public static PictureAlbum Pics         = new PictureAlbum();	// Static
                                                                // PictureAlbum
                                                                // object for
                                                                // use in all
                                                                // child
                                                                // classes.

  public static int          MAX_CLUSTERS = 10;                // Constant
                                                                // determines
                                                                // number of
                                                                // clusters in a
                                                                // galaxy.
  public static int          MAX_SECTORS  = 10;                // Constant
                                                                // determines
                                                                // number of
                                                                // sectors in
                                                                // cluster (1 to
                                                                // MAX)
  public static int          MAX_SYSTEMS  = 10;                // Constant
                                                                // determines
                                                                // number of
                                                                // stars in
                                                                // sector (1 to
                                                                // MAX)
  public static int          MAX_PLANETS  = 10;                // Constant
                                                                // determines
                                                                // number of
                                                                // orbitals
                                                                // around a star
                                                                // (1 to MAX)
  public static int          MAX_MOONS    = 10;                // Constant
                                                                // determines
                                                                // number of
                                                                // orbitals
                                                                // around a
                                                                // planet (1 to
                                                                // MAX)
  
  protected BufferedImage    Picture;                          // Used to
                                                                // reference
                                                                // image data
  protected Location         parent;

  /**
   * constructor
   */
  public Location()
  {
    // does nothing
  }

  /**
   * Returns location's image data
   * If no scale is specified it will scale to DEFAULT_SIZE
   * @return
   */
  public BufferedImage GetPic()
  {
	
    return Picture;
  }
  
  public Image GetPic(int scale)
  {
	  return PictureAlbum.getScaledSquareImage(Picture, scale);
  }

  public Location getParent()
  {
    return parent;
  }

  public void setParent(Location target)
  {
    parent = target;
  }

  public Location getChild(int index)
  {
    return null;
  }

  public Location[] getChild()
  {
    return null;
  }
  
  public int GetX()
  {
    return 0;
  }
  
  public int GetY()
  {
    return 0;
  }
  
  public int whatAmI()
  {
    return 0;
  }
}
