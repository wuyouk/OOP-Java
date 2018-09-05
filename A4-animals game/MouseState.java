package Animals;

/**
 * MouseState class, handling mouse events.
 * @author hyzhang
 *
 */
public class MouseState {
	
	/**
	 * Set to true when pressing the mouse. Set to false after repaint().
	 */
	boolean pressed = false;
	
	/**
	 * Set to true when releasing the mouse. Set to false after repaint().
	 */
	boolean released = false;
	
	/**
	 * Set to true when dragging the mouse. Set to false after repaint().
	 */
	boolean dragged = false;
	
	/**
	 * Store the position of pressing.
	 */
	Point pressPoint = new Point();
	
	/**
	 * Store the position of releasing.
	 */
	Point releasePoint = new Point();
	
	/**
	 * Store the position of dragging.
	 */
	Point dragPoint = new Point();
	
}
