import java.awt.Graphics2D;


public class LifeTime extends DrawElement {

	// ****************** KONSTRUKTORY *************************
	
	public LifeTime(Graphics2D buf, int pX, int pY, int w, int h) {
		super(buf, pX, pY, w, h);
		prcnt = 1;
		colors = new int[w()];
		background = new int[w()];
    	int red = 50;
    	int green = 255 - 2*w;
    	int blue = 20;
	    for (int i = 0; i < w(); i++) {
	    	colors[i] = (red<<16) | (green<<8) | blue ;
	    	green+=2;
	    	background[i] = 0;
	    }
		image.setRGB(0, 0, w(), h(), colors, 0, 0);
	}
	
	
	// ********************* ZMIENNE ***************************
	
		// procent
	protected double prcnt;
		// tablica kolorow tla (czarny)
	protected int[] background;
		// tablica kolorow (zielony)
	protected int[] colors;
	
	// ********************* METODY ****************************
	
	public void move(double arg0) {
		posX += arg0;
	}
	
	public void setPr(double arg0) {
		prcnt = arg0;
		int wGreen = (int)(w() * prcnt);
		image.setRGB(wGreen, 0, w()-wGreen, h(), background, 0, 0);
	}

}