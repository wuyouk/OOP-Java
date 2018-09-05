
/**
 * Represents a lion
 * @author yklam2
 *
 */
public class Lion extends Feline {
	/**
	 * Create a lion in a forest
	 * @param forest The forest this lion is placed
	 */
	public Lion(Forest forest) {
		super(forest);
	}
	/* (non-Javadoc)
	 * @see Feline#getSymbol()
	 */
	public String getSymbol() {
		return "l";
	}
	
	/* (non-Javadoc)
	 * @see Feline#attack(Animal)
	 */
	public void attack(Animal otherAnimal) {
		if(otherAnimal instanceof Hippo) {
			this.kills(otherAnimal);
		} else {
			super.attack(otherAnimal);
		}
	}
}
