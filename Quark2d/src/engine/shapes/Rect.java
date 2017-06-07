package engine.shapes;

import engine.Vector2f;

public class Rect extends Polygon {
	
	private float width, height;
	
	public Rect(){
		this(1, 1);
	}
	
	public Rect(float width, float height){
		super( genPoints(width, height) );
		this.width = width;
		this.height = height;
	}
	
	public Rect(Vector2f size){
		super ( genPoints(size.x, size.y) );
		this.width = size.x;
		this.height = size.y;
	}
	
	public Rect(Vector2f size, Vector2f offset) {
		super( genPoints(offset, size) );
	}
	
	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	private static Vector2f[] genPoints(float width, float height){
		float wa = width / 2;
		float ha = height / 2;
		
		return new Vector2f[]{
				new Vector2f(-wa, -ha),
				new Vector2f(wa, -ha),
				new Vector2f(wa, ha),
				new Vector2f(-wa, ha)
		};
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
