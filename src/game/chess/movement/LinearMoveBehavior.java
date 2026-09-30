package game.chess.movement;

import java.util.Set;
import java.util.HashSet;
import game.chess.entity.Board;
import game.chess.utils.Position;

public class LinearMoveBehavior implements MoveBehavior {

// ======================================================================================================================================================

	private final Direction direction;
	private final int maxDistance; // [ 1 ; Integer.MAX_VALUE ]
	private final Orientation orientation;
	private final boolean hasToBeEmpty;
	private final boolean hasToBeEnemy;

// ======================================================================================================================================================

	private LinearMoveBehavior(Builder builder) {
		this.direction = builder.direction;
		this.maxDistance = builder.maxDistance;
		this.orientation = builder.orientation;
		this.hasToBeEmpty = builder.hasToBeEmpty;
		this.hasToBeEnemy = builder.hasToBeEnemy;
	}

// ======================================================================================================================================================

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
				if (!this.hasToBeEnemy) {
					moves.add(targetPos);
				}
			} else {
				if (!this.hasToBeEmpty && board.isEnemyPiece(currentPosition, targetPos)) {
					moves.add(targetPos);
				}
				break;
			}
		}

		return moves;
	}

// ======================================================================================================================================================

	public static class Builder {
		private final Direction direction;
		private final int maxDistance;
		private Orientation orientation = Orientation.NORTH;
		private boolean hasToBeEmpty = false;
		private boolean hasToBeEnemy = false;

		public Builder(Direction direction, int maxDistance) {
			this.direction = direction;
			this.maxDistance = Builder.validateMaxDistance(maxDistance);
		}

		private static int validateMaxDistance(int maxDistance) {
			if (maxDistance < 1) {
				throw new IllegalArgumentException("Linear Move max distance have to be equal or higher than 1.");
			}
			return maxDistance;
		}

		public Builder orientation(Orientation orientation) {
			this.orientation = orientation;
			return this;
		}

		public Builder hasToBeEmpty(boolean hasToBeEmpty) {
			this.hasToBeEmpty = hasToBeEmpty;
			if (this.hasToBeEmpty) {
				this.hasToBeEnemy = false;
			}
			return this;
		}

		public Builder hasToBeEnemy(boolean hasToBeEnemy) {
			this.hasToBeEnemy = hasToBeEnemy;
			if (this.hasToBeEnemy) {
				this.hasToBeEmpty = false;
			}
			return this;
		}

		public LinearMoveBehavior build() {
			return new LinearMoveBehavior(this);
		}
	}
}