/**
 * Represents a cat
 * @author yklam2
 *
 */
public class Cat extends Feline {
	/**
	 * Create a cat in a forest
	 * @param forest The forest this feline is placed
	 */
	public Cat(Forest forest) {
		super(forest);
	}

	/* (non-Javadoc)
	 * @see Feline#getSymbol()
	 */
	public String getSymbol() {
		return "c";
	}
	
}
