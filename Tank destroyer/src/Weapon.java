import java.awt.Graphics2D;


public class Weapon extends DrawElement {
	
	// ****************** KONSTRUKTORY *************************
	
	public Weapon(Graphics2D buf, int pX, int pY, String fN) {
		super(buf, pX, pY, fN);
			// domyslne ustawienia
		damage = 20;
		speed = 1;
		freezeTime = 30;
		guard = 0;
		cost = new int[3];
		for (int i=0;i<3;i++)
			cost[i] = 10;
	}
	
	// ********************* ZMIENNE ***************************
		
		// obrazenia
	protected int damage;
		// predkosc pocisku
	protected int speed;
		// czas przeladowania
	protected int freezeTime;
		// odmierzacz czasu
	protected int guard;
		// koszt ulepszen
	protected int[] cost;
		

	
	// ********************* METODY ****************************
	
	public int damage() {
		return damage;
	}
	
	public int speed() {
		return speed;
	}
	
	public int freezeTime() {
		return freezeTime;
	}
	
	public int guard() {
		return guard;
	}
	
	
	public void upgradeDamage() {
		damage+=5;
		cost[0]+=8;
	}
	
	public void upgradeSpeed() {
		speed++;
		cost[1]*=1.8;
	}
	public void reduceFT() {
		if (freezeTime>0) {
			freezeTime-=2;
			cost[2]+=17;
		}
	}
	
	public void setGuard(int arg0) {
		guard = arg0;
	}
	
	public void reduceGuard() {
		if (guard>0) 
			guard--;
	}
    public int[] cost() {
    	return cost;
    }
    


}
