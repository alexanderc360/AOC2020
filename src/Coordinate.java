public class Coordinate {
    private int x;
    private int y;
    private char currentDirection;

    public Coordinate(int x, int y, char currentDirection) {
        this.x = x;
        this.y = y;
        this.currentDirection = currentDirection;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.currentDirection = 'E';
    }

    public Coordinate(char currentDirection) {
        x = y = 0;
        this.currentDirection = currentDirection;
    }

    public Coordinate() {
        x = y = 0;
        currentDirection = 'E';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getCurrentDirection() {
        return currentDirection;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCurrentDirection(char currentDirection) {
        this.currentDirection = currentDirection;
    }
}
