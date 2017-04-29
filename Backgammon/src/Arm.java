import java.util.ArrayList;
import java.util.List;

import engine.Display;
import engine.Screen;
import engine.Vector2f;

public class Arm {
	
	private static final float x0 = (float) 110 / 700 * 8;
	private static final float w0 = (float) 96 / 700 * 8;
	private static final float h0 = (float) 432 / 530 * 6;

	public Table table;

	final int x;
	private final float xp;
	private List<Man> _mans = new ArrayList<>();

	public Arm(int x, int i, Table table) {
		this.x = x;

		x = this.x % 12;
		int xa = x <= 5 ? (6 - x) : (5 - x);
		xp = (x0 * sgn(xa) + (xa - sgn(xa)) * w0) * (this.x >= 12 ? -1 : 1);

		for (int k = 0; k < Math.abs(i); k++) {
			Man man = new Man(i > 0 ? table.spriteA : table.spriteB, new Vector2f(), this);
			add(man);
		}
		this.table = table;
	}
	
	public int contains(Vector2f pos){
		if (Math.abs(this.xp - pos.x) >= w0 / 2) return 1;
		if (this.x < 12){
			if (pos.y > h0) return 2;
			if (pos.y < 0) return 3;
		}else {
			if (pos.y < -h0) return 4;
			if (pos.y > 0) return 5;
		}
		return 0;
	}

	public void update(Display display) {
		for (Man man : _mans)
			man.update(display);
	}

	public void render(Screen screen) {
		for (Man man : _mans)
			man.render(screen);
	}

	public void mouseRelesed(Vector2f mouse) {
		for (Man man : _mans)
			if (man.mouseRelesed(mouse))
				break;
	}

	public boolean mousePressed(Vector2f mouse) {
		int s = _mans.size();
		if (s > 0)
			if (_mans.get(s-1).mousePressed(mouse))
					return true;
		return false;
	}

	public void add(Man man) {
		_mans.add(man);
		int ya = _mans.size() - 1;
		float yp = (h0 - (man.rad - .1f) * 2 * ya);

		if (this.x >= 12)
			yp *= -1;

		man.pos = new Vector2f(xp, yp);
	}

	public void remove(Man man) {
		_mans.remove(man);
	}

	private int sgn(int v) {
		return v < 0 ? -1 : v > 0 ? 1 : 0;
	}
}
