import java.util.ArrayList;

/**
 * Represents a smoothie that consists of a number of ingredients and a container
 * @author yklam2
 *
 */
public class Smoothie {
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Container> availableContainers;

	/**
	 * Create an empty smoothie
	 */
	public Smoothie() {
		ingredients = new ArrayList<Ingredient>();
		availableContainers = new ArrayList<Container>();
	}
	
	/**
	 * Add ingredient to this smoothie
	 * @param ingredient Ingredient to be added
	 */
	public void addIngredient(Ingredient ingredient) {
		ingredients.add(ingredient);
		if(ingredient instanceof Container)
			availableContainers.add((Container)ingredient);
		
		System.out.println("Added "+ingredient+" "+ingredient.getVolume()+"ml");
	}
	
	/**
	 * Get a list of ingredients that can be used as a container
	 * @return List of containers
	 */
	public Container[] getAvailableContainers() {
		return availableContainers.toArray(new Container[0]);
	}
	
	private void addIce(Container container) {
		int capacity = container.getCapacity();
		for(Ingredient ingredient: ingredients) {
			capacity -= ingredient.getVolume();
		}
		if(capacity > 0) {
			addIngredient(new Ice(capacity));
		}
	}
	
	/**
	 * Create a smoothie and put it into the container
	 * Ice will be added automatically to fill up the container
	 * @param container Container to hold the smoothie
	 */
	public void create(Container container) {
		
		addIce(container);
		
		System.out.println("Smoothie ingredients:");
		int totalVolume = 0;
		for(Ingredient ingredient: ingredients) {
			totalVolume += ingredient.getVolume();
			System.out.println(ingredient+" ("+ingredient.getVolume()+"ml)");
		}
		System.out.println("Container: "+container+" ("+container.getCapacity()+"ml)");
		if(container.getCapacity() < totalVolume) {
			System.out.println("Wasted " + (totalVolume-container.getCapacity())+"ml!");
		}
	}
}
