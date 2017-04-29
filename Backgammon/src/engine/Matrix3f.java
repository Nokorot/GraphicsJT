package engine;

public class Matrix3f {

	public float[] data = new float[3 * 3]; 
	
	public Matrix3f() {
		for (int i = 0; i < this.data.length; i++)
			this.data[i] = 0;
	}
	
	private Matrix3f(float[] data){
		for (int i = 0; i < this.data.length; i++)
			this.data[i] = data[i];
	}
	
	public static Matrix3f Identity(){
		Matrix3f m = new Matrix3f();
		for (int i = 0; i < 3; i++)
			m.set(i, i, 1);
		return m;
	}
	
	public static Matrix3f viewMatrix(float l, float r, float t, float b, int w, int h){
		Matrix3f m = Identity();
		m.set(0, 0, (r- l) / w);
		m.set(1, 1, (b- t) / h);
		m.set(2, 0, l);
		m.set(2, 1, t);
		return m;
	}

//	public Vector2f multiply(Vector2f pos) {
//		float x = get(0, 0) * pos.x + get(1, 0) * pos.y + get(2, 0);
//		float y = get(0, 1) * pos.x + get(1, 1) * pos.y + get(2, 1);
//		
//		return new Vector2f(x, y);
//	}
	
	public float get(int x, int y) {
		return data[x + y * 3];
	}
	
	public void set(int x, int y, float value) {
		data[x + y * 3] = value;
	}
	
	public static void main(String[] args) {
		Matrix3f view = viewMatrix(-1, 1, -1, 1, 100, 100);
		
		Vector2f pos = new Vector2f(50, 75);
		pos = Matrix3f.multiply(view, pos);
		
		System.out.println(pos);
	}

	public static Vector2f multiply(Matrix3f mtx, Vector2f pos) {
		float x = mtx.get(0, 0) * pos.x + mtx.get(1, 0) * pos.y + mtx.get(2, 0);
		float y = mtx.get(0, 1) * pos.x + mtx.get(1, 1) * pos.y + mtx.get(2, 1);
		
		return new Vector2f(x, y);
	}
	
}
