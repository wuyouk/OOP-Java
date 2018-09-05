/**
 * A melon class, a ingredient and a container
 * @author yklam2
 */
public class Melon extends Ingredient implements Container {

	/**
	 * Create a melon object, volume between 50 to 70.
	 */
	public Melon() {
		super(50, 70);
	}
	/* (non-Javadoc)
	 * @see Container#getCapacity()
	 */
	public int getCapacity() {
		return getVolume() * 4;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() { 
		return "Melon";
	}	
}
/**
 * A factory class that create melon object
 * @author yklam2
 */
class MelonFactory extends IngredientFactory {
	public Ingredient getIngredient() {
		return new Melon();
	}
	public String getIngredientName() {
		return "Melon";
	}
	
}