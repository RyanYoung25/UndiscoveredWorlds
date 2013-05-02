


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ItemMaker extends JFrame
{

  private JLabel   itemList;

  private JPanel          entryPanel;
  private JPanel          displayPanel;

  private JTextField      name;
  private JTextField      description;
  private JTextField      basePrice;
  private JTextField      volatility;
  private JTextField      IDNumber;
  private JTextField      modifier;
  private JTextField      classification;

  private JLabel          nameLabel;
  private JLabel          descriptionLabel;
  private JLabel          basePriceLabel;
  private JLabel          volatilityLabel;
  private JLabel          IDNumberLabel;
  private JLabel          modifierLabel;
  private JLabel          classLabel;

  private JButton         save;
  private JButton         quit;

  private ArrayList<Item> items;

  public ItemMaker()
  {
    super("Item Maker");

    itemList = new JLabel();
    
    items = new ArrayList<Item>();

    entryPanel = new JPanel();
    displayPanel = new JPanel();

    name = new JTextField();
    description = new JTextField();
    basePrice = new JTextField();
    volatility = new JTextField();
    IDNumber = new JTextField();
    modifier = new JTextField();
    classification = new JTextField();

    nameLabel = new JLabel("Name: ");
    descriptionLabel = new JLabel("Description: ");
    basePriceLabel = new JLabel("Base Price: ");
    volatilityLabel = new JLabel("Volatility: ");
    IDNumberLabel = new JLabel("ID Number: ");
    modifierLabel = new JLabel("Modifier: ");
    classLabel = new JLabel("Classification(int): ");

    save = new JButton("Save");
    quit = new JButton("Quit");

    save.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent arg0)
      {
        try
        {
          Item i = new Item(name.getText(), description.getText(), Integer
              .parseInt(IDNumber.getText()), Integer.parseInt(basePrice
              .getText()), Integer.parseInt(modifier.getText()), Integer
              .parseInt(volatility.getText()), Integer.parseInt(classification
              .getText()));
          items.add(i);
          itemList.setText(i.getName());
        } catch (Exception e)
        {
          JOptionPane.showMessageDialog(null,
              "There was an error with what you entered", "Error",
              JOptionPane.ERROR_MESSAGE);
        }
      }

    });

    quit.addActionListener(new ActionListener()
    {

      @Override
      public void actionPerformed(ActionEvent event)
      {

        try
        {
          ObjectOutputStream output = new ObjectOutputStream(
              new FileOutputStream("Items.ser"));
          for (Item item : items)
          {
            output.writeObject(item);
          }
          output.close();
          System.exit(1);
        } catch (IOException e)
        {
          e.printStackTrace();
        }

      }

    });

    entryPanel.setLayout(new GridLayout(8, 2));
    entryPanel.add(nameLabel);
    entryPanel.add(name);
    entryPanel.add(descriptionLabel);
    entryPanel.add(description);
    entryPanel.add(basePriceLabel);
    entryPanel.add(basePrice);
    entryPanel.add(modifierLabel);
    entryPanel.add(modifier);
    entryPanel.add(volatilityLabel);
    entryPanel.add(volatility);
    entryPanel.add(IDNumberLabel);
    entryPanel.add(IDNumber);
    entryPanel.add(classLabel);
    entryPanel.add(classification);
    entryPanel.add(save);
    entryPanel.add(quit);
    
    this.setLayout(new FlowLayout());
    this.add(entryPanel);
    this.add(displayPanel);

  }

}
