package Animals;

import Animals.Animal;

/**
 * The class Feline in animal hierarchy.
 * @author hyzhang
 *
 */
abstract class Feline extends Animal {
	
	/**
	 * The constructor. Invoking that in the superclass.
	 * @param p The initial position of this animal.
	 */
	Feline(Point p) { super(p); }
	
	/* (non-Javadoc)
	 * @see Animals.Animal#setMove()
	 */
	void setMove() {
		oneMove = new Point[] {
				new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1),
				new Point(1, 1), new Point(-1, 1), new Point(1, -1), new Point(-1, -1)
		};
	}
	
	/* (non-Javadoc)
	 * @see Animals.Animal#attack(Animals.Animal)
	 */
	Point attack(Animal other) {
		if (this instanceof Lion && other instanceof Hippo) {
			return other.die();
		} else if (other instanceof Canine) {
			return other.die();
		} else return this.die();
	}
	
}
