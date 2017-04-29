package engine;

import java.awt.Color;

import engine.shapes.Shape;

public class Entity extends GameObject {

	public Vector2f pos; // change to Transform
	public Transform transform = new Transform();
	
	private Shape shape;
	public Color color;
	
	public Sprite sprite;

	public Entity(){
		this.pos = new Vector2f();
	}
	
	public Entity(Vector2f pos) {
		this.pos = pos;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public void Render(Screen screen) {
		if (sprite != null)
			this.sprite.render(screen, pos);
		else if (color != null){
			shape.render(screen, color, transform);
		}
	}

	public void Update(Display display) {
	}

}
