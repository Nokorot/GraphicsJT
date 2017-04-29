import java.awt.Color;

import engine.Application;
import engine.Display;

public class Game extends Application {

	private Display display;
	
	private Puzzle puzzle;
	
	public Game() {
		display = new Display(this, 800, 600, 1.2F);
		display.screen.setViweMatrix(-8, 8, 6, -6);
		display.setBackground(new Color(100, 200, 30));
		
		puzzle = new Puzzle();
		add(puzzle);
		
		display.start();
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
}
