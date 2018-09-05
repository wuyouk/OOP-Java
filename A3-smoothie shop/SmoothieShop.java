import java.util.Scanner;

/**
 * The main program, simulating a smoothie shop that create smoothie for customers
 * @author yklam2
 *
 */
public class SmoothieShop {
	
	public static void main(String[] args) {
		IngredientFactory [] factories = {
				new AppleFactory(),
				new BananaFactory(),
				new ChocolateChipsFactory(),
				new CoconutFactory(),
				new MelonFactory(),
				new MilkFactory(),
				new OrangeFactory(),
				new VanillaIceCreamFactory()
		};
		SmoothieShop shop = new SmoothieShop(factories);
		shop.buildSmoothie();
	}
	
	private Scanner keyboard = new Scanner(System.in);
	private int getOption(String message, int min, int max) {
		int option = min-1;
		do {
			System.out.print(message);
			option = keyboard.nextInt();
		} while(option < min || option > max);
		return option;
	}

	private IngredientFactory [] factories;
	public SmoothieShop(IngredientFactory [] factories) {
		this.factories = factories;
	}
	/**
	 * The main routine that build a smoothie
	 */
	public void buildSmoothie() {
		Smoothie mix = new Smoothie();
		Ingredient ing = null;
		do {
			ing = getIngredient();
			if(ing != null)
				mix.addIngredient(ing);
		} while(ing != null);
		mix.create(getContainer(mix.getAvailableContainers()));
	}
	
	/**
	 * Ask user for one ingredient
	 * @return a new ingredient that the user choose
	 */
	public Ingredient getIngredient() {

		int i = 1;
		for(IngredientFactory factory : factories) {
			System.out.println(i+". "+factory.getIngredientName());
			i++;
		}
		System.out.println("What would you like to add to your smoothie?");
		int option = getOption("Please enter your choice (1-"+(i-1)+", or 0 to finish the order): ", 0, i-1);
		
		if(option == 0)
			return null;
		
		return factories[option-1].getIngredient();
	}
	
	/**
	 * Ask user to pick a container
	 * Always include plastic cup as the first choice
	 * @param containers List of container to be chosen
	 * @return The container that the user choose
	 */
	public Container getContainer(Container [] containers) {
		Container plasticCup = new PlasticCup();
		if(containers.length == 0) 
			return plasticCup;

		int i = 1;
		System.out.println(i+". "+plasticCup+" ("+plasticCup.getCapacity()+"ml)");
		for(Container container : containers) {
			i++;
			System.out.println(i+". "+container+" ("+container.getCapacity()+"ml)");
		}
		System.out.println("What would you like to use to hold your smoothie?");
		int option = getOption("Please enter your choice (1-"+i+"): ", 1, i);
		if(option == 1)
			return plasticCup;
		else
			return containers[option-2];
	}
}

