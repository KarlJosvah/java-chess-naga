package game.chess.movement;

import java.util.Set;
import game.chess.entity.Board;
import game.chess.utils.Position;

public interface MoveBehavior {
	Set<Position> getPossibleMoves(Position currentPosition, Board board);
}
