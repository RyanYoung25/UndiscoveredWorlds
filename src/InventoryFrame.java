import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class InventoryFrame extends JFrame
{

  private Player            thePlayer;
  private JButton           use;
  private JButton           drop;
  private JButton           back;
  private JList             inventory;
  private DefaultListModel  inventoryList;
  private JScrollPane       scroll;
  private JPanel            buttons;
  private JLabel            message;
  private ArrayList<String> usableItems;  // Let Bryant decide what is usable

  public InventoryFrame(Player player)
  {
    thePlayer = player;
    use = new JButton("Use");
    drop = new JButton("Drop");
    back = new JButton("Back");
    buttons = new JPanel();
    buttons.add(use);
    buttons.add(drop);
    buttons.add(back);

    message = new JLabel();

    makeInventory();
    inventory = new JList(inventoryList);

    scroll = new JScrollPane(inventory);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    this.setLayout(new BorderLayout());
    this.add(scroll, BorderLayout.CENTER);
    this.add(buttons, BorderLayout.SOUTH);
    this.add(message, BorderLayout.NORTH);
  }

  private void makeInventory()
  {
    inventoryList = new DefaultListModel();
    Vector<Item> v = thePlayer.getInventory();
    for (Item item : v)
    {
      inventoryList.addElement(item);
    }
  }

  private class ButtonListener implements ActionListener
  {

    @Override
    public void actionPerformed(ActionEvent event)
    {
      Item item = (Item) inventory.getSelectedValue();
      String itemName = item.getName();
      
      if (event.getSource().equals(use))
      {
        if (usableItems.contains(itemName))
        {
          thePlayer.use(item);  // TODO: not implemented in player yet
        } else
        {
          message.setText("You can't use this item");
        }
      }
      else if(event.getSource().equals(drop))
      {
        thePlayer.drop(item);  //TODO: not implemented in player yet
      }

    }

  }
}
