package painting;

import javax.swing.*;

public class DisplayWindow extends JFrame{
	
	public static final long serialVersionUID = 1L;

	public DisplayWindow() {
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(new Draw());
		setSize(1400,800);
		setResizable(false);
		setVisible(true);
		setTitle("DVD Screensaver");
		
	}
	
	public static void main(String[] args) {
	
		new DisplayWindow();
		
	}
	
}
