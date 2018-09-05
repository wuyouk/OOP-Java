/**
 * The main program for part b, simulating a smoothie shop that create smoothie for customers
 * @author yklam2
 *
 */
public class SmoothieShopB extends SmoothieShop {
	
	public static void main(String[] args) {
		IngredientFactory [] factories = {
				new AppleFactory(),
				new BananaFactory(),
				new ChocolateChipsFactory(),
				new CoconutFactory(),
				new MelonFactory(),
				new MilkFactory(),
				new PeachFactory(),
				new PineappleFactory(),
				new VanillaIceCreamFactory(),
		};
		SmoothieShopB shop = new SmoothieShopB(factories);
		shop.buildSmoothie();
	}
	
	public SmoothieShopB(IngredientFactory [] factories) {
		super(factories);
	}
}

