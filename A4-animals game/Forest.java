package Animals;

import java.util.Random;

/**
 * Class Forest dealing with initialization, attacking, moving and printing.
 * @author hyzhang
 *
 */
public class Forest {
	
	/**
	 * Same in AnimalGUI.
	 */
	final int area = 15;
	
	/**
	 * The number of animals included.
	 */
	int size = 0;
	
	/**
	 * Objects of the animals.
	 */
	Animal[] forest = new Animal[7];
	
	/**
	 * A 2-D array storing an integer for each cell.
	 * If getAnimal[i][j] = k at cell (i, j), we can get the object by using forest[k].
	 * getAnimal[i][j] = -1 if there is nothing inside this cell.
	 */
	int[][] getAnimal;
	
	/**
	 * Constructor of Forest.
	 * @param animalIcon Paths of icons.
	 * @param animalSelected Deciding whether an animal is included.
	 */
	Forest(String[] animalIcon, boolean[] animalSelected) {
		
		Point[] initial = randomPosition(7);
			
		getAnimal = new int[area][];
		for (int i = 0; i < area; i++) {
			getAnimal[i] = new int[area];
			for (int j = 0; j < area; j++)
				getAnimal[i][j] = -1;
		}
		

		System.out.println("-------- Start --------");
		
		for (int i = 0; i < 7; i++) {
			if (!animalSelected[i]) continue;
			getAnimal[initial[i].getX()][initial[i].getY()] = size;
			switch (StartupMenu.animals[i]) {
				case "Dog": forest[size] = new Dog(initial[i]); break;
				case "Fox": forest[size] = new Fox(initial[i]); break;
				case "Wolf": forest[size] = new Wolf(initial[i]); break;
				case "Cat": forest[size] = new Cat(initial[i]); break;
				case "Lion": forest[size] = new Lion(initial[i]); break;
				case "Tiger": forest[size] = new Tiger(initial[i]); break;
				case "Hippo": forest[size] = new Hippo(initial[i]); break;
			}
			System.out.println(forest[size].getName() + " initialized at " + initial[i].getX() + ", " + initial[i].getY());
			forest[size++].setImageSrc(animalIcon[i]);
		}	
		
	}
	
	/**
	 * Get the initialized positions randomly.
	 * @param num The number of animals included.
	 * @return An array of points as the initialized positions.
	 */
	Point[] randomPosition(int num) {
		Point p[] = new Point[num];
		boolean[] idle[] = new boolean[area][];	
		for (int i = 0; i < area; i++) {
			idle[i] = new boolean[area];
			for (int j = 0; j < area; j++)
				idle[i][j] = true;
		}	
		Random r = new Random();
		for (int i = 0; i < num; i++) {
			int x, y;
			do {
				x = r.nextInt(area);
				y = r.nextInt(area);
			} while (!idle[x][y]);
			idle[x][y] = false;
			p[i] = new Point(x, y);
		}		
		return p;
	}
	
	/**
	 * Get an element of getAnimal[][].
	 * @param p Get the value in (p.x, p.y).
	 * @return The element.
	 */
	int getAnimal(Point p) { return getAnimal[p.getX()][p.getY()]; }
	
	/**
	 * Get the number of animals alive at this moment.
	 * @return The current number of animals in the forest.
	 */
	int getAlive() {
		int count = 0;
		for (int i = 0; i < area; i++)
			for (int j = 0; j < area; j++)
				if (getAnimal[i][j] != -1) count++;
		return count;
	}
	
	/**
	 * Get the position where the loser died.
	 * @param attacker The attacker.
	 * @param defender The defender.
	 * @return The position where the loser died.
	 */
	Point deathPoint(int attacker, int defender) {
		return forest[attacker].attack(forest[defender]);
	}
	
	/**
	 * Attacking, as well as printing information.
	 * @param attacker The attacker.
	 * @param defender The defender.
	 */
	void attack(int attacker, int defender) {
		
		int winner = attacker, loser = attacker;
		Point death = deathPoint(attacker, defender);
		if (death.equal(forest[attacker].getPosition())) winner = defender;
		else loser = defender;
		
		System.out.print(forest[attacker].getName() + " from " + forest[attacker].getPosition().getX() + ", " + forest[attacker].getPosition().getY() +" attacks " + forest[defender].getName() + " at " + forest[defender].getPosition().getX() + ", " + forest[defender].getPosition().getY() + " and ");
		if (winner == attacker) System.out.println("wins");
		else System.out.println("loses");
		System.out.println(forest[loser].getName() + " dies at " + death.getX() + ", " + death.getY());
		getAnimal[forest[loser].getPosition().getX()][forest[loser].getPosition().getY()] = -1;
		if (winner == attacker) move(winner, forest[winner].getPosition(), forest[loser].getPosition());
		
	}
	
	/**
	 * Moving, as well as printing information.
	 * @param animal The animal.
	 * @param start Starting point.
	 * @param end Ending point.
	 */
	void move(int animal, Point start, Point end) {
		forest[animal].setPosition(end);
		getAnimal[start.getX()][start.getY()] = -1;
		getAnimal[end.getX()][end.getY()] = animal;
		System.out.println(forest[animal].getName() + " moved from " + start.getX() + ", " + start.getY() + " to " + end.getX() + ", " + end.getY());
	}
	
}
