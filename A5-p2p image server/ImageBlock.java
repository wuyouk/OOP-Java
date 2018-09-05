import java.awt.image.BufferedImage;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * A image block in the canvas
 * @author yklam2
 *
 */
public class ImageBlock extends JLabel {

	private static final long serialVersionUID = -3871940881401713016L;
	/**
	 * Timestamp of this block, used to identify most updated block
	 */
	private long timestamp;
	
	/**
	 * Get timestamp of block
	 * @return timestamp of block
	 */
	public long getTimestamp() {
		return timestamp;
	}
	
	private int width;
	private int height;
	
	/**
	 * Image buffer, cannot be serialized and so readObject/writeObject is defined to serialize it 
	 */
	private transient BufferedImage image;
	
	/**
	 * Prepare a image block of specific width and height
	 * @param width Width of image block
	 * @param height Height of image block
	 */
	public ImageBlock(int width, int height) {
		timestamp = 0; // 0 means never updated
		this.width = width;
		this.height = height;
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}
	/**
	 * Set image by pixels
	 * @param timestamp Timestamp of block
	 * @param pixels Pixel values from BufferedImage.getRGB()
	 */
	public synchronized void setImage(long timestamp, int [] pixels) {
		this.timestamp = timestamp;
		setImage(pixels);
	}
	/**
	 * Set image by another block
	 * @param block ImageBlock to be used
	 */
	public synchronized void setImage(ImageBlock block) {
		timestamp = block.timestamp;
		width = block.width;
		height = block.height;
		image = block.image;
		setIcon(new ImageIcon(image));
		revalidate();		
	}
	private void setImage(int [] pixels) {
		image.setRGB(0, 0, width, height, pixels, 0, width);
		setIcon(new ImageIcon(image));
		revalidate();
	}
	private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException
    {
        inputStream.defaultReadObject();
        int [] pixels = (int [])inputStream.readObject();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        image.setRGB(0, 0, width, height, pixels, 0, width);
    }    
	private synchronized void writeObject(ObjectOutputStream outputStream) throws IOException, ClassNotFoundException
    {
		outputStream.defaultWriteObject();
        outputStream.writeObject(image.getRGB(0, 0, width, height, null, 0, width));
    }    
}
