package engine;

import java.awt.Color;

import engine.shapes.Shape;

public class Entity extends GameObject {

	public Transform transform = new Transform();
	
	protected Shape shape;
	public Color color;
	
	public Sprite sprite;

	public Entity(){
	}

	public Entity(Vector2f pos) {
		transform.setPos(pos);
	}
	
	public Entity(Transform transform) {
		this.transform = transform;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public void Render(Screen screen) {
		if (sprite != null)
			this.sprite.render(screen, transform);
		else if (color != null){
			shape.render(screen, color, transform);
		}
	}

	public void Update(Display display) {
	}

	public void setTransform(Transform transform) {
		this.transform = new Transform(transform);
	}
	
	public Vector2f getPosition() {
		return transform.getPosition();
	}
	
}
