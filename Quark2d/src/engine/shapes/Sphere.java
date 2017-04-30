package engine.shapes;

import engine.Screen;
import engine.Transform;
import engine.Vector2f;
import engine.Vector2i;

public class Sphere extends Shape {

	private float radius = 1;
	
	public Sphere() {
	}
	
	public Sphere(float radius){
		this.radius = radius;
	}
	
	public void render(Screen screen, Transform transform) {
		Vector2i pos = screen.asPixelCord(transform.getPosition());
		Vector2i size = screen.asPixelSize(transform.getScale().mult(radius)).abs();
		pos.sub(Vector2i.div(size, 2));
		screen.getGraphics().fillOval(pos.x, pos.y, size.x, size.y);
	}

	public boolean contains(Vector2f sub) {
		return false;
	}

}
