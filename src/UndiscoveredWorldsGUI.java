import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class UndiscoveredWorldsGUI extends JFrame implements ActionListener {

	private JPanel startPanel;
	private JPanel storyPanel;
	private JLabel logoLabel;
	private JButton newGameButton;
	private JButton loadGameButton;
	private JButton playButton;
	private JButton backButton;
	private JTextArea storyTextArea;
	private JScrollPane storyScrollPane;
	private Galaxy Andromeda;
	private Player player;
	private SpacePanel test;

	public UndiscoveredWorldsGUI() {
		super("Undiscovered Worlds");
		setBackground(Color.BLACK);
		setLayout(null);
		
		Andromeda = new Galaxy();
		player = new Player();
		Andromeda.Generate(1);
		player.setLoc(Andromeda.randomStart());
		test = new SpacePanel(player, this);
		
		String story = "The year is 2132. " +
				"You are the commander of GSC-235. " +
				"General Systems Cruisers are notorious for having faulty warp drives and your ship was no exception. " +
				"While on your mission of galactic mapping, your cruiser fell victim to the fault while touring a new star system. " +
				"It is now your duty as commander to navigate though the galaxy and acquire materials needed to rebuild your warp drive. " +
				"You begin the game with what Central Command left you. " +
				"You have $500 and whatever supplies you have on board. " +
				"In order to get the required materials you will need to analyze the local economies and " +
				"determine where to buy and sell your goods. Buy low, sell high. " +
				"You'll need to increase your bank by trading cheap items at first and increase your money to " +
				"be able to trade with more valuable items. \n\n"+
				"Be sure to keep an eye on your fuel too. " +
				"Traveling distances this large isn't cheap. " +
				"You'll have to purchase fuel occasionally in order to keep traveling to new systems. " +
				"Run out of fuel and its game over. " +
				"You'll drift around in space until you run out of supplies or the cold abyss freezes your ship.";
		String directions = "Controls\n"
						  + "========\n"
						  + "To navigate throughout the galaxy, click on navigation markers or the back button"
						  + "to return to the parent level.";

		// Start Panel
		logoLabel = new JLabel(new ImageIcon("Art" + File.separator
				+ "LogoTitle.png"));
		logoLabel.setSize(700, 200);
		logoLabel.setLocation((int) (370 - (logoLabel.getWidth() / 2)),
				(int) (70));
		newGameButton = new JButton();
		newGameButton.setSize(400, 100);
		newGameButton.setLocation((int) (370 - (newGameButton.getWidth() / 2)),
				(int) ((370 - (newGameButton.getHeight() / 2) + 50)));
		newGameButton.addActionListener(this);
		newGameButton.setToolTipText("Start a new game");
		newGameButton.setIcon(new ImageIcon("Art" + File.separator + "MainNewGameButton.png"));
		newGameButton.setSelectedIcon(new ImageIcon("Art" + File.separator + "MainNewGameButtonPressed.png"));
		newGameButton.setOpaque(false);
		newGameButton.setBorderPainted(false);
		newGameButton.setContentAreaFilled(false);

		loadGameButton = new JButton();
		loadGameButton.setSize(400, 100);
		loadGameButton.setLocation((int) (newGameButton.getLocation().getX()),
				(int) ((newGameButton.getLocation().getY() + 120)));
		loadGameButton.addActionListener(this);
		loadGameButton.setToolTipText("Load a previous game save");
		loadGameButton.setIcon(new ImageIcon("Art" + File.separator + "MainLoadGameButton.png"));
		loadGameButton.setSelectedIcon(new ImageIcon("Art" + File.separator + "MainLoadGameButtonPressed.png"));
		loadGameButton.setOpaque(false);
		loadGameButton.setBorderPainted(false);
		loadGameButton.setContentAreaFilled(false);

		startPanel = new JPanel();
		startPanel.setLayout(null);
		startPanel.setSize(740, 740);
		startPanel.setLocation(0, 0);
		startPanel.setBackground(Color.DARK_GRAY);
		startPanel.setVisible(true);

		startPanel.add(logoLabel);
		startPanel.add(newGameButton);
		//startPanel.add(loadGameButton);

		add(startPanel);

		// Story Panel

		storyTextArea = new JTextArea();
		storyTextArea.setText(story + "\n\n\n" + directions);
		storyTextArea.setBackground(Color.BLACK);
		storyTextArea.setForeground(Color.GREEN);
		storyTextArea.setSelectionColor(Color.BLACK);
		storyTextArea.setSelectedTextColor(Color.GREEN);
		storyTextArea.setEditable(false);
		storyTextArea.setWrapStyleWord(true);
		storyTextArea.setLineWrap(true);

		storyScrollPane = new JScrollPane(storyTextArea);
		storyScrollPane.setSize(600, 400);
		storyScrollPane.setLocation(
				(int) (370 - (storyScrollPane.getWidth() / 2)), (int) (70));

		playButton = new JButton();
		playButton.setSize(340, 100);
		playButton.setLocation(380, 600);
		playButton.addActionListener(this);
		playButton.setToolTipText("Play Undiscovered Worlds");
		playButton.setIcon(new ImageIcon("Art" + File.separator + "StoryPlayButton.png"));
		playButton.setSelectedIcon(new ImageIcon("Art" + File.separator + "StoryPlayButtonPressed.png"));
		playButton.setOpaque(false);
		playButton.setBorderPainted(false);
		playButton.setContentAreaFilled(false);

		backButton = new JButton();
		backButton.setSize(340, 100);
		backButton.setLocation(20, 600);
		backButton.addActionListener(this);
		backButton.setToolTipText("Return to the main menu");
		backButton.setIcon(new ImageIcon("Art" + File.separator + "StoryBackButton.png"));
		backButton.setSelectedIcon(new ImageIcon("Art" + File.separator + "StoryBackButtonPressed.png"));
		backButton.setOpaque(false);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);

		storyPanel = new JPanel();
		storyPanel.setLayout(null);
		storyPanel.setSize(740, 740);
		storyPanel.setBackground(Color.DARK_GRAY);
		storyPanel.add(storyScrollPane);
		storyPanel.add(backButton);
		storyPanel.add(playButton);
	}

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == newGameButton) {
			storyPanel.setVisible(true);
			startPanel.setVisible(false);
			add(storyPanel);
			remove(startPanel);
			repaint();
		} else if (event.getSource() == backButton) {
			startPanel.setVisible(true);
			storyPanel.setVisible(false);
			add(startPanel);
			remove(storyPanel);
			repaint();
		} else if (event.getSource() == playButton) {

			Andromeda = new Galaxy();
			player = new Player();
			Andromeda.Generate(1);
			player.setLoc(Andromeda.randomStart());
			test = new SpacePanel(player, this);
			test.setVisible(true);
			storyPanel.setVisible(false);
			add(test);
			remove(storyPanel);
			repaint();
		} else if (event.getSource() == loadGameButton){
			System.out.println("This is where the load game funtion will be called");
		}
	}
	
	public void resetGame(){
		test.setVisible(false);
		remove(test);
		add(startPanel);
		startPanel.setVisible(true);
		repaint();
	}
	

}
