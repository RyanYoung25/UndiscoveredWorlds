import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpacePanel extends JPanel
{

  private static final int SCALAR = 2;
  private static final int SHIFT  = 360;
  Player                   player;
  Location                 currentLocation;

  public SpacePanel(Player player)
  {
    this.player = player;
    this.setBackground(Color.BLACK);
    populateSpace();
  }

  public void populateSpace()
  {
    //this.removeAll();
    this.setLayout(null);
    this.setBounds(0, 0, 720, 720);
    currentLocation = this.player.getLoc();
    Location[] locations = currentLocation.getChild();
    if(locations != null)
    {
    for (int i = 0; i < locations.length; i++)
    {
      Location loc = locations[i];
      JButton b = new JButton(loc.toString());
      b.setBounds(SCALAR * loc.GetX() + SHIFT, SCALAR * loc.GetY() + SHIFT, 50,
          50);
      b.setBackground(Color.BLUE);
      LocationListener handler = new LocationListener(i);
      b.addActionListener(handler);
      this.add(b);
    }
    repaint();
    }
    else
    {
      TradingMenu menu = new TradingMenu(player);
      menu.setSize(500, 500);
      menu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  
      menu.setVisible(true);
    }
  }

  private class LocationListener implements ActionListener
  {

    private int index;          // The index of the child location

    public LocationListener(int index)
    {
      this.setIndex(index);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
      player.setLoc(currentLocation.getChild(index));
      populateSpace();
    }

    public int getIndex()
    {
      return index;
    }

    public void setIndex(int index)
    {
      this.index = index;
    }

  }
}
