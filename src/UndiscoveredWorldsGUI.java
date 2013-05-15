import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UndiscoveredWorldsGUI extends JFrame implements ActionListener{
	
	private JButton optionsButton;
	
	public UndiscoveredWorldsGUI(){
		super("Undiscovered Worlds");
		
		optionsButton = new JButton("?");
		optionsButton.setLocation(700, 1);
		optionsButton.setSize(20, 20);
		optionsButton.addActionListener(this);
		
		UWOptionsPanel options = new UWOptionsPanel();
		options.setPreferredSize(new Dimension(300,720));
		
		Galaxy Andromeda = new Galaxy();
		Player player = new Player();

		Andromeda.Generate(1);
		//Andromeda.DisplayCluster();
		
		player.setLoc(Andromeda.randomStart());
		
		SpacePanel test = new SpacePanel(player);
		//test.populateSpace();
		//test.setPreferredSize(new Dimension(720, 720));
		//test.setLocation(0, 0);
		this.add(test);
		System.out.println("This was run");
	/*	GUITest test = new GUITest(Andromeda.GetSectors());
		test.setPreferredSize(new Dimension(720,720));
		test.setLocation(0, 0);
		test.setLayout(null);
		
		setLayout(new FlowLayout());
		setBackground(Color.DARK_GRAY);
*/
		test.add(optionsButton);
		//add(test);
	}
	
	public void actionPerformed(ActionEvent event){
		UWOptionsPanel optionWindow = new UWOptionsPanel();
		optionWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		optionWindow.setLocation((this.getWidth()/4), (this.getHeight()/4));
		optionWindow.setSize(400, 400);
		optionWindow.setVisible(true);
		optionWindow.setResizable(true);
	}
	
}
