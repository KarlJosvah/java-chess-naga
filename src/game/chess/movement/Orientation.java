package game.chess.movement;

public enum Orientation {
	NORTH(0),          // 0 steps (0°)
	NORTH_EAST(1),     // 1 step clockwise (+45°)
	EAST(2),           // 2 steps clockwise (+90°)
	SOUTH_EAST(3),     // 3 steps clockwise (+135°)
	SOUTH(4),          // 4 steps clockwise (+180°)
	SOUTH_WEST(5),     // 5 steps clockwise (+225°)
	WEST(6),           // 6 steps clockwise (+270°)
	NORTH_WEST(7);     // 7 steps clockwise (+315°)

	private static final Direction[] CLOCKWISE_DIRECTIONS = {
		Direction.NORTH,
		Direction.NORTH_EAST,
		Direction.EAST,
		Direction.SOUTH_EAST,
		Direction.SOUTH,
		Direction.SOUTH_WEST,
		Direction.WEST,
		Direction.NORTH_WEST
	};

	private final int steps;

	Orientation(int steps) {
		this.steps = steps;
	}

	public Direction apply(Direction dir) {
		if (dir == null) {
			return null;
		}

		int baseIndex = -1;
		for (int i = 0; i < CLOCKWISE_DIRECTIONS.length; i++) {
			if (CLOCKWISE_DIRECTIONS[i] == dir) {
				baseIndex = i;
				break;
			}
		}

		if (baseIndex == -1) {
			return dir;
		}

		int rotatedIndex = (baseIndex + this.steps) % CLOCKWISE_DIRECTIONS.length;
		return CLOCKWISE_DIRECTIONS[rotatedIndex];
	}
}