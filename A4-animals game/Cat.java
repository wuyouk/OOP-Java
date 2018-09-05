package Animals;

/**
 * The class Cat in animal hierarchy.
 * @author hyzhang
 *
 */
public class Cat extends Feline {
	
	/**
	 * The constructor. Invoking that in the superclass.
	 * @param p The initial position of this animal.
	 */
	public Cat(Point p) { super(p); }
	
	/* (non-Javadoc)
	 * @see Animals.Animal#getName()
	 */
	String getName() { return "Cat"; }

}
