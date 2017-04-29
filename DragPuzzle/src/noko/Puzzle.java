package noko;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import engine.Display;
import engine.Entity;
import engine.Matrix3f;
import engine.Screen;
import engine.Vector2f;
import engine.Vector2i;
import engine.shapes.Line;
import engine.shapes.Rect;

public class Puzzle extends Entity {

	private Color background = new Color(0x2b231b * 2);
	private Color grid = Color.DARK_GRAY;

	Vector2f size;

	int width = 4;
	int height = 4;

	private Vector2i free = new Vector2i(width-1, height-1);

	private Line[] lines;

	private Piece[] pieces;

	public Puzzle() {
		super (new Vector2f());
		
		this.size = new Vector2f(11.5f, 11.5f);

		genGrid();
		genPieces();
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
	
	public void Update(Display display) {
		if (display.mousePressed)
			for (Piece p : pieces)
				if (p.mousePress(display))
					tryToMove(p);
	}
	
	private boolean tryToMove(Piece p){
		if (p == null) return false;
		int dx = free.x - p.x;
		int dy = free.y - p.y;
		if (abs(dx) + abs(dy) < 2) {
			free = new Vector2i(p.x, p.y);
			p.x += dx;
			p.y += dy;

			if (p.x == (p.num - 1) % width && (height - 1 - p.y) == (p.num - 1) / width) {
				p.color = new Color(160, 30, 30);
			} else {
				p.color = new Color(0xd19c57);
			}
			return true;
		}
		return false;
	}

	public void Render(Screen screen) {
		super.Render(screen);
		screen.drawRect(background, pos, size, true);
		for (Line l : lines)
			l.render(screen, grid);
//		for (Piece p : pieces)
//			p.render(screen);
	}

	private void genPieces() {
		List<Integer> l = new ArrayList<>();
		Random rand = new Random();

		Rect pieceS = new Rect(Vector2f.div(size, width).mult(.8f));

		Matrix3f mpos = new Matrix3f();
		mpos.translate(.5f, .5f);
		mpos.scale(size);
		mpos.scale(1f / width);
		mpos.itranslate(pos);
		mpos.itranslate(Vector2f.div(size, 2)); 

		pieces = new Piece[width * height -1];

		for (int i = 0; i < pieces.length; i++)
			l.add(i + 1);

		int j = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (x == free.x && y == free.y)
					continue;
//				if (l.size() == 0)
//					break;
				System.out.println(l.size());
				int i = rand.nextInt(l.size());
				pieces[j] = new Piece(l.get(i), x, y, mpos, pieceS);
				pieces[j].color = new Color(0xd19c57);
				l.remove(i);
				
				add(pieces[j++]);
			}
		}
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
