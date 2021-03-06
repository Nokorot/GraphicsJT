package engine;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Display extends Canvas	 {
	private static final long serialVersionUID = 1L;
	
	private Rectangle sceenbounds;
	
	public Vector2f mouse = new Vector2f();
	public boolean mouseDown = false;
	public boolean mousePressed = false, mouseRelaesed = false;
	private boolean mousePressedNow = false, mouseReleasedNow = false;
	
	private Application parent;
	private JFrame frame;
	
	private boolean running = false;
	private Thread thread;

	private Color background = new Color(0x2b231b);
	public Screen screen;
	
	public Display(Application patent, int width, int height, float scale) {
		this.setSize(width, height);
		screen = new Screen(width, height, scale);
		sceenbounds = this.getBounds();
		this.parent = patent; 
		
		frame = new JFrame("Title");
		frame.setDefaultCloseOperation(3);
		frame.add(this);
		frame.pack();
		frame.setLocationRelativeTo(null);

		this.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
				mouse = fromDisplayToScreenCord(e.getX(), e.getY());
			}
			public void mouseDragged(MouseEvent e) {
				mouse = fromDisplayToScreenCord(e.getX(), e.getY());
			}
		});
		
		this.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(MouseEvent e) {
				mouseDown = mousePressedNow = true;
			}
			
			public void mouseReleased(MouseEvent e) {
				mouseDown = !(mouseReleasedNow = true);
			}
			
			public void mouseExited(MouseEvent e) {
				mouseDown = !(mouseReleasedNow = true);
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		
		frame.setVisible(true);
	}
	
	public void start(){
		if (running) return;
		running = true;
		thread = new Thread(() -> run());
		thread.start();
	}
	
	public void stop(){
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void run(){
		while (running) {
			update();
			render();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void update(){
		mousePressed = mousePressedNow;
		mouseRelaesed = mouseReleasedNow;
		parent.update();
		if (mousePressed)
			mousePressedNow = false;
		mousePressed = false;
		if (mouseRelaesed)
			mouseReleasedNow = false;
		mouseRelaesed = false;
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(background);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());

		screen.prepare();
		parent.render(screen);
		sceenbounds = screen.render(g, frame.getContentPane().getBounds());
		screen.disose();
//		g.setFont(new Font("Verdana", 2, 50));
//		g.setColor(Color.yellow);
//		g.drawString(fps + "fps", 20, 50);
		g.dispose();
		bs.show();
	}
	
	public Vector2f fromDisplayToScreenCord(int x, int y){
		x = (x - sceenbounds.x) * screen.width / sceenbounds.width; 
		y = (y - sceenbounds.y) * screen.height / sceenbounds.height;
		return screen.fromPixelCord(x, y);
	}
	
}
