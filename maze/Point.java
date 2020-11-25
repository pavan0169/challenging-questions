package maze;

public class Point {
    int x;
    int y;
    Point parent;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
    }

    public Point(int x, int y, Point parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Point getParent() {
        return parent;
    }

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}