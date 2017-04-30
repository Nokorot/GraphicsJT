package noko;

import java.awt.Color;
import java.awt.Font;

import engine.Display;
import engine.Entity;
import engine.Screen;
import engine.Vector2f;
import engine.Vector2i;
import engine.shapes.Shape;

public class Piece extends Entity {

	private static Font f;
	
	int x;
	int y;

	final int num;

	public Piece(int num, int x, int y, Shape shape) {
		this.num = num;
		this.x = x;
		this.y = y;
		this.shape = shape;
		
		f = new Font("Areal", Font.BOLD, 50);
	}

	public void Update(Display display) {
	}
	
	public boolean mousePress(Display display){
		if(shape.contains(Vector2f.sub(display.mouse, getPosition()))){
			return true;
		}
		return false;
	}

	public void Render(Screen screen) {
		super.Render(screen);
		Vector2f pos = getPosition();
		shape.render(screen, pos);
		Vector2i p = screen.asPixelCord(pos);
		screen.getGraphics().setColor(Color.BLACK);
		screen.getGraphics().setFont(f);
		screen.getGraphics().drawString(""+num, (int) p.x, (int) p.y);
	}

//	public Vector2f getPosition(){
//		return transform.transform(new Vector2f(x, y));
//	}
	
}
