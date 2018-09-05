package Animals;

/**
 * The class Dog in animal hierarchy.
 * @author hyzhang
 *
 */
public class Dog extends Canine {
	
	/**
	 * The constructor. Invoking that in the superclass.
	 * @param p The initial position of this animal.
	 */
	public Dog(Point p) { super(p); }
	
	/* (non-Javadoc)
	 * @see Animals.Animal#getName()
	 */
	String getName() { return "Dog"; }
	
}
