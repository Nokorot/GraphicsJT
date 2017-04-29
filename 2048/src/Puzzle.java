import java.awt.Color;

import engine.Entity;
import engine.Screen;
import engine.Vector2f;
import engine.shapes.Line;

public class Puzzle extends Entity {

	private Color background = new Color(100, 200, 30);
	private Color grid = Color.DARK_GRAY;

	Vector2f size;

	int width = 4;
	int height = 4;


	private Line[] lines;

	private Piece[] pieces;

	public Puzzle() {
		this.pos = new Vector2f();
		this.size = new Vector2f(11.5f, 11.5f);

		genGrid();
		//genPieces();
	}

	private int abs(int x) {
		return x < 0 ? -x : x;
	}
	
	public Piece getPiece(int x, int y) {
		for (Piece p : pieces)
			if (p.x == x && p.y == y)
				return p;
		return null;
	}
/*
	public void onKeypress(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			tryToMove(getPiece(free.x, free.y + 1));
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			tryToMove(getPiece(free.x, free.y - 1));
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			tryToMove(getPiece(free.x - 1, free.y));
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			tryToMove(getPiece(free.x + 1, free.y));
			break;
		}
	}
	
*/


	public void Render(Screen screen) {
		super.Render(screen);
		screen.drawRect(background, pos, size, true);
		for (Line l : lines)
			l.render(screen, grid);
//		for (Piece p : pieces)
//			p.render(screen);
	}

	private void genGrid() {
		lines = new Line[width + height + 2];

		int i = 0;

		float sx2 = this.size.x / 2 + .1f;
		float sy2 = this.size.y / 2 + .1f;

		float dx = (size.x) / width;
		for (int x = -width / 2; x < width / 2 + 1; x++)
			lines[i++] = new Line(new Vector2f(x * dx, -sy2), new Vector2f(x * dx, sy2), .2f);
		float dy = (size.y) / height;
		for (int y = -height / 2; y < height / 2 + 1; y++)
			lines[i++] = new Line(new Vector2f(-sx2, y * dy), new Vector2f(sx2, y * dy), .2f);
	}

}
