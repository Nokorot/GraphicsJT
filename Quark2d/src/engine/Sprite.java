package engine;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Sprite {

	private final Image image;
	private final int width, height;
	public Vector2f size; 

	public Sprite(Image image) {
		this.image = image;
		width = image.getWidth(null);
		height = image.getHeight(null);
	}

	public void setSize(float x, float y) {
		if (size == null)
			size = new Vector2f(x, y);
		else
			size.set(x, y);
	}

	public void render(Screen screen, Vector2f pos, Vector2f pos2) {
		Vector2i p = screen.asPixelCord(pos);
		Vector2i s = screen.asPixelCord(pos2);

		screen.getGraphics().drawImage(image, p.x, p.y, s.x, s.y, null);
	}

	public void render(Screen screen, Transform transform) {
		Vector2i p = screen.asPixelCord(transform.getPosition());
		if (size != null) {
			Vector2i s = screen.asPixelSize(size.mult(transform.getScale()));
			screen.getGraphics().drawImage(image, p.x-s.x/2, p.y-s.y/2, s.x, s.y, null);
		} else
			screen.getGraphics().drawImage(image, p.x-width/2, p.y-height/2, null);
	}
	
	public static Sprite load(String file) {
		BufferedImage image = null;
		try {
			URL url = Sprite.class.getResource("../" + file);
			if (url == null){
				System.err.printf("File %s was not found. \n", file);
				return null;
			}
			file = Sprite.class.getResource("../" + file).getFile();
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Sprite(image);
	}


}
