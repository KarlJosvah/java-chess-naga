package game.chess.movement;

public enum Direction {
	NORTH(0, 1),
	SOUTH(0, -1),
	EAST(1, 0),
	WEST(-1, 0),
	NORTH_EAST(1, 1),
	NORTH_WEST(-1, 1),
	SOUTH_EAST(1, -1),
	SOUTH_WEST(-1, -1);

	private final int dx;
	private final int dy;

	public static final Direction[] STRAIGHT_DIRS = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
	public static final Direction[] DIAGONAL_DIRS = {Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST};

	Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public int getDx() {
		return this.dx;
	}

	public int getDy() {
		return this.dy;
	}
}