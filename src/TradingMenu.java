
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 * This class is a frame that has some very simple trading. The Frame only needs a player. If 
 * this frame is brought up and passed a player that does not have a trade port location unknown
 * things will happen. NEED TO TEST MORE
 * 
 * @author Ryan
 *
 */
public class TradingMenu extends JFrame
{

  private JList            playersInventory;
  private JList            merchantsInventory;
  private JPanel           buttonPanel;
  private JButton          buy;               // put price of selected
                                               // items in parenthesis on
                                               // button
  private JButton          sell;              // put price of selected
                                               // items in parenthesis on
  // button
  private JButton          leave;
  private JLabel           playerBank;
  private JLabel           merchantBank;
  private Player           thePlayer;
  private TradePort         theMerchant;
  private DefaultListModel merchantList;
  private DefaultListModel playerList;
  
  /**
   * Constructor needs a player. Has no default constructor because you need a player to play
   * @param player The player of the game in the correct location 
   */
  public TradingMenu(Player player)
  {

    thePlayer = player;
    makeMerchant();
    addMerchantModel();
    addPlayerModel();
    playersInventory = new JList(playerList);
    merchantsInventory = new JList(merchantList);

    buy = new JButton("Buy");
    sell = new JButton("Sell");
    leave = new JButton("Leave");

    playerBank = new JLabel("" + player.getMoney());
    merchantBank = new JLabel("" + theMerchant.getMoney());

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(3, 1));
    buttonPanel.add(buy);
    buttonPanel.add(sell);
    buttonPanel.add(leave);

    setLayout(new GridLayout(2, 3));
    add(playersInventory);
    add(buttonPanel);
    add(merchantsInventory);
    add(playerBank);
    add(new JLabel());
    add(merchantBank);

    ButtonListener handler = new ButtonListener();
    buy.addActionListener(handler);
    sell.addActionListener(handler);

  }

  private void makeMerchant()
  {
    if(thePlayer.hasBeenHere(thePlayer.getLoc()))
    {
      theMerchant = new TradePort(thePlayer.getPort(thePlayer.getLoc()));
    }
    else
    {
      theMerchant = new TradePort(thePlayer.getLoc());
    }
  }

  private void addPlayerModel()
  {
    playerList = new DefaultListModel();
    Vector<Item> v = thePlayer.getInventory();
    for (Item item : v)
    {
      playerList.addElement(item);
    }
  }

  private void addMerchantModel()
  {
    merchantList = new DefaultListModel();
    Vector<Item> v = theMerchant.getInventory();
    for (Item item : v)
    {
      merchantList.addElement(item);
    }

  }

  private void updateBanks()
  {
    playerBank.setText("" + thePlayer.getMoney());
    merchantBank.setText("" + theMerchant.getMoney());
  }

  private class ButtonListener implements ActionListener
  {

    @Override
    public void actionPerformed(ActionEvent event)
    {
      try
      {
        if (event.getActionCommand().equals("Buy"))
        {
          Item item = (Item) merchantsInventory.getSelectedValue();
          merchantList.removeElement(item);
          playerList.addElement(item);
          theMerchant.sale((double) item.getBasePrice(), item);
          thePlayer.purchase((double) item.getBasePrice(), item);
          updateBanks();
          repaint();
        } else if (event.getActionCommand().equals("Sell"))
        {
          Item item = (Item) playersInventory.getSelectedValue();
          playerList.removeElement(item);
          merchantList.addElement(item);
          thePlayer.sale((double) item.getBasePrice(), item);
          theMerchant.purchase((double) item.getBasePrice(), item);
          updateBanks();
          repaint();
        } else
        {
          Orbital loc = ((TradePort) theMerchant).getLocale();
          thePlayer.setLoc(loc.getParent());
          //TODO: figure out how to close the menu RY
        }
      } catch (Exception e)
      {

      }
    }

  }
}
