import java.util.Random;

public class Table {
	
	int width;
	int height;
	
	private Piece[][] pieces;
	
	public static Integer LEFT = new Integer(0);
	public static Integer UP = new Integer(1);
	public static Integer RIGHT = new Integer(2);
	public static Integer DOWN = new Integer(3);
	public static Integer[] DIRECTIONS = {LEFT, UP, RIGHT, DOWN};
	
	public Table(int width, int height) {
		this.width = width;
		this.height = height;
		this.pieces = new Piece[width][height];
	}
	
	public void newPiece(int num, int x, int y) {
		pieces[y][x] = new Piece(num, x, y);
	}
	
	public void new2048Piece() {
		Random random = new Random();
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		int num = (random.nextDouble() < 0.8) ? 2 : 4;
		if (!isEmptyOn(x, y) ) {
			newPiece(num, x, y);
		}
	}
	
	public void movePieces(int direction) {
		if (!(0<=direction && direction<=3)) {
			return;
		}
		Piece[][] newPieces = new Piece[width][height];
		if (LEFT.equals(direction) ) {
			for (int y = 0; y < height; y++) {
				int newX = 0;
				for (int x = 0; x < width; x++) {
					Piece piece = pieces[y][x];
					if (piece != null) {
						newPieces[y][newX++] = piece;
					}
				}
			}
		}
		else if (UP.equals(direction) ) {
			for (int x = 0; x < height; x++) {
				int newY = 0;
				for (int y = 0; y < width; y++) {
					Piece piece = pieces[y][x];
					if (piece != null) {
						newPieces[newY++][x] = piece;
					}
				}
			}
		}
		else if (RIGHT.equals(direction) ) {
			for (int y = 0; y < height; y++) {
				int newX = width-1;
				for (int x = 0; x < width; x++) {
					Piece piece = pieces[y][x];
					if (piece != null) {
						newPieces[y][newX--] = piece;
					}
				}
			}
		}
		else if (DOWN.equals(direction) ) {
			for (int x = 0; x < height; x++) {
				int newY = height-1;
				for (int y = 0; y < width; y++) {
					Piece piece = pieces[y][x];
					if (piece != null) {
						newPieces[newY--][x] = piece;
					}
				}
			}
		}
		pieces = newPieces;
	}
	
	public Piece getPiece(int x, int y) {
		return pieces[y][x];
	}
	
	public boolean isEmptyOn(int x, int y) {
		return getPiece(x, y) == null;
	}
}
