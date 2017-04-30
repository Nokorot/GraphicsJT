import java.awt.Color;
import java.awt.Font;

import engine.Display;
import engine.Entity;
import engine.Screen;
import engine.Vector2f;
import engine.Vector2i;

public class Piece extends Entity {

	private static Font f;
	
	private final int num;
	private int x;
	private int y;

	public Piece(int num, int x, int y) {
		this.num = num;
		this.x = x;
		this.y = y;
		
		f = new Font("Areal", Font.BOLD, 50);
	}

	public void Update(Display display) {
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
 
	
}