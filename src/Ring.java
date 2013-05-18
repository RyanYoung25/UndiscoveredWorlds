import java.awt.Image;
import java.awt.image.BufferedImage;


/**
 * Like Planet, Ring classes can replace orbitals in StarSystems. Pretty much just a placeholder for now.
 * @author Bryant
 */
public class Ring extends Orbital
{
	/**
	 * Constructor. Just calls the Orbital class' constructor at the moment.
	 * @param radius = relative distance of this ring from it's parent star
	 * @param oclass = classification of this ring.
	 * @param parent = classification of this ring's star.
	 */
	public Ring(int radius, byte oclass, int star, Location target)
	{
		super(radius, oclass, star, target);
	}
	
	@Override
	public BufferedImage GetPic()
	{
	    return super.GetPic();
	}
	
	@Override
	public Image GetPic(int scale)
	{
		if(this.getTradePort() == null)
		{
			return PictureAlbum.getScaledSquareImage(Pics.getPort(), 1); //no trade port means no image should be present; I'd set it to null if I could
		}
		return PictureAlbum.getScaledSquareImage(Pics.getPort(), scale);
	}
}
