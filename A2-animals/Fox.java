
/**
 * Represents a fox
 * @author yklam2
 *
 */
public class Fox extends Canine {
	/**
	 * Create a fox in a forest
	 * @param fox The forest this fox is placed
	 */
	public Fox(Forest forest) {
		super(forest);
	}
	/* (non-Javadoc)
	 * @see Canine#getSymbol()
	 */
	public String getSymbol() {
		return "f";
	}
	
	/* (non-Javadoc)
	 * @see Canine#attack(Animal)
	 */
	public void attack(Animal otherAnimal) {
		if(otherAnimal instanceof Cat) {
			this.kills(otherAnimal);
		} else {
			super.attack(otherAnimal);
		}
	}
}
