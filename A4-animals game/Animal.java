package Animals;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * The class Animal in animal hierarchy.
 * @author hyzhang
 *
 */
abstract class Animal {
	
	private Point position;
	
	private Image icon;
	
	/**
	 * The set of next possible movements as vectors(points).
	 */
	Point[] oneMove;

	/**
	 * The constructor. To be invoked.
	 * @param p The initial position of this animal.
	 */
	Animal(Point p) { position = p; setMove(); }
	
	/**
	 * Set the field oneMove[].
	 */
	abstract void setMove();
	
	/**
	 * Get the simple name of the animal object.
	 * @return The simple name.
	 */
	abstract String getName();
	
	/**
	 * Get the image of the animal.
	 * @return The field icon.
	 */
	Image getImageSrc() { return icon; }
	
	/**
	 * Set the image of the animal.
	 * @param Path of the image.
	 */
	void setImageSrc(String s) { icon = new ImageIcon(s).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); }
	
	/**
	 * Will be called when the animal is killed under an attack.
	 * @return The position of the loser.
	 */
	Point die() { return position; }
	
	/**
	 * Attack another animal object.
	 * @param other The defender.
	 * @return The position of the loser.
	 */
	abstract Point attack(Animal other);
	
	/**
	 * Get the current position of the animal object.
	 * @return The field: position.
	 */
	Point getPosition() { return position; }
	
	/**
	 * Set the current position with another one.
	 * @param p New position.
	 */
	void setPosition(Point p) { position = p; }
	
}
