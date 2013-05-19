import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class UndiscoveredWorlds {
	public static void main(String[] args) {
		Dimension screenRes = Toolkit.getDefaultToolkit().getScreenSize();
		try {
		    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
		UndiscoveredWorldsGUI app = new UndiscoveredWorldsGUI();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(740, 740);
		app.setLocation((int) ((screenRes.getWidth()/2) - (app.getWidth()/2)),
						(int) ((screenRes.getHeight()/2) - (app.getHeight()/2)));
		app.setVisible(true);
		app.setResizable(false);
	}
}
