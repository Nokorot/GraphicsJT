package engine.shapes;

import engine.Vector2f;

public class Rect extends Polygon {
	
	public Rect(){
		this(new Vector2f(), new Vector2f(1, 1));
	}
	
	public Rect(Vector2f pos, Vector2f size) {
		super( genPoints(pos, size) );
	}

	private static Vector2f[] genPoints(Vector2f pos, Vector2f size) {
		pos = Vector2f.sub(pos, Vector2f.div(size, 2));
		
		return new Vector2f[]{
				new Vector2f(pos),
				new Vector2f(pos.x + size.x, pos.y),
				new Vector2f(pos.x + size.x, pos.y + size.x),
				new Vector2f(pos.x, pos.y + size.y)
		};
	}

	public boolean contains(Vector2f sub) {
		if (sub.x < fpos[0].x) return false;
		if (sub.y < fpos[0].y) return false;
		if (sub.x > fpos[2].x) return false;
		if (sub.y > fpos[2].y) return false;
		return true;
	}
	
}
