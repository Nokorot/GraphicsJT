import java.awt.Font;

import engine.Display;
import engine.Entity;
import engine.Screen;

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
	}


	
}