package game.chess.movement;

import java.util.Set;
import java.util.HashSet;
import game.chess.entity.Board;
import game.chess.utils.Position;

public class LeapMoveBehavior implements MoveBehavior {
	private final int dRow;
	private final int dCol;

	public LeapMoveBehavior(int dRow, int dCol) {
		this.dRow = dRow;
		this.dCol = dCol;
	}

	@Override
	public Set<Position> getPossibleMoves(Position currentPosition, Board board) {
		Set<Position> moves = new HashSet<>();
		Position targetPos = new Position(
			currentPosition.getRow() + this.dRow,
			currentPosition.getCol() + this.dCol
		);

		if (board.isValidPosition(targetPos)) {
			if (board.isEmpty(targetPos) || board.isEnemyPiece(currentPosition, targetPos)) {
				moves.add(targetPos);
			}
		}

		return moves;
	}
}