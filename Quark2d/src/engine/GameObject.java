package engine;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public abstract class GameObject {
	
	private List<GameObject> childs = new ArrayList<>();

	public void add(GameObject object) {
		childs.add(object);
	}

	boolean mousepress(MouseEvent e, Vector2f pos){
		for (GameObject child : childs)
			if (child.mousepress(e, pos))
				return true;
		return onMousepress(e, pos);
	}
	
	void keypresed(KeyEvent e){
		onKeypress(e);
		onKeypress(e.getKeyCode());
		for (GameObject child : childs)
			child.keypresed(e);
	}
	
	void keyreleased(KeyEvent e){
		onKeyreleas(e);
		onKeyreleas(e.getKeyCode());
		for (GameObject child : childs)
			child.keyreleased(e);
	}
	
	void update(Display display){
		Update(display);
		for (GameObject child : childs)
			child.update(display);
	}
	
	void render(Screen screen){
		Render(screen);
		for (GameObject child : childs)
			child.render(screen);
	}
	
	public void Update(Display display) {}
	public void Render(Screen screen) { }

	public void onKeypress(KeyEvent e){};
	public void onKeyreleas(KeyEvent e){};
	public void onKeypress(int key){};
	public void onKeyreleas(int key){};

	public boolean onMousepress(MouseEvent e, Vector2f pos){ return false; };
	public boolean onMousereleas(MouseEvent e, Vector2f pos){ return false; };
	
}
