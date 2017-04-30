package engine.shapes;

import java.awt.Color;

import engine.Screen;
import engine.Transform;
import engine.Vector2f;

public abstract class Shape {
	
	public void render(Screen screen){
		this.render(screen, new Vector2f());
	}
	
	public void render(Screen screen, Color c){
		this.render(screen, c, new Vector2f());
	}
	
	
	public void render(Screen screen, Vector2f offset){
		Transform transform = new Transform();
		transform.setPos(offset);
		this.render(screen, transform);
	}

	public void render(Screen screen, Color c, Vector2f offset){
		screen.getGraphics().setColor(c);
		this.render(screen, offset);
	}
	
	public void render(Screen screen, Color c, Transform transform){
		screen.getGraphics().setColor(c);
		render(screen, transform);
	}
	
	public abstract void render(Screen screen, Transform transform);
	
	public abstract boolean contains(Vector2f sub);

}
