import java.awt.*;
import javax.swing.*;

public class UndiscoveredWorldsGUI extends JFrame{
	
	private JPanel mapPanel;
	
	public UndiscoveredWorldsGUI(){
		super("Undiscovered Worlds");
		
		UWPanel panel = new UWPanel();
		
		setLayout(new FlowLayout());
		setBackground(Color.DARK_GRAY);
		
		mapPanel = new JPanel();

		//mapPanel.setPreferredSize(new Dimension(720,720));
		//mapPanel.setBackground(Color.WHITE);

		mapPanel.add(panel);
		add(mapPanel);
		
	}
	
}
