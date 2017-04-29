import engine.Application;
import engine.Display;
import engine.Screen;

public class Game extends Application {
	
	private Display display;
	
	private Table table;
	
	public Game() {
		display = new Display(this, 800, 600, 1.2F);
		display.screen.setViweMatrix(-8, 8, 6, -6);
		
		table = new Table();
		
		display.start();
	}

	public void update() {
		table.update(display);
	}

	public void render(Screen screen) {
		table.render(screen);
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
}
