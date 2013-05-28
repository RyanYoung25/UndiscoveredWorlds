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
	public Image GetNavImage()
	{
		if(this.getTradePort() == null)
		{
			return super.GetNavImage(); // no image will be visible, pirate base
		}
		return PictureAlbum.getScaledSquareImage(Pics.getPort(),50);
	}
}
