


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

public class TradingMenu extends JFrame
{

  private JList<Item>            playersInventory;
  private JList<Item>            merchantsInventory;
  private JPanel                 buttonPanel;
  private JButton                buy;               // put price of selected
                                                     // items in parenthesis on
                                                     // button
  private JButton                sell;              // put price of selected
                                                     // items in parenthesis on
  // button
  private JButton                leave;
  private JLabel                 playerBank;
  private JLabel                 merchantBank;
  private Merchant               thePlayer;
  private Merchant               theMerchant;
  private DefaultListModel<Item> merchantList;
  private DefaultListModel<Item> playerList;

  public TradingMenu(Merchant player, Merchant merchant)
  {

    thePlayer = player;
    theMerchant = merchant;
    addMerchantModel();
    addPlayerModel();
    playersInventory = new JList<Item>(playerList);
    merchantsInventory = new JList<Item>(merchantList);

    buy = new JButton("Buy");
    sell = new JButton("Sell");
    leave = new JButton("Leave");

    playerBank = new JLabel("" + player.getMoney());
    merchantBank = new JLabel("" + merchant.getMoney());

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

  private void addPlayerModel()
  {
    playerList = new DefaultListModel<Item>();
    Vector<Item> v = thePlayer.getInventory();
    for (Item item : v)
    {
      playerList.addElement(item);
    }
  }

  private void addMerchantModel()
  {
    merchantList = new DefaultListModel<Item>();
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
        if(event.getActionCommand().equals("Buy"))
        {
          Item item = (Item) merchantsInventory.getSelectedValue();
          merchantList.removeElement(item);
          playerList.addElement(item);
          theMerchant.sale((double) item.getBasePrice(), item);
          thePlayer.purchase((double) item.getBasePrice(), item);
          updateBanks();
          repaint();
          System.out.println(thePlayer.toString());
        }
        else if(event.getActionCommand().equals("Sell"))
        {
          Item item = (Item) playersInventory.getSelectedValue();
          playerList.removeElement(item);
          merchantList.addElement(item);
          thePlayer.sale((double) item.getBasePrice(), item);
          theMerchant.purchase((double) item.getBasePrice(), item);
          updateBanks();
          repaint();
          System.out.println(thePlayer.toString());
        }
        else
        {
          //Implement leave button
        }
      }
      catch(Exception e)
      {
        
      }
    }

  }
}