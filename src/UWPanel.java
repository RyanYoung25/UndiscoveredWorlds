import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;


public class UWPanel extends JPanel{
	
	private JLabel label;
	
	public UWPanel(){
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(720,700));
		setBackground(Color.GREEN);
		
		label = new JLabel();
		label.setToolTipText("" + new ImageIcon("Art/Barren01.png").getDescription());
		label.setIcon(new ImageIcon("Art/Barren01.png"));
		add(label);
	}
}