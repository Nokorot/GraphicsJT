import engine.Display;
import engine.Entity;
import engine.Screen;
import engine.Sprite;
import engine.Vector2f;

public class Table extends Entity {

	private static final int[] setup = { 2, 0, 0, 0, 0, -5, 0, -3, 0, 0, 0, 5, -5, 0, 0, 0, 3, 0, 5, 0, 0, 0, 0, -2 };
	public Arm[] arms = new Arm[setup.length];

	public int spriteA = 3, spriteB = 2;

	public Table() {
		super(Sprite.load("bord.png"), new Vector2f());
		sprite.size = new Vector2f(16, 12);

		for (int x = 0; x < setup.length; x++) {
			arms[x] = new Arm(x, setup[x], this);
		}
	}

	public void render(Screen screen) {
		super.render(screen);
		for (Arm arm : arms)
			arm.render(screen);
	}

	public void update(Display display) {
		if (display.mousePressed)
			for (Arm arm : arms)
				if (arm.mousePressed(display.mouse))
					break;
		if (display.mouseRelaesed)
			for (Arm arm : arms)
				arm.mouseRelesed(display.mouse);
		for (Arm arm : arms)
			arm.update(display);
	}

}
