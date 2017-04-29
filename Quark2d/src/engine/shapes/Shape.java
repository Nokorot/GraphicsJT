package engine.shapes;

import java.awt.Color;

import engine.Screen;
import engine.Vector2f;

public abstract class Shape {
	
	public void render(Screen screen){
		this.render(screen, new Vector2f());
	}
	
	public void render(Screen screen, Color c){
		this.render(screen, c, new Vector2f());
	}
	
	public void render(Screen screen, Color c, Vector2f offset){
		screen.getGraphics().setColor(c);
		this.render(screen, offset);
	}
	
	public abstract void render(Screen screen, Vector2f offset); // Transform instead 

	public abstract boolean contains(Vector2f sub);

}
