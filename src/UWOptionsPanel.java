import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class UWOptionsPanel extends JPanel{
	
	private JLabel titleLabel;
	
	public UWOptionsPanel(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(260, 700));
		setBackground(Color.LIGHT_GRAY);
		
		titleLabel = new JLabel("Options / Information");
		
		add(titleLabel);
		
	}

}
