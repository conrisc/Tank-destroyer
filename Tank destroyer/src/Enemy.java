import java.awt.Graphics2D;


public class Enemy extends Character implements Runnable {
	
    // ****************** KONSTRUKTORY *************************

	public Enemy(Graphics2D buf, Container dt, int pX, int pY, int hlth, String fN) {
			// ustawianie poczatkowych wartosci
		super(buf, pX, pY,hlth,fN);
		enrage = 0;
		speed = 1;
		data = dt;
		desX = data.hero().x()+data.hero().w()+10;
		lifetime = new LifeTime(buffer, x() ,y()+1, 50, 3);
		
		
			// ustawianie przesuniecia
		dx = -1;
	}
	
	// ********************* ZMIENNE ***************************
	
		// wskaznik na kontener z danymi
	private Container data;
		// cel
	private int desX;
		// przesuniecie
	private double dx;
		// mnoznik
	private int speed;
    	// czy trafiony
    private int enrage;
    
	// ********************* METODY ****************************
    
	@Override
	public void run() {
        while(x()>desX && 0<getHealth()) {
            move();
            if (enrage>0) {
            	enrage--;
            	if (enrage==0) 
            		data.timer().removeActionListener(lifetime);
            }
            try{
                Thread.sleep(data.delay());
            }
            catch(InterruptedException e){
            }
        }
        if (enrage>0) data.timer().removeActionListener(lifetime);
        if (x()<=desX) data.hero().setHealth(data.hero().getHealth()-5);
        else {
        	if (data.random().nextInt(data.luck()) == data.luck()-1) {
        		DrawElement coin = new DrawElement(buffer,x(),y(),"coin.png");
        		data.coins().add(coin);
        		data.timer().addActionListener(coin);
        	}
        	int pkt = data.hero().getPoints();
        	data.hero().setPoints(pkt+1);
        }
        data.timer().removeActionListener(this);
        data.enemies().remove(this);
	}
    
	private void move() {
		posX += speed*dx;
		lifetime.move(speed*dx);
	}
	
	public void enrage() {
		if (enrage==0) data.timer().addActionListener(lifetime);
		enrage = 70;
	}
	
}
