package engine.shapes;

import engine.Screen;
import engine.Vector2f;
import engine.Vector2i;

public class Polygon extends Shape {

	protected final Vector2f[] fpos;

	public Polygon(Vector2f... fpos){
		this.fpos = fpos;
	}

	public void render(Screen screen, Vector2f offset) {
		int[] xi = new int[4];
		int[] yi = new int[4];

		Vector2i spos; int i = 0;
		for (Vector2f p : fpos){
			spos = screen.asPixelCord(Vector2f.add(p, offset));
			xi[i] = spos.x;
			yi[i++] = spos.y;
		}
		
		
//		int w = screen.asPixelSize(new Vector2f(width, width)).abs().x; // sceen.asPixelWidth();

		screen.getGraphics().fillPolygon(xi, yi, fpos.length);
	}
	
	public boolean contains(Vector2f sub) {
//		return ;
		// TODO: 
		
		return false;
	}
	

}
