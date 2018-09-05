
/**
 * Represents any Canine, the super class of all canines
 * @author yklam2
 *
 */
public class Canine extends Animal {
	/**
	 * Create a canine in a forest
	 * @param forest The forest this canine is placed
	 */
	public Canine(Forest forest) {
		super(forest);
	}
	/* (non-Javadoc)
	 * @see Animal#getSymbol()
	 */
	public String getSymbol() {
		return "C";
	}
	
	private static final int [] MOVE_UP = { -1, 0 };
	private static final int [] MOVE_DOWN = { 1, 0 };
	private static final int [] MOVE_LEFT = { 0, -1 };
	private static final int [] MOVE_RIGHT = { 0, 1 };
	private static final int [] MOVE_UP2 = { -2, 0 };
	private static final int [] MOVE_DOWN2 = { 2, 0 };
	private static final int [] MOVE_LEFT2 = { 0, -2 };
	private static final int [] MOVE_RIGHT2 = { 0, 2 };
	
	/**
	 * Canine moves 1 or 2 steps to its 4-neighourhood randomly
	 * @see Animal#getMovements()
	 */
	public int [][] getMovements() { 
		return new int[][] { MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT, MOVE_UP2, MOVE_DOWN2, MOVE_LEFT2, MOVE_RIGHT2 }; 
	}
	
	/* (non-Javadoc)
	 * @see Animal#attack(Animal)
	 */
	public void attack(Animal otherAnimal) {
		if(otherAnimal instanceof Canine) {
			if(Math.random() < 0.5) {
				this.killedBy(otherAnimal);
			} else {
				this.kills(otherAnimal);
			}
		} else {
			super.attack(otherAnimal);
		}
	}
}
