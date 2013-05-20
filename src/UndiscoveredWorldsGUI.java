import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class UndiscoveredWorldsGUI extends JFrame implements ActionListener {

	private JButton optionsButton;
	private JPanel startPanel;
	private JPanel storyPanel;
	private JLabel logoLabel;
	private JButton newGameButton;
	private JButton loadGameButton;

	public UndiscoveredWorldsGUI() {
		super("Undiscovered Worlds");
		setBackground(Color.BLACK);
		setLayout(null);
		
		logoLabel = new JLabel(new ImageIcon("Art" + File.separator + "LogoTitle.png"));
		logoLabel.setSize(700, 200);
		logoLabel.setLocation(
								(int) (370 - (logoLabel.getWidth()/2)),
								(int) (70));
		newGameButton = new JButton("New Game");
		newGameButton.setSize(400, 100);
		newGameButton.setLocation(
								(int) (370 - (newGameButton.getWidth()/2)),
								(int) ((370 - (newGameButton.getHeight()/2) + 50)));
		newGameButton.addActionListener(this);
		
		loadGameButton = new JButton("Load Game");
		loadGameButton.setSize(400, 100);
		loadGameButton.setLocation(
								(int) (newGameButton.getLocation().getX()),
								(int) ((newGameButton.getLocation().getY() + 120)));
		loadGameButton.addActionListener(this);
		
		startPanel = new JPanel();
		startPanel.setLayout(null);
		startPanel.setSize(740, 740);
		startPanel.setLocation(0, 0);
		startPanel.setVisible(true);
		startPanel.setBackground(Color.DARK_GRAY);
		
		startPanel.add(logoLabel);
		startPanel.add(newGameButton);
		startPanel.add(loadGameButton);
		
		//add(startPanel);
		
		/*
		optionsButton = new JButton();
		optionsButton.setLocation(702, 0);
		optionsButton.setSize(38, 38);
		optionsButton.setIcon(new ImageIcon("Art" + File.separator + "OptionButton.png"));
		optionsButton.setToolTipText("Options Menu");
		optionsButton.addActionListener(this);
		*/

		Galaxy Andromeda = new Galaxy();
		Player player = new Player();

		Andromeda.Generate(1);
		// Andromeda.DisplayCluster();

		player.setLoc(Andromeda.randomStart());

		SpacePanel test = new SpacePanel(player);
		test.setVisible(true);
		// test.populateSpace();
		// test.setPreferredSize(new Dimension(740, 740));
		// test.setLocation(0, 0);
		//this.add(optionsButton);
		add(test);
		// System.out.println("This was run");
		/*
		 * GUITest test = new GUITest(Andromeda.GetSectors());
		 * test.setPreferredSize(new Dimension(720,720)); test.setLocation(0,
		 * 0); test.setLayout(null);
		 * 
		 * setLayout(new FlowLayout()); setBackground(Color.DARK_GRAY);
		 */
		// test.add(optionsButton);
		// add(test);
	}

	public void actionPerformed(ActionEvent event) {
		
	}

}
