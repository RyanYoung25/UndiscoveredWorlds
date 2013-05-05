import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;


public class UWPanel extends JPanel{
	
	private JLabel label;
	
	public UWPanel(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(720,720));
		setBackground(Color.RED);
		label = new JLabel("This is a test label");
		label.setBackground(Color.YELLOW);
		add(label);
	}
}
