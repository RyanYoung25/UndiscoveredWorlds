import java.awt.*;
import javax.swing.*;

public class UndiscoveredWorldsGUI extends JFrame{
	
	private JPanel optionsPanel;
	private JPanel mapPanel;
	private JPanel tradingPanel;
	
	public UndiscoveredWorldsGUI(){
		super("Undiscovered Worlds");
		
		UWPanel panel = new UWPanel();
		UWOptionsPanel options = new UWOptionsPanel();
		//TradingMenu trading = new TradingMenu(null, null);
		
		setLayout(new FlowLayout());
		setBackground(Color.DARK_GRAY);
		
		optionsPanel = new JPanel();
		mapPanel = new JPanel();
		tradingPanel = new JPanel();
		
		//optionsPanel.setPreferredSize(new Dimension(260,720));
		//optionsPanel.setBackground(Color.WHITE);

		//mapPanel.setPreferredSize(new Dimension(720,720));
		//mapPanel.setBackground(Color.WHITE);

		tradingPanel.setPreferredSize(new Dimension(260,700));
		tradingPanel.setBackground(Color.LIGHT_GRAY);

		mapPanel.add(panel);
		optionsPanel.add(options);
		add(optionsPanel);
		add(mapPanel);
		add(tradingPanel);
		
	}
	
	public void clean(){
		repaint();
	}
	
}
