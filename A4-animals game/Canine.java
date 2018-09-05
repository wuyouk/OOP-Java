package Animals;

import java.util.Random;

import Animals.Animal;

/**
 * The class Canine in animal hierarchy.
 * @author hyzhang
 *
 */
abstract class Canine extends Animal {
	
	/**
	 * The constructor. Invoking that in the superclass.
	 * @param p The initial position of this animal.
	 */
	Canine(Point p) { super(p); }
	
	/* (non-Javadoc)
	 * @see Animals.Animal#setMove()
	 */
	void setMove() {
		oneMove = new Point[] {
				new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1),
				new Point(2, 0), new Point(-2, 0), new Point(0, 2), new Point(0, -2)
		};
	}
	
	/* (non-Javadoc)
	 * @see Animals.Animal#attack(Animals.Animal)
	 */
	Point attack(Animal other) {
		if (this instanceof Fox && other instanceof Cat) {
			return other.die();
		} else if (other instanceof Feline) {
			if (new Random().nextBoolean()) return other.die();
			else return this.die();
		} else return this.die();
	}
	
}