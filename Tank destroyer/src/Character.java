import java.awt.Graphics2D;


public class Character extends DrawElement {
	
    // ****************** KONSTRUKTORY *************************
	
	public Character(Graphics2D buf, int pX, int pY, int mH, String fN) {
		super(buf, pX, pY, fN);
		maxHealth = mH;
		health = mH;
	}
	
	// ********************* ZMIENNE ***************************
	
		// zycie
	private int health;
	private final int maxHealth;
	// pasek zycia
	protected LifeTime lifetime;

	// ********************* METODY ****************************
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int arg0) {
		health = arg0;
		double prcnt = (double)health/(double)maxHealth;
		if (0<=prcnt && prcnt<=1)
			lifetime.setPr(prcnt);
		else
			lifetime.setPr(0);
	}
	
	public int maxHlth() {
		return maxHealth;
	}


}
