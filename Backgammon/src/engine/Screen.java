package engine;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Screen {
	
	public final int width, height;
	private final double asp;
	
	private Matrix3f ViewMatrix, invViewMatrix;
	private Vector2f viewSize;
	
	private BufferedImage img;
	
	private Image background;
	
	private Graphics g;
	
	public Screen(int width, int height, double scale) {
		this.width = (int) (width * scale);
		this.height = (int) (height * scale);
		this.asp = (double) width / height;
		
		img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		setBackground(Color.BLACK);
		
		setViweMatrix(-1, 1, -1, 1);
	}
	
	public void setViweMatrix(float left, float right, float top, float buttom) {
		ViewMatrix = Matrix3f.Identity();
		ViewMatrix.set(0, 0, width / (right- left));
		ViewMatrix.set(1, 1, height / (buttom - top));
		ViewMatrix.set(2, 0, width / 2);
		ViewMatrix.set(2, 1, height / 2);
//		invViewMatrix = ViewMatrix.inverse()
		invViewMatrix = Matrix3f.Identity();
		invViewMatrix.set(0, 0, (right - left) / width);
		invViewMatrix.set(1, 1, (buttom - top) / height);
		invViewMatrix.set(2, 0, left);
		invViewMatrix.set(2, 1, top);
		
		viewSize = new Vector2f(right - left, buttom - top);
	}

	public void setBackground(Color color){
		BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		image.setRGB(0, 0, 1, 1, new int[]{color.getRGB()}, 0, 0);
		setBackground(image);
	}
	
	public void setBackground(Image image) {
		background = image;
	}
	
	public Graphics getGraphics() {
		return g;
	}

	public Vector2i getSize() {
		return new Vector2i(width, height);
	}
	
	public void prepare() {
		g = img.getGraphics();
		g.drawImage(background, 0, 0, width, height, null);
	}

	public Rectangle render(Graphics g, Rectangle bounds) {
		double aspT = bounds.getWidth() / bounds.getHeight();
		
		int w, h;
		if (aspT > asp){
			h = bounds.height;
			w = (int) (h * asp);
		}else{
			w = bounds.width;
			h = (int) (w / asp);
		}
		int x = aspT > asp ? (bounds.width - w) / 2 : 0;
		int y = aspT > asp ? 0 : (bounds.height - h) / 2;
		
		g.drawImage(img, x, y, w, h, null);
		return new Rectangle(x, y, w, h);
	}
	
	public void disose(){
		g.dispose();
	}

	public Vector2i asPixelCord(Vector2f pos) {
		return Matrix3f.multiply(ViewMatrix, pos).floor();
//		
//		int x = (int) ((pos.x + 1) * width / 2);
//		int y = (int) ((pos.y + 1) * height / 2);
//		
//		return new Vector2i(x, y);
	}

	public Vector2f fromPixelCord(int x, int y) {
		return Matrix3f.multiply(invViewMatrix, new Vector2f(x, y));
		
//		float fx = (float) x / width * 2 - 1;
//		float fy = (float) y / height * 2 - 1;
		
//		return new Vector2f(fx, fy);
	}

	public Vector2i asPixelSize(Vector2f size) {
		return Vector2f.div(size, viewSize).mult(getSize().asFloat()).floor();
	}

	public void drawRect(Color c, Vector2f pos, Vector2f size, boolean centerd) {
		Vector2i p = asPixelCord(pos);
		Vector2i s = asPixelSize(size).abs();
		
		if (centerd) p.sub(Vector2i.div(s, 2));
		
		g.setColor(c);
		g.fillRect(p.x, p.y, s.x, s.y);
	}
	
}
