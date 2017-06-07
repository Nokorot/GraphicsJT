import java.awt.Color;
import java.awt.Font;

import engine.Display;
import engine.Entity;
import engine.Screen;
import engine.Transform;
import engine.Vector2f;
import engine.Vector2i;
import engine.shapes.Rect;

public class Piece extends Entity {

	private static Font f;
	
	private final int num;
	private Table table;
	private float width;
	private float height;
	private int x;
	private int y;

	public Piece(Table table, int num, int x, int y, float width, float height) {
		this.table = table;
		Rect rect = new Rect(new Vector2f(1, 1));
		
		transform.setScale(width, height);
		
		setTransform(x, y);
		
		this.num = num;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.shape = rect;
		
		f = new Font("Areal", Font.BOLD, 50);
	}
	
	public void setX(int x) {
		this.x = x;
		setTransform(x, y);
	}
	
	public void setY(int y) {
		this.y = y;
		setTransform(x, y);
	}

	private void setTransform(int x, int y){
		Transform transform = new Transform();
		transform.translate(new Vector2f(1,1).mult(table.size).div(width));
		//transform.itranslate(getPosition());
		transform.itranslate(Vector2f.div(table.size, 2));
		transform.translate(new Vector2f(x, y).mult(width));
		
		System.out.println(width);
		
//		System.out.println(transform.getPosition());
		
		this.setTransform(transform);
	}
	
	public void Update(Display display) {
	}
	

	public void Render(Screen screen) {
		super.Render(screen);

		Vector2f pos = getPosition();
		shape.render(screen, Color.WHITE, pos);
		Vector2i p = screen.asPixelCord(pos);
		screen.getGraphics().setColor(Color.BLACK);
		screen.getGraphics().setFont(f);
		screen.getGraphics().drawString(""+num, (int) p.x, (int) p.y);
	}
 
	
}