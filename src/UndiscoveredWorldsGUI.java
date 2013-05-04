import java.awt.*;
import javax.swing.*;

public class UndiscoveredWorldsGUI extends JFrame{
	
	private JPanel infoPanel;
	private JPanel mapPanel;
	private JPanel tradingPanel;
	
	public UndiscoveredWorldsGUI(){
		super("Undiscovered Worlds");
		Galaxy Andromeda = new Galaxy();

		Andromeda.Generate(1);
		Andromeda.DisplayCluster();
		GUITest yay = new GUITest(Andromeda.GetSectors());
		
		setLayout(new FlowLayout());
		setBackground(Color.DARK_GRAY);
		
		infoPanel = new JPanel();
		mapPanel = new JPanel();
		tradingPanel = new JPanel();
		
		infoPanel.setPreferredSize(new Dimension(260,720));
		infoPanel.setBackground(Color.WHITE);

		mapPanel.setPreferredSize(new Dimension(720,720));
		//mapPanel.setBackground(Color.BLACK);

		tradingPanel.setPreferredSize(new Dimension(260,720));
		tradingPanel.setBackground(Color.WHITE);

		mapPanel.add(yay);
		add(infoPanel);
		add(mapPanel);
		add(tradingPanel);
		
	}
	
	public void clean(){
		repaint();
	}
	
}
