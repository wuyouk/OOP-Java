import java.util.Random;

/**
 * Super class for all ingredients
 * @author yklam2
 */
public abstract class Ingredient {
	private static Random rand = new Random();
	private int volume;
	
	/**
	 * Create a ingredient of volume between <code>min</code> and <code>max</code> (inclusive)
	 * @param min Minimum volume
	 * @param max Maximum volume
	 */
	public Ingredient(int min, int max) {
		volume = min + rand.nextInt(max-min+1);
	}
	/**
	 * Create a ingredient of a specific <code>volume</code>
	 * @param volume Volume of the ingredient
	 */
	public Ingredient(int volume) {
		this.volume = volume;
	}
	
	/**
	 * Get the volume of this ingredient
	 * @return Volume of this ingredient
	 */
	public int getVolume() {
		return volume;
	}
}
/**
 * A factory class that generate ingredients
 * @author yklam2
 *
 */
abstract class IngredientFactory {
	/**
	 * Create a new ingredient
	 * @return the ingredient
	 */
	public abstract Ingredient getIngredient();
	/**
	 * Get the name of ingredient
	 * @return Name of ingredient
	 */
	public abstract String getIngredientName();
}