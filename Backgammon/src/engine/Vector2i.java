package engine;

public class Vector2i {
	
	public int x, y;
	
	public Vector2i(Vector2i other){
		this.x = other.x;
		this.y = other.y;
	}
	
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String toString() {
		return this.getClass().getName() + "[" + x + ", " + y + "]";
	}

	public Vector2i multiply(Vector2i other) {
		return new Vector2i(x * other.x, y * other.y);
	}

	public Vector2f asFloat() {
		return new Vector2f(x, y);
	}

	public Vector2i abs() {
		x = x < 0 ? -x : x;
		y = y < 0 ? -y : y;
		return this;
	}

	public Vector2i add(Vector2i other){
		this.x += other.x;
		this.y += other.y;
		return this;
	}
	
	public Vector2i sub(Vector2i other) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}

	public Vector2i div(int i) {
		this.x /= i;
		this.y /= i;
		return this;
	}
	
	
	public static Vector2i add(Vector2i a, Vector2i b){
		return new Vector2i(a).add(b);
	}

	public static Vector2i div(Vector2i a, int i) {
		return new Vector2i(a).div(i);
	}
}
