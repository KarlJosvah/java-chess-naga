package game.chess.movement;

import java.util.Set;
import java.util.HashSet;
import game.chess.entity.Board;
import game.chess.utils.Position;

public class LinearMoveBehavior implements MoveBehavior {
	private final Direction direction;
	private final int maxDistance; // [ 1 ; Integer.MAX_VALUE ]
	private final Orientation orientation;

	public LinearMoveBehavior(Direction direction, int maxDistance) {
		this(direction, maxDistance, Orientation.NORTH);
	}

	public LinearMoveBehavior(Direction direction, int maxDistance, Orientation orientation) {
		this.direction = direction;
		this.maxDistance = LinearMoveBehavior.validateMaxDistance(maxDistance);
		this.orientation = orientation;
	}

	private static int validateMaxDistance(int maxDistance) {
		if (maxDistance < 1) {
			throw new IllegalArgumentException("Linear Move max distance have to be equal or higher than 1.");
		}
		return maxDistance;
	}

	@Override
	public Set<Position> getPossibleMoves(Position currentPosition, Board board) {
		Set<Position> moves = new HashSet<>();
		int currentRow = currentPosition.getRow();
		int currentCol = currentPosition.getCol();

		Direction adjustedDirection = this.orientation.apply(this.direction);

		for (int step = 1; step <= this.maxDistance; step++) {
			int targetRow = currentRow + (adjustedDirection.getDRow() * step);
			int targetCol = currentCol + (adjustedDirection.getDCol() * step);

			Position targetPos = new Position(targetRow, targetCol);
			if (!board.isValidPosition(targetPos)) {
				break;
			}

			if (board.isEmpty(targetPos)) {
				moves.add(targetPos);
			} else {
				if (board.isEnemyPiece(currentPosition, targetPos)) {
					moves.add(targetPos);
				}
				break;
			}
		}

		return moves;
	}
}