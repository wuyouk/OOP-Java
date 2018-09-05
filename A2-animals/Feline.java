
/**
 * Represents any Feline, the super class of all felines
 * @author yklam2
 *
 */
public class Feline extends Animal {
	/**
	 * Create a feline in a forest
	 * @param forest The forest this feline is placed
	 */
	public Feline(Forest forest) {
		super(forest);
	}
	/* (non-Javadoc)
	 * @see Animal#getSymbol()
	 */
	public String getSymbol() {
		return "F";
	}
	
	private static final int [] MOVE_UP = { -1, 0 };
	private static final int [] MOVE_DOWN = { 1, 0 };
	private static final int [] MOVE_LEFT = { 0, -1 };
	private static final int [] MOVE_RIGHT = { 0, 1 };
	private static final int [] MOVE_UP_LEFT = { -1, -1 };
	private static final int [] MOVE_DOWN_LEFT = { 1, -1 };
	private static final int [] MOVE_UP_RIGHT = { -1, 1 };
	private static final int [] MOVE_DOWN_RIGHT = { 1, 1 };
	/**
	 * Canine moves to its 8-neighourhood randomly
	 * @see Animal#getMovements()
	 */
	public int [][] getMovements() { 
		return new int[][] { MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT, MOVE_UP_LEFT, MOVE_DOWN_LEFT, MOVE_UP_RIGHT, MOVE_DOWN_RIGHT }; 
	}
	
	
	/* (non-Javadoc)
	 * @see Animal#attack(Animal)
	 */
	public void attack(Animal otherAnimal) {
		if(otherAnimal instanceof Canine) {
			this.kills(otherAnimal);
		} else {
			super.attack(otherAnimal);
		}
	}
}
