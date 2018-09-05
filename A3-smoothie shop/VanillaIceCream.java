
public class VanillaIceCream extends Ingredient {

	public VanillaIceCream() {
		super(40, 45);
	}
	public String toString() { 
		return "Vanilla ice cream";
	}
}
/**
 * A factory class that create vanilla ice cream object
 * @author yklam2
 */
class VanillaIceCreamFactory extends IngredientFactory {
	public Ingredient getIngredient() {
		return new VanillaIceCream();
	}
	public String getIngredientName() {
		return "Vanilla ice cream";
	}
	
}