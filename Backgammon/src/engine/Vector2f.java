package engine;

public class Vector2f {
	
	public float x, y;
	
	public Vector2f(Vector2f other){
		this.x = other.x;
		this.y = other.y;
	}
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2f(){
		this(0, 0);
	}

	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2f add(Vector2f other) {
		this.x += other.x;
		this.y += other.y;
		return this;
	}
	
	public Vector2f sub(Vector2f other) {
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}
	
	public Vector2f mult(Vector2f other) {
		this.x *= other.x;
		this.y *= other.y;
		return this;
	}

	public Vector2f div(Vector2f other) {
		this.x /= other.x;
		this.y /= other.y;
		return this;
	}

	public Vector2f div(float d) {
		this.x /= d;
		this.y /= d;
		return this;
	}

	public float sqrLength(){
		return x * x + y * y;
	}
	
	public float length() {
		return (float) Math.sqrt(sqrLength());
	}
	
	public Vector2i floor() {
		return new Vector2i((int) x, (int) y);
	}

	public Vector2f scale(float s) {
		return new Vector2f(x * s, y * s);
	}

	@Override
	public String toString() {
		return getClass().getName() + "[" + x + ", " + y + "]";
	}

	public static Vector2f sub(Vector2f a, Vector2f b) {
		return new Vector2f(a).sub(b);
	}

	public static Vector2f div(Vector2f a, Vector2f b) {
		return new Vector2f(a).div(b);
	}

	public static Vector2f div(Vector2f vec, int i) {
		return new Vector2f(vec).div(i);
	}
}
