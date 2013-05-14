import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SpacePanel extends JPanel
{

  private static final int SCALAR = 2;
  private static final int SHIFT = 360;
  Player   player;
  Location currentLocation;

  public SpacePanel(Player player)
  {
    this.player = player;
    currentLocation = this.player.getLoc();
    //populateSpace();
    
    this.setLayout(null);
    this.setBounds(0, 0, 720, 720);
    this.setBackground(Color.BLACK);
    
    Location[] locations = currentLocation.getChild();
    for (Location loc : locations)
    {
      JButton b = new JButton(loc.toString());
      b.setBounds(SCALAR * loc.GetX() + SHIFT,SCALAR * loc.GetY() + SHIFT,  50, 50);
      //b.setLocation(loc.GetX() + 360, loc.GetY() + 360);
      //b.setPreferredSize(new Dimension(50, 50));
      b.setBackground(Color.BLUE);
      add(b);      
    }
  }

  public void populateSpace()
  {
    Location[] locations = currentLocation.getChild();
    for (Location loc : locations)
    {
      JButton b = new JButton(loc.toString());
      b.setLocation(loc.GetX() + 360, loc.GetY() + 360);
      b.setPreferredSize(new Dimension(50, 50));
      b.setBackground(Color.BLUE);
      this.add(b);
    }
    repaint();
  }

}
