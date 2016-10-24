import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Frame extends JFrame implements WindowListener{

	public WebEngine engine;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Frame frame =new Frame();
		frame.loadURL("http://www.kcom.ac.jp/");
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
				WebView view = new WebView();
				engine = view.getEngine();
				Scene scene = new Scene(view, 100, 200);
				view.setMaxSize(scene.getWidth(), scene.getHeight());
				view.maxWidthProperty().bind(scene.widthProperty());
				view.maxHeightProperty().bind(scene.heightProperty());
				panel.setScene(scene);
				add(panel);
			}
		});
	}

	public void loadURL(String url){
		engine.load(url);
	}

	public void windowOpened(WindowEvent e) {}

	public void windowClosing(WindowEvent e) {}

	public void windowClosed(WindowEvent e) {}

	public void windowIconified(WindowEvent e) {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		System.out.println("a");
	}

	public void windowDeiconified(WindowEvent e) {}

	public void windowActivated(WindowEvent e) {}

	public void windowDeactivated(WindowEvent e) {}

}
