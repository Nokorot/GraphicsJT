package engine;

public class Transform {

	private Vector2f pos = new Vector2f();
	private Vector2f scale = new Vector2f(1, 1);
	private float rotate = 0;
	
	private boolean updated = false;
	private Matrix3f matrix = new Matrix3f();
	
	public Transform() {
	}
	
	public Transform(Transform other){
		this.pos = new Vector2f(other.pos);
		this.scale = new Vector2f(other.scale);
		this.rotate = other.rotate;
	}
	
	public void setPos(Vector2f pos) {
		this.pos = pos;
		updated = false;
	}
	
	public void setPos(float x, float y) {
		this.pos.x = x;
		this.pos.y = y;
	}
	
	public void setScale(Vector2f scale) {
		this.scale = scale;
		updated = false;
	}
	
	public void setScale(float x, float y){
		this.scale.x = x;
		this.scale.y = y;
	}
	
	public void setRotate(float rotate) {
		this.rotate = rotate;
		updated = false;
	}
	
	public Vector2f getPosition() {
		return pos;
	}
	
	public Vector2f getScale() {
		return scale;
	}
	
	public float getRotate() {
		return rotate;
	}
	
	public void translate(Vector2f trans) {
		pos.add(trans);
	}
	
	public void itranslate(Vector2f trans) {
		pos.sub(trans);
	}

	public void scale(Vector2f s) {
		scale.mult(s);
	}

	public void iscale(Vector2f s) {
		scale.div(s);
	}

	public void scale(float s) {
		scale.mult(s);
	}

	public void iscale(float s) {
		scale.div(s);
	}
	
	public void framMatrix(Matrix3f mpos) {
		updated = true;
		matrix = mpos;
		
		
	}
	
	public Matrix3f getTransformMatrix() {
		if (!updated){
			matrix.clear();
			matrix.scale(scale);
			matrix.rotate(rotate);
			matrix.translate(pos);
		}
		return matrix;
	}

	public Vector2f transform(Vector2f vec0) {
		return Matrix3f.multiply(getTransformMatrix(), vec0);
	}
	
}
