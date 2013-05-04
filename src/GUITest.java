import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GUITest extends JPanel
{
	private Cluster[] c;
	private Sector[] s;
	
	public GUITest(Sector[] sectors)
	{
		s = sectors;
	}
	
	public GUITest(Cluster[] clusters)
	{
		c = clusters;
	}
	
	public void paintComponent(Graphics g)
	{
		int xoff = 350;
		int yoff = 350;
		super.paintComponent(g);
		
		this.setBackground(Color.BLACK);
		g.setColor(Color.WHITE);
		
		for( Sector x : s)
		{
			g.drawOval(x.GetX() + xoff, x.GetY() + yoff, 10, 10);
		}
	}
	
}
