import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DrawElement implements ActionListener {
	
	// ****************** KONSTRUKTORY *************************
	
	public DrawElement (Graphics2D buf, int pX, int pY, int w, int h) {
    	buffer = buf;
    	posX = (double)pX;
    	posY = (double)pY;
    	width = w;
    	height = h;
    	image =  new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB);
    }

	public DrawElement (Graphics2D buf, int pX, int pY, String fN) {
    	buffer = buf;
    	posX = (double)pX;
    	posY = (double)pY;
    	loadImage(fN);
    }
    
    // ********************* ZMIENNE ***************************
	
		// wspolny bufor
	protected Graphics2D buffer;
		// obrazek
	protected BufferedImage image;
		// polozenie
	protected double posX;
	protected double posY;
		// wielkosc
	protected int width;
	protected int height;
	
	// ********************* METODY ****************************
	
	private void loadImage(String fileName) {
	    File file =  new File(fileName);
	    
	    try {
			image = ImageIO.read(file);
			width = image.getWidth();
			height = image.getHeight();
		} catch (IOException e) {
			width = 30;
			height = 30;
			image =  new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			e.printStackTrace();
		}
	}
	
	protected <T extends DrawElement> boolean isIn(T obj) {
		int x = x() + w()/2;
		int y = y() + h()/2;
		if (obj.x()<=x && x<=obj.x()+obj.w() && 
			obj.y()<=y && y<=obj.y()+obj.h()) {
			return true;
		}
		return false;
	}
	
    public int x() {
    	return (int)posX;
    }
    
    public int y() {
    	return (int)posY;
    }
    
    public int w() {
    	return width;
    }
    
    public int h() {
    	return height;
    }
    protected void draw() {
	    buffer.drawImage(image, (int)posX, (int)posY, null);
    }
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		draw();
	}

}
