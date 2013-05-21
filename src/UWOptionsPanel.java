import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class UWOptionsPanel extends JFrame implements ActionListener, WindowFocusListener{
	
	private JButton saveButton;
	private JButton loadButton;
	private JButton menuButton;
	private JButton returnButton;
	
	public UWOptionsPanel(){
		super("Options");
		setLayout(new GridLayout(4,1));
		setBackground(Color.DARK_GRAY);
		requestFocus();
		addWindowFocusListener(this);
	
		saveButton = new JButton("Save Game");
		saveButton.addActionListener(this);
		saveButton.setFocusPainted(false);
		
		loadButton = new JButton("Load Game");
		loadButton.addActionListener(this);
		loadButton.setFocusPainted(false);
		
		menuButton = new JButton("Main Menu");
		menuButton.addActionListener(this);
		menuButton.setFocusPainted(false);
		
		returnButton = new JButton("Return to Game");
		returnButton.addActionListener(this);
		returnButton.setFocusPainted(false);
		
		add(saveButton);
		add(loadButton);
		add(menuButton);
		add(returnButton);
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == saveButton){
			System.out.print("save");
		}else if(e.getSource() == loadButton){
			System.out.print("laod");
		}else if(e.getSource() == returnButton){
			dispose();
		}
	}

	public void windowGainedFocus(WindowEvent arg0) {
	}


	public void windowLostFocus(WindowEvent arg0) {
    	dispose();
	}

}
