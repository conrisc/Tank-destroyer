import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Game
    extends JPanel 
    implements ActionListener, MouseListener
{

	// ****************** KONSTRUKTORY *************************
	
	public Game() {
		
        JFrame frame = new JFrame("Game");
        frame.getContentPane().add(this);
        frame.setLocation(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        addMouseListener(this);

    }
	// ********************* ZMIENNE ***************************
	
    	// UID
	private static final long serialVersionUID = -4278205180931902927L;
    	// bufor
    private Image image;
    	// wykreslacz ekranowy
    private Graphics2D device;
		// wykreslacz bufora
	private Graphics2D buffer;
		// kontener z danymi rozgrywki
	private Container data;
    	// spawn wrogow
    private int respawn = 0;
    
    	
    
	// ********************* METODY ****************************
    
    public Dimension getPreferredSize(){
        return new Dimension(700, 400);
    }
    
    public void letsPlay(){

    	
        image = createImage(getWidth(), getHeight());
        buffer = (Graphics2D)image.getGraphics();
        buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                RenderingHints.VALUE_ANTIALIAS_ON);
        device = (Graphics2D)getGraphics();
        device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                RenderingHints.VALUE_ANTIALIAS_ON);
        buffer.setBackground(new Color(240,240,240));
        
        data = new Container(buffer, getWidth(), getHeight(), this);
        
        // Elementy statyczne
        DrawElement wall = new DrawElement(buffer, data.hero().x()+data.hero().w(), 0, "wall.png");
        data.timer().addActionListener(wall);
        //******************//
        
        data.timer().addActionListener(data.hero());
        data.timer().start();

    }
    public void actionPerformed(ActionEvent evt){
    		// stan gry
    	buffer.drawString("Health:", 10, 20);
    	buffer.drawString("Points: "+ data.hero().getPoints(), 10, 50);
    	buffer.drawString("Money: $"+ data.hero().getMoney(), 10, 70);
    		// opcje
    	buffer.drawString("Upgrade damage", 1, 280);
    	buffer.drawString("Cost: $"+data.weapon().cost()[0], 1, 300);
    	buffer.drawString("Upgrade speed", 1, 320);
    	buffer.drawString("Cost: $"+data.weapon().cost()[1], 1, 340);
    	buffer.drawString("Upgrade reload", 1, 360);
    	buffer.drawString("Cost: $"+data.weapon().cost()[2], 1, 380);
    	
    		// wykreslanie bufora
        device.drawImage(image, 0, 0, null);
        buffer.clearRect(0, 0, getWidth(), getHeight());
        	// 
        data.weapon().reduceGuard();
        int spawn = 120-data.hero().getPoints()/3;
        spawn = (spawn>9) ? spawn : 10;
        if ((respawn++) == spawn) {
        	int startY = data.random().nextInt(getHeight()-50);
        	int startX = getWidth();
        	Enemy enemy = new Enemy(buffer, data, startX, startY, 40+data.hero().getPoints()/2, "enemy.png");
        	data.enemies().add(enemy);
        	new Thread(enemy).start();
        	data.timer().addActionListener(enemy);
        	respawn = 0;
        }
        if (data.hero().getHealth()<=0) {
        	data.timer().stop();
        	System.exit(0);
        }
    }

	@Override
	public void mouseClicked(MouseEvent arg0) {}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	
	@Override
	public void mouseExited(MouseEvent arg0) {}
	
	@Override
	public void mousePressed(MouseEvent arg0) {		
		int desX = arg0.getX();
		int desY = arg0.getY();
		if (desX>data.hero().x()+data.hero().w() && data.weapon().guard()==0) {
			Bullet bullet = new Bullet(buffer, data, data.hero().x()+data.hero().w(), data.hero().y()+data.hero().h()/2-10, desX-10, desY-10, "bullet.png");
			new Thread(bullet).start();
			data.timer().addActionListener(bullet);
			data.weapon().setGuard(data.weapon().freezeTime());
		}
		else if (0<desX && desX<data.hero().x()+data.hero().w() && 
				270<desY && desY<=305 &&
				data.hero().getMoney()>=data.weapon().cost()[0]) {
			data.hero().setMoney(data.hero().getMoney()-data.weapon().cost()[0]);
			data.weapon().upgradeDamage();
		}
		else if (0<desX && desX<data.hero().x()+data.hero().w() && 
				305<desY && desY<=340 &&
				data.hero().getMoney()>=data.weapon().cost()[1]) {
			data.hero().setMoney(data.hero().getMoney()-data.weapon().cost()[1]);
			data.weapon().upgradeSpeed();
		}
		else if (0<desX && desX<data.hero().x()+data.hero().w() && 
				340<desY && desY<=375 &&
				data.hero().getMoney()>=data.weapon().cost()[2]) {
			data.hero().setMoney(data.hero().getMoney()-data.weapon().cost()[2]);
			data.weapon().reduceFT();
		}
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {}
	
	
	public static void main(String[] args) {
			// start gry
		Game ply = new Game();
		ply.letsPlay();
    }
}