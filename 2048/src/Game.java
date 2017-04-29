import java.awt.Color;

import engine.Application;
import engine.Display;

public class Game extends Application {

	private Display display;
	
<<<<<<< HEAD
	public Game(){
		display = new Display(this, 800, 600, 2);
=======
	private Puzzle puzzle;
	
	public Game() {
		display = new Display(this, 800, 600, 1.2F);
		display.screen.setViweMatrix(-8, 8, 6, -6);
		display.setBackground(new Color(100, 200, 30));
		
		puzzle = new Puzzle();
		add(puzzle);
		
		display.start();
>>>>>>> 5efc026c92c301813d0cb41eb1db45d6a8ee5296
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
}
