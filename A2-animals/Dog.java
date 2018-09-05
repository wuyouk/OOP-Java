
/**
 * Represents a dog
 * @author yklam2
 *
 */
public class Dog extends Canine {
	/**
	 * Create a dog in a forest
	 * @param forest The forest this feline is placed
	 */
	public Dog(Forest forest) {
		super(forest);
	}
	/* (non-Javadoc)
	 * @see Canine#getSymbol()
	 */
	public String getSymbol() {
		return "d";
	}
}
