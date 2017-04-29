package engine.shapes;

import engine.Vector2f;

public class Line extends Polygon {
	
	private int width;
	private Vector2f p1, p2;
	
	public Line( Vector2f a, Vector2f b, float width ) {
		super( genPoints(a, b, width) );
	}
	
	private static Vector2f[] genPoints(Vector2f a, Vector2f b, float width) {
		Vector2f dir = new Vector2f( b.y - a.y, a.x - b.x );
		dir.mult(width / dir.length() / 2);
		
		return new Vector2f[]{ Vector2f.sub(a, dir), Vector2f.add(a, dir), Vector2f.add(b, dir), Vector2f.sub(b, dir) };
	}
	
	
}
