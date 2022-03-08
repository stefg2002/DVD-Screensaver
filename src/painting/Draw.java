package painting;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Draw extends JPanel implements ActionListener{
	
	public static final long serialVersionUID = 1L;
	
	Random r = new Random();
	Timer t = new Timer(2,this);
	
	int x=r.nextInt(1251-1)+1, y=r.nextInt(686-1)+1, velX = 1, velY = 1, cornerCount;

	public ImageObserver IO;
	public Image image;

	/**
	 * Default constructor for the 'Draw' class
	 */
	public Draw(){
		
			try {
				
				image = ImageIO.read(new File("assets/dvdwhite.png"));
				
			} 
				catch (IOException e) {

				e.printStackTrace();
			}
		
	}

	/**
	 * This method overrides the paintComponent method in JComponent, used for
	 * displaying graphics such as images, shapes and text.
	 * @param g refers to the object of the Graphics class
	 */
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		this.setBackground(Color.BLACK);
		
		g.setFont(customFont());
		
		g.setColor(Color.RED);
		
		g.drawString("Corner Hits: " + cornerCount, 600, 685);
		
		g.drawImage(image,x,y,150,100,IO);
		
		t.start();
		
	}

	/**
	 * Method is implemented from ActionListener class. Checks if image hits the wall, then it reverses its
	 * velocity based on the axis that it hit.
	 * If image hits the corner, then it adds to 'cornerCount'.
	 * @param e refers to the ActionEvent class.
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(x < 0 || x > 1250) {
		
			velX = -velX;
			chooseImage();

		}
		
		if(y < 0 || y > 685) {
			
			velY = -velY;
			chooseImage();
			
			
		}
		
		x+=velX;
		y+=velY;
		
		if((x == -1 && y==-1) || (x==-1 && y==686) || (x==1251 &&  y==-1) || (x==1251 && y==686)) {
			
				cornerCount++;

		}
		
		
		repaint();
		
	}

	/**
	 * Method is used for creating a custom font from a .ttf file.
	 * @return retuns the customFont if the file is found, otherwise, returns null.
	 */
	public Font customFont() {	
		
		try {
			Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("assets/digit.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);
			customFont = customFont.deriveFont(40F);
			
			return customFont;
			
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return null;
	}

	/**
	 * Method plays a .ogg sound file.
	 * @param s is the path of the .ogg file
	 */
	public void playSound(String s) {
		
		try {
			
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(s)));
			clip.start();
			
		}catch(Exception e) {}
		
	}


	/**
	 * Method randomly changes the image, called when image hits the wall.
	 */
	public void chooseImage() {
		
		int choice = r.nextInt(9-1)+1;
		
		if(choice == 1) {
			
			changeImage("assets/dvdred.png");
			
		}
		
		else if(choice == 2) {
			
			changeImage("assets/dvdorange.png");
			
		}
		
		else if(choice == 3) {
			
			changeImage("assets/dvdyellow.png");
			
		}
		
		else if(choice == 4) {
			
			changeImage("assets/dvdgreen.png");
			
		}
		
		else if(choice == 5) {
			
			changeImage("assets/dvdblue.png");
			
		}
		
		else if(choice == 6) {
			
			changeImage("assets/dvdpurple.png");
			
		}
		
		else if(choice == 7) {
			
			changeImage("assets/dvdpink.png");
			
		}
		
		else {
			
			changeImage("assets/dvdwhite.png");
			
		}
		
		
	}

	/**
	 * Method changes the path of the image file
	 * @param s is the path of the .png image file
	 */
	public void changeImage(String s) {
		
		try {
			
			image=ImageIO.read(new File(s));
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
