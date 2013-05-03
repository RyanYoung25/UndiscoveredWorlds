import java.awt.*;
import javax.swing.*;

public class UndiscoveredWorldsGUI extends JFrame{
	
	private JPanel infoPanel;
	private JPanel mapPanel;
	private JPanel tradingPanel;
	
	public UndiscoveredWorldsGUI(){
		super("Undiscovered Worlds");
		setLayout(new FlowLayout());
		setBackground(Color.DARK_GRAY);
		
		infoPanel = new JPanel();
		mapPanel = new JPanel();
		tradingPanel = new JPanel();
		
		infoPanel.setPreferredSize(new Dimension(260,720));
		infoPanel.setBackground(Color.WHITE);

		mapPanel.setPreferredSize(new Dimension(720,720));
		mapPanel.setBackground();

		tradingPanel.setPreferredSize(new Dimension(260,720));
		tradingPanel.setBackground(Color.WHITE);

		add(infoPanel);
		add(mapPanel);
		add(tradingPanel);
		
	}
	
	public void clean(){
		repaint();
	}
	
}
