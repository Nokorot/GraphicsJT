import engine.Application;
import engine.Display;

public class Game extends Application {

	private Display display;
	
	public Game(){
		display = new Display(this, 800, 600, 2);
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
}
