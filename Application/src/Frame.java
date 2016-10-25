import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;

public class Frame extends JFrame implements WindowListener{

	public WebEngine engine;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Frame frame =new Frame();
		frame.addWindowListener(frame);
		frame.setVisible(true);
	}

	private Frame(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		device.setFullScreenWindow(this);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		final JFXPanel panel = new JFXPanel();

		Platform.runLater(new Runnable() {
			public void run() {
				Scene scene = new Scene(new Browser());
				panel.setScene(scene);
				panel.setSize(1366,760);
				add(panel);
			}
		});
	}

	public void windowOpened(WindowEvent e) {}

	public void windowClosing(WindowEvent e) {}

	public void windowClosed(WindowEvent e) {}

	public void windowIconified(WindowEvent e) {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public void windowDeiconified(WindowEvent e) {}

	public void windowActivated(WindowEvent e) {}

	public void windowDeactivated(WindowEvent e) {
		this.requestFocusInWindow();
	}

}
