
/**
 * Represents any animal, the super class of all animals
 * @author yklam2
 *
 */
public class Animal {
	
	private int row;
	private int col;
	/**
	 * Get the row of this animal in the forest
	 * @return Row number
	 */
	public int getRow() {
		return row;
	}
	/**
	 * Get the column of this animal in the forest
	 * @return Column number
	 */
	public int getCol() {
		return col;
	}
	
	private Forest forest;
	/**
	 * Create an arbitary animal in the specific <code>forest</code>
	 * @param forest The forest this animal will be added into
	 */
	public Animal(Forest forest) {
		alive = true;
		this.forest = forest;
		forest.add(this);
	}
	/**
	 * Set a new location for this animal
	 * @param row Row of the cell in the forest
	 * @param col Column of the cell in the forest
	 */
	public void setLocation(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	private boolean moved = false;
	/**
	 * Reset moving status
	 */
	public void reset() {
		moved = false;
	}
	
	/**
	 * Get the representative symbol of this animal
	 * @return The representative symbol
	 */
	public String getSymbol() {
		return "A";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.getClass().getName();
	}
	
	
	private boolean alive;
	/**
	 * Check if this animal is still alive
	 * @return true if the animal is alive, false otherwise
	 */
	public boolean isAlive() {
		return alive;
	}
	/**
	 * Kill this animal
	 */
	public void kill() {
		alive = false;	
		System.out.println(this + " dies at " + row + ", " + col);
		
	}
	
	private static final int [] MOVE_UP = { -1, 0 };
	private static final int [] MOVE_DOWN = { 1, 0 };
	private static final int [] MOVE_LEFT = { 0, -1 };
	private static final int [] MOVE_RIGHT = { 0, 1 };
	/**
	 * Get possible movements of this animal
	 * @return The possible movements
	 */
	public int [][] getMovements() { 
		return new int[][] { MOVE_UP, MOVE_DOWN, MOVE_LEFT, MOVE_RIGHT }; 
	}
	/**
	 * Move this animal, only if moved status is false
	 * Animals moves to its four-neightbourhood randomly by default
	 */
	public void move() {
		if(!moved) {

			int forestSize = forest.getSize();
			int [][] movements = getMovements();
			
			int [] movement;
			int newRow, newCol;
			do {
				movement = movements[(int)(Math.random()*movements.length)];
				newRow = getRow() + movement[0];
				newCol = getCol() + movement[1];
			} while(newRow < 0 || newCol < 0 ||
					newRow >= forestSize ||
					newCol >= forestSize);

			
			Animal targetCell = forest.getCell(newRow, newCol);
			moved = true;
			
			if(targetCell == null) {
				forest.moveAnimal(getRow(), getCol(), newRow, newCol);
			} else {
				this.attack(targetCell);
				if(this.isAlive()) {
					forest.moveAnimal(getRow(), getCol(), newRow, newCol);
				} else {
					forest.removeAnimal(getRow(), getCol());
				}
			}
		}
	}
	/**
	 * Kills another animal
	 * @param otherAnimal The other animal to be killed
	 */
	public void kills(Animal otherAnimal) {
		System.out.println(this + " from "+getRow()+", "+getCol()+" attacks "+otherAnimal + " at "+otherAnimal.getRow()+", "+otherAnimal.getCol()+" and wins");
		otherAnimal.kill();
	}
	/**
	 * Killed by another animal
	 * @param otherAnimal The other animal that kills this animal
	 */
	public void killedBy(Animal otherAnimal) {
		System.out.println(this + " from "+getRow()+", "+getCol()+" attacks "+otherAnimal + " at "+otherAnimal.getRow()+", "+otherAnimal.getCol()+" and loses");
		this.kill();
	}
	/**
	 * Attack an animal
	 * @param otherAnimal The animal being attacked
	 */
	public void attack(Animal otherAnimal) {
		this.killedBy(otherAnimal);
	}


}
