package engine;

public class Matrix3f {

	public float[] data = new float[3 * 3]; 
	
	public Matrix3f() {
		for (int i = 0; i < this.data.length; i++)
			this.data[i] = 0;
		
		for (int i = 0; i < 3; i++)
			set(i, i, 1);
	}
	
	public Matrix3f(float[] data){
		if (data.length != 9)
			throw new RuntimeException("Data length invalid!");
		this.data = data;
	}
	
	public static Matrix3f viewMatrix(float l, float r, float t, float b, int w, int h){
		Matrix3f m = new Matrix3f(new float[]{
			(r-l)/w,    0   ,  l,
				0  , (b-t)/h,  t,
				0  ,    0   ,  1
		});
//		m.set(0, 0, (r- l) / w);
//		m.set(1, 1, (b- t) / h);
//		m.set(2, 0, l);
//		m.set(2, 1, t);
		return m;
	}

	public Matrix3f multiply(Matrix3f mtx) {
		Matrix3f result = new Matrix3f();
		for (int y = 0; y < 3; y++)
			for (int x = 0; x < 3; x++){
				float s = 0;
				for (int i = 0; i < 3; i++)
					s += get(x, i) * mtx.get(i, y);
				result.set(x, y, s);
			}
		this.data = result.data;
		return result;
	}
	
	public float get(int x, int y) {
		return data[x + y * 3];
	}
	
	public void set(int x, int y, float value) {
		data[x + y * 3] = value;
	}
	
	public static Vector2f multiply(Matrix3f mtx, Vector2f pos) {
		float x = mtx.get(0, 0) * pos.x + mtx.get(1, 0) * pos.y + mtx.get(2, 0);
		float y = mtx.get(0, 1) * pos.x + mtx.get(1, 1) * pos.y + mtx.get(2, 1);
		
		return new Vector2f(x, y);
	}

	public void translate(float x, float y) {
		Matrix3f m = new Matrix3f();
		
		m.set(2, 0, x);
		m.set(2, 1, y);
		
		this.multiply(m);
	}
	
	public void translate(Vector2f trans){
		this.translate(trans.x, trans.y);
	}

	public void scale(Vector2f scale) {
		Matrix3f m = new Matrix3f();
		
		m.set(0, 0, scale.x);
		m.set(1, 1, scale.y);
		
		this.multiply(m);
	}

	public void scale(float scale) {
		Matrix3f m = new Matrix3f();
		
		m.set(0, 0, scale);
		m.set(1, 1, scale);
		
		this.multiply(m);
	}
	
	public void rotate(float angle) {
		float cos = (float) Math.cos(Math.toRadians(angle));
		float sin = (float) Math.sin(Math.toRadians(angle));
		Matrix3f m = new Matrix3f(new float[]{
			 cos, sin, 0,
			-sin, cos, 0,
			  0,   0,  1
		});

//		m.set(0, 0,  cos);
//		m.set(0, 1,  sin);
//		m.set(1, 0, -sin);
//		m.set(1, 1,  cos);
//		
		this.multiply(m);
	}

	public void itranslate(Vector2f pos) {
		translate(-pos.x, -pos.y);
	}

	public Matrix3f transposed(){
		return new Matrix3f(new float[]{
			get(0,0), get(0,1), get(0,2), 
			get(1,0), get(1,1), get(1,2), 
			get(2,0), get(2,1), get(2,2)
		});
	}
	
	public String toString() {
		return "Matrix3f : \n\t" + 
				get(0,0) + " " + get(1,0) + " " + get(2,0) + "\n\t" + 
				get(0,1) + " " + get(1,1) + " " + get(2,1) + "\n\t" + 
				get(0,2) + " " + get(1,2) + " " + get(2,2);
	}

	public void clear() {
		for (int i = 0; i < this.data.length; i++)
			this.data[i] = 0;
		
		for (int i = 0; i < 3; i++)
			set(i, i, 1);
	}
}
