import java.awt.Graphics2D;


public class Hero 
	extends Character 
{
	// ****************** KONSTRUKTORY *************************
	
	public Hero(Graphics2D buf, Container data, int pX, int pY, int hlth, String fN) {
			// ustawienie poczatkowych wartosci
		super (buf,pX,pY,hlth,fN);
		lifetime = new LifeTime(buffer, 5 , 25, 90, 7);
		setPoints(0);
		setMoney(0);
		data.timer().addActionListener(lifetime);
		
	}
	
	// ********************* ZMIENNE ***************************
	
		// pieniadze
	private int money;
		// punkty
	private int points;
	
	// ********************* METODY ****************************
    
    public int getPoints() {
    	return points;
    }
    
    public void setPoints(int arg0) {
    	points = arg0; 
    }

	public int getMoney() {
		return money;
	}
	
    public void setMoney(int arg0) {
    	money = arg0; 
    }

	
 



}
