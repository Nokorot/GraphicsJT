package test;

import java.awt.Color;

import engine.Application;
import engine.Display;
import engine.Entity;
import engine.Screen;
import engine.Vector2f;
import engine.shapes.Rect;

public class Game extends Application {

	Display display; 
	
	float angle = 0; 
	
	Entity box;
	
	public Game() {
		display = new Display(this, 800, 600, 2);
		display.screen.setViweMatrix(-8, 8, 6, -6);
		
		
		box = new Entity(new Vector2f());
		box.setShape(new Rect(2, 3));
		box.transform.setScale(new Vector2f(3, 3));
		box.transform.setRotate(0);
		box.setColor(Color.BLUE);
				
		add(box);
		
		display.start();
	}
	
	public void Update(Display display) {
		box.transform.setRotate(angle+=2);
	}
	
	public void Render(Screen screen) {
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
}
