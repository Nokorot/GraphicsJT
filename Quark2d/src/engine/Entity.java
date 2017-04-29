package engine;

import java.awt.Color;

public class Entity extends GameObject {

	public Sprite sprite;
	public Vector2f pos; // change to Transform
	
	public Color color;

	protected Entity(){
	}

	public Entity(Sprite sprite, Vector2f pos) {
		this.sprite = sprite;
		this.pos = pos;
	}

	public void Render(Screen screen) {
		if (color != null)
			screen.getGraphics().setColor(color);
		if (sprite != null)
			this.sprite.render(screen, pos);
	}

	public void Update(Display display) {
	}

}
