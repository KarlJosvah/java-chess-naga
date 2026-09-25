package game.chess.builder;

import game.chess.entity.Piece;
import game.chess.layout.LayoutEntry;

import tools.SVGLoader;

public class PieceBuilder {
	
// ======================================================================================================================================================

	public static Piece of(Piece.Type type, Piece.Color color, float tileSize) {
		switch (type) {
			case PAWN:
				return PieceBuilder.ofPawn(color, tileSize);
			case ROOK:
				return PieceBuilder.ofRook(color, tileSize);
			case KNIGHT:
				return PieceBuilder.ofKnight(color, tileSize);
			case BISHOP:
				return PieceBuilder.ofBishop(color, tileSize);
			case QUEEN:
				return PieceBuilder.ofQueen(color, tileSize);
			case KING:
				return PieceBuilder.ofKing(color, tileSize);
			default:
				return PieceBuilder.ofPawn(color, tileSize);
		}
	}

	private static Piece ofPawn(Piece.Color color, float tileSize) {
		Piece pawn = new Piece(Piece.Type.PAWN, color);
		pawn.setSprite(SVGLoader.loadPieceSvg(color == Piece.Color.BLACK ? "pb.svg" : "pw.svg", tileSize, tileSize));
		return pawn;
	}

	private static Piece ofRook(Piece.Color color, float tileSize) {
		Piece rook = new Piece(Piece.Type.ROOK, color);
		rook.setSprite(SVGLoader.loadPieceSvg(color == Piece.Color.BLACK ? "rb.svg" : "rw.svg", tileSize, tileSize));
		return rook;
	}

	private static Piece ofKnight(Piece.Color color, float tileSize) {
		Piece knight = new Piece(Piece.Type.KNIGHT, color);
		knight.setSprite(SVGLoader.loadPieceSvg(color == Piece.Color.BLACK ? "nb.svg" : "nw.svg", tileSize, tileSize));
		return knight;
	}

	private static Piece ofBishop(Piece.Color color, float tileSize) {
		Piece bishop = new Piece(Piece.Type.BISHOP, color);
		bishop.setSprite(SVGLoader.loadPieceSvg(color == Piece.Color.BLACK ? "bb.svg" : "bw.svg", tileSize, tileSize));
		return bishop;
	}

	private static Piece ofQueen(Piece.Color color, float tileSize) {
		Piece queen = new Piece(Piece.Type.QUEEN, color);
		queen.setSprite(SVGLoader.loadPieceSvg(color == Piece.Color.BLACK ? "qb.svg" : "qw.svg", tileSize, tileSize));
		return queen;
	}

	private static Piece ofKing(Piece.Color color, float tileSize) {
		Piece king = new Piece(Piece.Type.KING, color);
		king.setSprite(SVGLoader.loadPieceSvg(color == Piece.Color.BLACK ? "kb.svg" : "kw.svg", tileSize, tileSize));
		return king;
	}

// ======================================================================================================================================================

	public static Piece from(LayoutEntry entry, float tileSize) {
		return PieceBuilder.of(entry.getType(), entry.getColor(), tileSize);
	}
}