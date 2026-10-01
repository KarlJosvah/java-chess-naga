package game.chess.factory;

import java.awt.image.BufferedImage;
import game.chess.entity.Piece;
import game.chess.config.Config;
import game.chess.layout.LayoutEntry;
import game.chess.factory.MoveFactory;

import tools.SVGLoader;

public class PieceFactory {
	
// ======================================================================================================================================================

	public static Piece of(Piece.Type type, Piece.Color color, float tileSize) {
		switch (type) {
			case PAWN:
				return PieceFactory.ofPawn(color, tileSize);
			case ROOK:
				return PieceFactory.ofRook(color, tileSize);
			case KNIGHT:
				return PieceFactory.ofKnight(color, tileSize);
			case BISHOP:
				return PieceFactory.ofBishop(color, tileSize);
			case QUEEN:
				return PieceFactory.ofQueen(color, tileSize);
			case KING:
				return PieceFactory.ofKing(color, tileSize);
			default:
				return PieceFactory.ofPawn(color, tileSize);
		}
	}

	private static Piece ofPawn(Piece.Color color, float tileSize) {
		Piece pawn = new Piece(Piece.Type.PAWN, color);
		pawn.setSprite(PieceFactory.loadSprite(color == Piece.Color.BLACK ? "pb.svg" : "pw.svg", color, tileSize));
		pawn.addAllBehaviors(MoveFactory.of(Piece.Type.PAWN, color));
		return pawn;
	}

	private static Piece ofRook(Piece.Color color, float tileSize) {
		Piece rook = new Piece(Piece.Type.ROOK, color);
		rook.setSprite(PieceFactory.loadSprite(color == Piece.Color.BLACK ? "rb.svg" : "rw.svg", color, tileSize));
		rook.addAllBehaviors(MoveFactory.of(Piece.Type.ROOK));
		return rook;
	}

	private static Piece ofKnight(Piece.Color color, float tileSize) {
		Piece knight = new Piece(Piece.Type.KNIGHT, color);
		knight.setSprite(PieceFactory.loadSprite(color == Piece.Color.BLACK ? "nb.svg" : "nw.svg", color, tileSize));
		knight.addAllBehaviors(MoveFactory.of(Piece.Type.KNIGHT));
		return knight;
	}

	private static Piece ofBishop(Piece.Color color, float tileSize) {
		Piece bishop = new Piece(Piece.Type.BISHOP, color);
		bishop.setSprite(PieceFactory.loadSprite(color == Piece.Color.BLACK ? "bb.svg" : "bw.svg", color, tileSize));
		bishop.addAllBehaviors(MoveFactory.of(Piece.Type.BISHOP));
		return bishop;
	}

	private static Piece ofQueen(Piece.Color color, float tileSize) {
		Piece queen = new Piece(Piece.Type.QUEEN, color);
		queen.setSprite(PieceFactory.loadSprite(color == Piece.Color.BLACK ? "qb.svg" : "qw.svg", color, tileSize));
		queen.addAllBehaviors(MoveFactory.of(Piece.Type.QUEEN));
		return queen;
	}

	private static Piece ofKing(Piece.Color color, float tileSize) {
		Piece king = new Piece(Piece.Type.KING, color);
		king.setSprite(PieceFactory.loadSprite(color == Piece.Color.BLACK ? "kb.svg" : "kw.svg", color, tileSize));
		king.addAllBehaviors(MoveFactory.of(Piece.Type.KING));
		return king;
	}

// ======================================================================================================================================================

	public static Piece from(LayoutEntry entry, float tileSize) {
		return PieceFactory.of(entry.getType(), entry.getColor(), tileSize);
	}

// ======================================================================================================================================================

	private static BufferedImage loadSprite(String svgFileName, Piece.Color color, float tileSize) {
		boolean withStroke = (color == Piece.Color.WHITE) && (Config.get().getWhitePieceBorder() == Config.WhitePieceBorder.SVG_DOM_STROKE);
		return SVGLoader.loadPieceSvg(svgFileName, tileSize, tileSize, withStroke);
	}
}