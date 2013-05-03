import java.awt.Cursor;

import javax.swing.JFrame;

public class UndiscoveredWorlds {
	public static void main(String args[]) {
		UndiscoveredWorldsGUI app = new UndiscoveredWorldsGUI();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(1280, 720);
		app.setVisible(true);
		app.setResizable(false);
	}
}
