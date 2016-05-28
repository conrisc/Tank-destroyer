import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.Timer;


public class Container {

	// ****************** KONSTRUKTORY *************************
	
	public Container(Graphics2D buf, int w, int h, Game obj) {
		
        width = w;
        height = h;
        delay = 30;
        luck = 3;
  
        weapon = new Weapon (buf, 0, 0, "weapon.png");
        enemies = new LinkedList<Enemy>();
        coins = new LinkedList<DrawElement>();
        timer = new Timer(delay, obj);
        generator = new Random();
        int heroX = 50;
        int heroY = height/2;
        hero = new Hero(buf, this, heroX,heroY,100, "hero.png");
	}
	
	// ********************* ZMIENNE ***************************
	

		// rozmiar okna
	private int width;
	private int height;
		// bohater
	private Hero hero;
		// bron
	private Weapon weapon;
		// wrogowie
	private LinkedList<Enemy> enemies;
		// monety
	private LinkedList<DrawElement> coins;
		// opoznienie
	private int delay;
		// timer
	private Timer timer;
		// generator liczb losowych
	private Random generator;
		// szczescie dropu (wieksza liczba, tym mniejsze szanse na drop)
	private int luck;

	
	// ********************* METODY ****************************
	
	public int width() {
		return width;
	}
	
	public int height() {
		return height;
	}
	
	public Hero hero() {
		return hero;
	}
	public Weapon weapon() {
		return weapon;
	}
	public LinkedList<Enemy> enemies() {
		return enemies;
	}
	public LinkedList<DrawElement> coins() {
		return coins;
	}
	public int delay() {
		return delay;
	}
	
	public Timer timer() {
		return timer;
	}
	
	public Random random() {
		return generator;
	}
	public int luck() {
		return luck;
	}
}
