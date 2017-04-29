package engine;

public class Transform {

	private Vector2f pos = new Vector2f();
	private Vector2f scale = new Vector2f(1, 1);
	private float rotate = 0;
	
	private boolean updated = false;
	private Matrix3f matrix = new Matrix3f();
	
	public void setPos(Vector2f pos) {
		this.pos = pos;
		updated = false;
	}
	
	public void setScale(Vector2f scale) {
		this.scale = scale;
		updated = false;
	}
	
	public void setRotate(float rotate) {
		this.rotate = rotate;
		updated = false;
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
