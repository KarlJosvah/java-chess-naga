package game.chess.factory;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import game.chess.entity.Piece;
import game.chess.movement.Direction;
import game.chess.movement.MoveBehavior;
import game.chess.movement.LeapMoveBehavior;
import game.chess.movement.LinearMoveBehavior;

public class MoveFactory {

// ======================================================================================================================================================

	public static List<MoveBehavior> of(Piece.Type type) {
		switch (type) {
			case PAWN:
				return MoveFactory.ofPawn();
			case ROOK:
				return MoveFactory.ofRook();
			case KNIGHT:
				return MoveFactory.ofKnight();
			case BISHOP:
				return MoveFactory.ofBishop();
			case QUEEN:
				return MoveFactory.ofQueen();
			case KING:
				return MoveFactory.ofKing();
			default:
				return MoveFactory.ofPawn();
		}
	}

	private static List<MoveBehavior> ofPawn() {
		Set<MoveBehavior> behaviors = new HashSet<>();
		return List.copyOf(behaviors);
	}

	private static List<MoveBehavior> ofRook() {
		Set<MoveBehavior> behaviors = new HashSet<>();

		for (Direction dir : Direction.STRAIGHT_DIRS) {
			behaviors.add(new LinearMoveBehavior(dir, Integer.MAX_VALUE));
		}

		return List.copyOf(behaviors);
	}

	private static List<MoveBehavior> ofKnight() {
		Set<MoveBehavior> behaviors = new HashSet<>();
		return List.copyOf(behaviors);
	}

	private static List<MoveBehavior> ofBishop() {
		Set<MoveBehavior> behaviors = new HashSet<>();
		return List.copyOf(behaviors);
	}

	private static List<MoveBehavior> ofQueen() {
		Set<MoveBehavior> behaviors = new HashSet<>();
		return List.copyOf(behaviors);
	}

	private static List<MoveBehavior> ofKing() {
		Set<MoveBehavior> behaviors = new HashSet<>();
		return List.copyOf(behaviors);
	}
}