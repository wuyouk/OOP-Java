import java.util.Scanner;

/**
 * The main program, simulate a forest
 * @author yklam2
 *
 */
public class Forest {
	private Scanner keyboard = new Scanner(System.in);
	private String getLine() {
		return keyboard.nextLine();
	}
	
	public static void main(String[] args) {
		Forest forest = new Forest(10);
		new Cat(forest);
		new Tiger(forest);
		new Lion(forest);
		new Dog(forest);
		new Fox(forest);
		new Wolf(forest);
		new Hippo(forest);

		System.out.println(forest);
		forest.simulate();
	}

	private Animal [][] cells;
	private int size;
	/**
	 * Get size of forest, the forest must be square in size
	 * @return size of forest
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Add one animal to the forest randomly
	 * @param animal Animal to be added
	 */
	public void add(Animal animal) {
		boolean isFull = false;
		for(int r=0;r<cells.length&&!isFull;++r) {
			for(int c=0; c<cells[r].length&&!isFull; ++c) {
				if(cells[r][c] == null) {
					isFull = false;
				}
			}
		}
		if(!isFull) {
			int r,c;
			do {
				r = (int)(Math.random()*size);
				c = (int)(Math.random()*size);
			} while(cells[r][c] != null);
			cells[r][c] = animal;
			animal.setLocation(r,c);
		}
	}

	/**
	 * Create a forest of a specific size
	 * @param size Size of forest
	 */
	public Forest(int size) {
		this.size = size;
		cells = new Animal[size][size];
	}
	/**
	 * Start simulation
	 */
	public void simulate() {
		while(true) {
			System.out.print("Press enter to iterate, type 'exit' to quit:");
			if(!(getLine().equals("exit"))) {
				run();
				System.out.println(this);
			} else {
				break;
			}
		}
		
	}
	private void run() {
		for(int r=0;r<cells.length;++r) {
			for(int c=0; c<cells[r].length; ++c) {
				if(cells[r][c] != null) {
					cells[r][c].move();
				}
			}
		}
		for(int r=0;r<cells.length;++r) {
			for(int c=0; c<cells[r].length; ++c) {
				if(cells[r][c] != null) {
					cells[r][c].reset();
				}
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String str = "";
		
		for(Animal[] row: cells) {
			for(Animal col: row) {
				if(col == null) {
					str = str + ".";
				} else {
					str = str + col.getSymbol();
				}
			}
			str = str + "\n";
		}
		return str;
	}

	/**
	 * Get the animal at cell (<code>row</code>, <code>col</code>)
	 * @param row Row of the cell
	 * @param col Column of the cell
	 * @return
	 */
	public Animal getCell(int row, int col) {
		return cells[row][col];
	}

	
	/**
	 * Move animal from one cell to another
	 * @param row Original row
	 * @param col Original column
	 * @param newRow New row
	 * @param newCol New column
	 */
	public void moveAnimal(int row, int col, int newRow, int newCol) {
		System.out.println(cells[row][col] + " moved from "+row+", "+col+" to "+newRow+", "+newCol);
		cells[newRow][newCol] = cells[row][col];
		cells[row][col] = null;
		cells[newRow][newCol].setLocation(newRow, newCol);
	}

	/**
	 * Remove animal from forest at cell (row, col)
	 * @param row Row of the cell
	 * @param col Col of the cell
	 */
	public void removeAnimal(int row, int col) {
		cells[row][col] = null;
	}

}
