import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UWOptionsPanel extends JFrame{
	
	private JLabel titleLabel;
	private JButton saveButton;
	
	public UWOptionsPanel(){
		super("Options");
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(370, 370));
		setBackground(Color.LIGHT_GRAY);
		
		titleLabel = new JLabel("Options / Information");
		saveButton = new JButton("Save Game");
		
		add(titleLabel);
		add(saveButton);
		
	}

}
