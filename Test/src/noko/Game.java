package noko;

import java.awt.Color;
import java.awt.Graphics;

import engine.Application;
import engine.Display;
import engine.Entity;
import engine.Screen;
import engine.Vector2f;
import engine.Vector2i;

public class Game extends Application {
	
	private Display display;
	
	public Game() {
		display = new Display(this, 800, 600, 1.2F);
//		display.screen.setViweMatrix(-8, 8, 6, -6);
		display.screen.setViweMatrix(0, 8/6f, 0, 1);
		
		add(new Ball());
				
		display.start();
	}
	
	public static void main(String[] args) {
		new Game();
	}
}

class Ball extends Entity {

	private final Vector2f g = new Vector2f(0, .003f); 
	
	
	private final Vector2f size = new Vector2f(.1f, .1f);
	private Vector2f pos, vel;
	
	private Color color = Color.RED;
	
	public Ball() {
		pos = new Vector2f(.5f, .2f);
		vel = new Vector2f();
	}
	
	public void Update(Display display) {
		vel.add(g);
		pos.add(vel);
		if (pos.x < 0) { pos.x = -pos.x; vel.x*=-1; }
		if (pos.x > 1) { pos.x = 1-pos.x; vel.x*=-1; };
		if (pos.y < 0) { pos.y = -pos.y; vel.y*=-1; }
		if (pos.y > 1) { pos.y = 1-(pos.y-1); vel.y*=-1; };
		
		System.out.println(pos);
	}

	
	public void Render(Screen screen) {
		super.Render(screen);
		
		screen.drawOval(color, pos, size);
	}
	
	protected void Draw(Graphics g) {
//		Display dl = getDisplay();
		
		g.setColor(color);
//		dl.DrawOval(g, pos, size);
	}
	
}
