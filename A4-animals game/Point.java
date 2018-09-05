package Animals;

/**
 * The class Point as a coordinate (x, y).
 * @author hyzhang
 *
 */
public class Point {
	
	private int x, y;
	
	/**
	 * Create a point by default. (0, 0).
	 */
	public Point() { x = 0; y = 0; }
	
	/**
	 * Create a Point with the coordinate (_x, _y).
	 * @param _x The x-coordinate of a given point.
	 * @param _y The y-coordinate of a given point.
	 */
	public Point(int _x, int _y) { x = _x; y = _y; }
	
	/**
	 * The addition of two points. Actually one of them acts as a vector.
	 * @param p The point to be added.
	 * @return The result of the addition.
	 */
	public Point add(Point p) { return new Point(x + p.x, y + p.y); }
	
	/**
	 * A new point multiplied by an integer.
	 * @param args The multiple.
	 * @return The result of the multiplication.
	 */
	public Point mul(int args) { return new Point(x * args, y * args); }
	
	/**
	 * @return The x-coordinate.
	 */
	public int getX() { return x; }
	
	/**
	 * @return The y-coordinate.
	 */
	public int getY() { return y; }
	
	/**
	 * A new point divided by an integer. Remainder ignored.
	 * @param d The divisor.
	 * @return Result of the point divided.
	 */
	public Point dividedBy(int d) { return new Point(x / d, y / d); }
	
	/**
	 * Judge whether two points are the same.
	 * @param p Another point.
	 * @return The boolean value as a result of comparison.
	 */
	public boolean equal(Point p) { 
		if (this == null || p == null) return false;
		return (x == p.x && y == p.y);
	}
}
