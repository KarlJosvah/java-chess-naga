package game.chess.movement;

public enum Direction {
	NORTH			(+1, +0),
	SOUTH			(-1, +0),
	EAST			(+0, +1),
	WEST			(+0, -1),
	NORTH_EAST		(+1, +1),
	NORTH_WEST		(+1, -1),
	SOUTH_EAST		(-1, +1),
	SOUTH_WEST		(-1, -1);

	private final int dRow;
	private final int dCol;

	public static final Direction[] STRAIGHT_DIRS = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
	public static final Direction[] DIAGONAL_DIRS = {Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST};

	Direction(int dRow, int dCol) {
		this.dRow = dRow;
		this.dCol = dCol;
	}

	public int getDRow() {
		return this.dRow;
	}

	public int getDCol() {
		return this.dCol;
	}
}