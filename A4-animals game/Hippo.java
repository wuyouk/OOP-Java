package Animals;

import Animals.Animal;

/**
 * The class Hippo in animal hierarchy.
 * @author hyzhang
 *
 */
public class Hippo extends Animal {
	
	/**
	 * The constructor. Invoking that in the superclass.
	 * @param p The initial position of this animal.
	 */
	public Hippo(Point p) { super(p); } 
	
	/* (non-Javadoc)
	 * @see Animals.Animal#setMove()
	 */
	void setMove() {
		oneMove = new Point[] {
				new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1)
		};
	}
	
	/* (non-Javadoc)
	 * @see Animals.Animal#getName()
	 */
	String getName() { return "Hippo"; }
	
	/* (non-Javadoc)
	 * @see Animals.Animal#attack(Animals.Animal)
	 */
	Point attack(Animal other) {
		return this.die();
	}
	
}