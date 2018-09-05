import java.text.DecimalFormat;

/**
 * The shop class, the main program
 * @author Kevin Lam
 */
public class Shop {

	private double total;

	/**
	 * The main routine
	 * @param args arguments, not used
	 */
	public static void main(String[] args) {
		Shop shop = new Shop();
		shop.checkout(new Fruit(10));
		shop.checkout(new Milk(12, 250));
		shop.checkout(new SkimMilk(340));
		shop.checkout(new SoyMilk(150));
		shop.checkout(new DiscountBasket(new ShopItem[] { new Fruit(15), new SoyMilk(340) }));
	}
	
	/**
	 * Create a shop that can check out items
	 */
	public Shop() {
		total = 0;
	}
	
	/**
	 * Check out one item
	 * @param item item to check out
	 */
	public void checkout(ShopItem item) {
		System.out.println(item);
		total += item.getPrice();
		// For demonstration only, number format is not graded
		DecimalFormat myFormatter = new DecimalFormat("#.###");
		System.out.println("Total: $"+myFormatter.format(total));
	}

}
