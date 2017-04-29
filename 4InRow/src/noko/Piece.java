package noko;

import java.awt.Color;
import java.awt.Font;

import engine.Display;
import engine.Entity;
import engine.Matrix3f;
import engine.Screen;
import engine.Vector2f;
import engine.Vector2i;
import engine.shapes.Shape;

public class Piece extends Entity {

	private static Font f;
	
	int x;
	int y;

	final int num;

	private Shape shape;
	private Matrix3f pos;

	public Piece(int num, int x, int y, Matrix3f pos, Shape shape) {
		super(new Vector2f());
		
		this.num = num;
		this.x = x;
		this.y = y;
		this.pos = pos;
		this.shape = shape;
		
		f = new Font("Areal", Font.BOLD, 50);
	}

	public void Update(Display display) {
	}
	
	public boolean mousePress(Display display){
		if(shape.contains(Vector2f.sub(display.mouse, getPos()))){
			return true;
		}
		return false;
	}

	public void Render(Screen screen) {
		super.Render(screen);
		Vector2f pos = getPos();
		shape.render(screen, pos);
		Vector2i p = screen.asPixelCord(pos);
		screen.getGraphics().setColor(Color.BLACK);
		screen.getGraphics().setFont(f);
		screen.getGraphics().drawString(""+num, (int) p.x, (int) p.y);
	}

	private Vector2f getPos(){
		return Matrix3f.multiply(pos, new Vector2f(x, y));
	}
	
}
