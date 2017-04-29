package noko;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import engine.Display;
import engine.Entity;
import engine.Screen;
import engine.Vector2f;
import engine.Vector2i;
import engine.shapes.Line;

public class Puzzle extends Entity {

	private Color background = new Color(0x2b231b * 2);
	private Color gridColor = new Color(0x130038 * 2);

	private Color[] piecColors = {background, Color.RED, Color.BLUE};
	
	private int[] grid;
	
	Vector2f size;

	int width = 13;
	int height = 8;

	private Vector2i free = new Vector2i(width-1, height-1);

	private Line[] lines;

	private Piece[] pieces;

	public Puzzle() {
		this.pos = new Vector2f();
		this.size = new Vector2f(15.5f, 11.5f);
		
		grid = new int[width * height];
		for (int i = 0; i < width * height ; i++)
			grid[i] = 0;
	}

	private int current = 1;
	
	private boolean addPiece(int key) {
		for (int i = 0; i < height; i++)
			if (grid[key + i * width] == 0){
				grid[key + i * width] = current;
				current = (current)%2+1;
				return true;
			}
		return false;
				
	}
	
	public void onKeypress(KeyEvent e) {
		try {
			int key = Integer.parseInt(""+e.getKeyChar());
			
			if (key < width){
				addPiece(key);
			}
			
		}catch (NumberFormatException ex) {
		}
	}
	
	public boolean onMousepress(MouseEvent e, Vector2f pos) {
		System.out.println(pos);
		int i = (int) ((pos.x - this.pos.x + this.size.x / 2) / size.x * width);
		
		System.out.println(i);
		
		addPiece(i);
		
		return true;
	}
	
	public void Update(Display display) {
	}
	
	public void Render(Screen screen) {
		super.Render(screen);
		screen.drawRect(gridColor, this.pos, this.size, true);

		float xw = this.size.x / width;
		float yw = this.size.y / height;
		
		for(int i = 0; i < width * height; i++){
			Color c = piecColors[grid[(i%width) + i/width*width]];
			screen.drawOval(c, Vector2f.sub(this.pos, Vector2f.div(this.size, 2)).add(new Vector2f(xw * (i % width+.5f), yw * (i / width+.5f))), new Vector2f(xw, yw).mult(.9f));
		}
		
	}

}
