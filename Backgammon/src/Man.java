import engine.Display;
import engine.Entity;
import engine.Sprite;
import engine.Vector2f;

public class Man extends Entity {

	private static Sprite[] pieces = new Sprite[6];
	static {
		for (int i = 0; i < pieces.length; i++) {
			pieces[i] = Sprite.load("piece%.png".replace("%", "" + (i + 1)));
			pieces[i].setSize(1.5f, 1.5f);
		}
	}

	public static float rad = 0.5f;
	private boolean held;

	private Vector2f oldPos;
	
	private Arm parrent;

	public Man(int sprite, Vector2f pos, Arm parrent) {
		super(pieces[sprite], pos);
		this.sprite.setSize(2 * rad, 2 * rad);
		this.parrent = parrent;
	}

	public boolean mousePressed(Vector2f pos) {
		if (Vector2f.sub(this.pos, pos).length() < rad){
			oldPos = new Vector2f(pos);
			held = true;
		}
		return held;
	}

	public boolean mouseRelesed(Vector2f pos) {
		if (!held)return false;
		held = false;
		for (Arm arm : parrent.table.arms) {
			if (arm.contains(pos) == 0) {
				this.parrent.remove(this);
				arm.add(this);
				this.parrent = arm;
				return true;
			}
		}
		return false;
	}

	public void update(Display display) {
		if (held)
			this.pos = display.mouse;
	}

}
