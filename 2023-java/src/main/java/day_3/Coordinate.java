package day_3;

public record Coordinate(int y, int x) {
    Coordinate moveLeft() {
        return new Coordinate(y, x - 1);
    }

    Coordinate moveRight() {
        return new Coordinate(y, x + 1);
    }

    enum CoordinateDirection {
        LEFT, RIGHT
    }
}
