package test;

import java.awt.Color;

import engine.Application;
import engine.Display;
import engine.Entity;
import engine.Screen;
import engine.Vector2f;
import engine.shapes.Rect;
import engine.shapes.Sphere;

public class Game extends Application {

	Display display; 
	
	float angle = 0; 
	
	Entity box, sphere;
	
	public Game() {
		display = new Display(this, 800, 600, 2);
		display.screen.setViweMatrix(-8, 8, 6, -6);
		
		box = new Entity(new Vector2f());
		box.setShape(new Rect());
		box.transform.setScale(new Vector2f(2,2));
		box.transform.setRotate(30);
		box.setColor(Color.BLUE);
		
		add(box);
		
		sphere = new Entity();
		sphere.setShape(new Sphere());
		sphere.setColor(Color.RED);
		sphere.transform.setScale(new Vector2f(3, 3));
		sphere.transform.setPos(new Vector2f(-1,-1));
		add(sphere);
		
		display.start();
	}
	
	public void Update(Display display) {
//		box.transform.setRotate(angle+=2);
	}
	
	public void Render(Screen screen) {
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
}
