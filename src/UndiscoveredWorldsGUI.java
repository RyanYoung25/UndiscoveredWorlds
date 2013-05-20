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

	public UndiscoveredWorldsGUI() {
		super("Undiscovered Worlds");
		setBackground(Color.BLACK);
		setLayout(null);

		// Start Panel
		logoLabel = new JLabel(new ImageIcon("Art" + File.separator
				+ "LogoTitle.png"));
		logoLabel.setSize(700, 200);
		logoLabel.setLocation((int) (370 - (logoLabel.getWidth() / 2)),
				(int) (70));
		newGameButton = new JButton("New Game");
		newGameButton.setSize(400, 100);
		newGameButton.setLocation((int) (370 - (newGameButton.getWidth() / 2)),
				(int) ((370 - (newGameButton.getHeight() / 2) + 50)));
		newGameButton.addActionListener(this);

		loadGameButton = new JButton("Load Game");
		loadGameButton.setSize(400, 100);
		loadGameButton.setLocation((int) (newGameButton.getLocation().getX()),
				(int) ((newGameButton.getLocation().getY() + 120)));
		loadGameButton.addActionListener(this);

		startPanel = new JPanel();
		startPanel.setLayout(null);
		startPanel.setSize(740, 740);
		startPanel.setLocation(0, 0);
		startPanel.setBackground(Color.DARK_GRAY);
		startPanel.setVisible(true);

		startPanel.add(logoLabel);
		startPanel.add(newGameButton);
		startPanel.add(loadGameButton);

		add(startPanel);

		// Story Panel

		storyTextArea = new JTextArea();
		storyTextArea
				.setText("This is where the text for the storyline will go! " +
						"This is where the text for the storyline will go! " +
						"This is where the text for the storyline will go! " +
						"This is where the text for the storyline will go! " +
						"This is where the text for the storyline will go! " +
						"This is where the text for the storyline will go! " +
						"This is where the text for the storyline will go! ");
		storyTextArea.setBackground(Color.BLACK);
		storyTextArea.setForeground(Color.GREEN);
		storyTextArea.setEditable(false);
		storyTextArea.setWrapStyleWord(true);
		storyTextArea.setLineWrap(true);

		storyScrollPane = new JScrollPane(storyTextArea);
		storyScrollPane.setSize(600, 400);
		storyScrollPane.setLocation(
				(int) (370 - (storyScrollPane.getWidth() / 2)), (int) (70));

		playButton = new JButton("Play Game");
		playButton.setSize(340, 100);
		playButton.setLocation(380, 600);
		playButton.addActionListener(this);

		backButton = new JButton("Back to Main Menu");
		backButton.setSize(340, 100);
		backButton.setLocation(20, 600);
		backButton.addActionListener(this);

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
			Galaxy Andromeda = new Galaxy();
			Player player = new Player();

			Andromeda.Generate(1);

			player.setLoc(Andromeda.randomStart());

			SpacePanel test = new SpacePanel(player);
			test.setVisible(true);
			storyPanel.setVisible(false);
			add(test);
			remove(storyPanel);
			repaint();
		} else if (event.getSource() == loadGameButton){
			System.out.println("This is where the load game funtion will be called");
		}
	}

}
