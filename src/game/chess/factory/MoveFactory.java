package game.chess.factory;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import game.chess.entity.Piece;
import game.chess.movement.Direction;
import game.chess.movement.MoveBehavior;
import game.chess.movement.LeapMoveBehavior;
import game.chess.movement.LinearMoveBehavior;
import game.chess.movement.Orientation;

public class MoveFactory {

// ======================================================================================================================================================

	public static List<MoveBehavior> of(Piece.Type type) {
		return MoveFactory.of(type, Piece.Color.WHITE);
	}

	public static List<MoveBehavior> of(Piece.Type type, Piece.Color color) {
		switch (type) {
			case PAWN:
				return MoveFactory.ofPawn(color);
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
				return MoveFactory.ofPawn(color);
		}
	}

	private static List<MoveBehavior> ofPawn(Piece.Color color) {
		Set<MoveBehavior> behaviors = new HashSet<>();

		Orientation orientation = color == Piece.Color.WHITE
			? Orientation.NORTH
			: Orientation.SOUTH;
		behaviors.add(
			new LinearMoveBehavior.Builder(Direction.NORTH, 1)
				.orientation(orientation)
				.hasToBeEmpty(true)
				.build()
		);

		return List.copyOf(behaviors);
	}

	private static List<MoveBehavior> ofRook() {
		Set<MoveBehavior> behaviors = new HashSet<>();

		for (Direction dir : Direction.STRAIGHT_DIRS) {
			behaviors.add(new LinearMoveBehavior.Builder(dir, Integer.MAX_VALUE).build());
		}

		return List.copyOf(behaviors);
	}

	private static List<MoveBehavior> ofKnight() {
		Set<MoveBehavior> behaviors = new HashSet<>();

		int[][] offsets = {
			{1, 2}, {1, -2}, {-1, 2}, {-1, -2},
			{2, 1}, {2, -1}, {-2, 1}, {-2, -1}
		};
		for (int[] offset : offsets) {
			behaviors.add(new LeapMoveBehavior(offset[0], offset[1]));
		}

		return List.copyOf(behaviors);
	}

	private static List<MoveBehavior> ofBishop() {
		Set<MoveBehavior> behaviors = new HashSet<>();

		for (Direction dir : Direction.DIAGONAL_DIRS) {
			behaviors.add(new LinearMoveBehavior.Builder(dir, Integer.MAX_VALUE).build());
		}

		return List.copyOf(behaviors);
	}

	private static List<MoveBehavior> ofQueen() {
		Set<MoveBehavior> behaviors = new HashSet<>();

		behaviors.addAll(MoveFactory.ofRook());
		behaviors.addAll(MoveFactory.ofBishop());

		return List.copyOf(behaviors);
	}

	private static List<MoveBehavior> ofKing() {
		Set<MoveBehavior> behaviors = new HashSet<>();

		for (Direction dir : Direction.STRAIGHT_DIRS) {
			behaviors.add(new LinearMoveBehavior.Builder(dir, 1).build());
		}

		for (Direction dir : Direction.DIAGONAL_DIRS) {
			behaviors.add(new LinearMoveBehavior.Builder(dir, 1).build());
		}

		return List.copyOf(behaviors);
	}
}