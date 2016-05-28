import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.ListIterator;


public class Bullet extends Weapon implements Runnable {
	
	// ****************** KONSTRUKTORY *************************
	
	public Bullet(Graphics2D buf, Container dt, int startX, int startY, int dX, int dY, String fN) {
			// ustawienie poczatkowych wartosci
		super(buf, startX, startY, fN);
		data = dt;
		endX = data.width();
		endY = data.height();
		damage = data.weapon().damage();
		speed = data.weapon().speed();
		freezeTime = data.weapon().freezeTime();
	    
	    	// obliczanie przesuniecia
		double difX = dX - startX;
		double difY = dY - startY;
		double div = (Math.abs(difX) > Math.abs(difY) ? Math.abs(difX) : Math.abs(difY));
		dx = difX/div;
		dy = difY/div;
	}

	// ********************* ZMIENNE ***************************
	
		// wskaznik na kontener z danymi
	private Container data;
		// koniec planszy
	private int endX;
	private int endY;
		// przesuniecie
	private double dx;
	private double dy;
    
	// ********************* METODY ****************************
	
	@Override
	public void run() {
		boolean clsin = false;
        while(0<=x() && x()<endX && 0<=y() && y()<=endY && clsin==false) {
            move();
        	clsin = isCollision(data.enemies());
        	if (clsin==false) {
        		clsin = isCollision2(data.coins());
        	}
            try{
                Thread.sleep(data.delay());
            }
            catch(InterruptedException e){
            }
        }
        data.timer().removeActionListener(this);
	}

	private boolean isCollision(LinkedList<Enemy> enemsList) {
        ListIterator<Enemy> iter = enemsList.listIterator();
        while (iter.hasNext()) {
        	Enemy current = iter.next();
            if (isIn(current) == true) {
            	current.setHealth(current.getHealth()-damage);
            	current.enrage();
            	return true;
            }
        }
		return false;
	}
	
	private boolean isCollision2(LinkedList<DrawElement> coins) {
        ListIterator<DrawElement> iter = coins.listIterator();
        while (iter.hasNext()) {
        	DrawElement current = iter.next();
            if (isIn(current) == true) {
            	coins.remove(current);
            	data.timer().removeActionListener(current);
            	int money = data.hero().getMoney();
            	data.hero().setMoney(money+10);
            	return true;
            }
        }
		return false;
	}
	
	
	private void move() {
		posX += speed*dx;
		posY += speed*dy;
	}
	



}
