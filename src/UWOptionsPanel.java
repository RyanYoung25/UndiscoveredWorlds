import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UWOptionsPanel extends JFrame implements ActionListener{
	
	private JButton saveButton;
	private JButton loadButton;
	
	public UWOptionsPanel(){
		super("Options");
		setLayout(new GridLayout(2,1));
		setPreferredSize(new Dimension(370, 370));
		setBackground(Color.LIGHT_GRAY);
	
		saveButton = new JButton("Save Game");
		saveButton.addActionListener(this);
		
		loadButton = new JButton("Load Game");
		loadButton.addActionListener(this);
		
		add(saveButton);
		add(loadButton);
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == saveButton){
			System.out.print("save");
		}else if(e.getSource() == loadButton){
			System.out.print("laod");
		}
	}

}
