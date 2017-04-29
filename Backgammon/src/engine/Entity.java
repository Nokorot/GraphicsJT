package engine;

public class Entity {

	public Sprite sprite;
	public Vector2f pos;

	protected Entity(){
	}

	public Entity(Sprite sprite, Vector2f pos) {
		this.sprite = sprite;
		this.pos = pos;
	}

	public void render(Screen screen) {
		if (sprite != null)
			this.sprite.render(screen, pos);
	}

	public void update(Display display) {
	}
}
