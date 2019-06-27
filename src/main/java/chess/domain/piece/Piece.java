package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.MovingRange;
import chess.domain.board.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private PieceColor color;

    public Piece(PieceColor color) {
        this.color = color;
    }

    public List<Tile> pathOf(Tile current, Tile target, boolean isTargetEmpty) {
        Direction direction = getRange(isTargetEmpty, current)
                .getProperDirection(target.getWidthDiff(current), target.getHeightDiff(current));

        List<Tile> possiblePath = new ArrayList<>();
        Tile nextTile = direction.nextTile(current);
        while (!nextTile.equals(target)) {
            possiblePath.add(nextTile);
            nextTile = direction.nextTile(nextTile);
        }

        return possiblePath;
    }

    public boolean isSameColorWith(Piece piece) {
        return piece.color == color;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isColor(PieceColor color) {
        return color == this.color;
    }

    public PieceColor getColor() {
        return color;
    }

    public abstract double getScore();

    public abstract String getType();

    protected abstract MovingRange getRange(boolean haveTarget, Tile current);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

}
