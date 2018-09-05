import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.*;


/**
 * The Image canvas
 * @author yklam2
 *
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 6191689375531691380L;
	private static final int GRID_SIZE = 20;
	private static final int BLOCK_SIZE = 20;
	
	private int currentRow;
	private int currentCol;
	private ImageBlock [][] imageBlocks;
	/**
	 * Keep track of the latest timestamp, used when requesting blocks
	 */
	private long maxTimestamp;
	
	/**
	 * Prepare canvas for displaying of image blocks
	 */
	public ImagePanel() {
		setPreferredSize(new Dimension(BLOCK_SIZE*GRID_SIZE,BLOCK_SIZE*GRID_SIZE));
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		setLayout(new GridLayout(GRID_SIZE,GRID_SIZE));
		
		Random r = new Random();
		currentRow = r.nextInt(GRID_SIZE);
		currentCol = r.nextInt(GRID_SIZE);
		imageBlocks = new ImageBlock[GRID_SIZE][GRID_SIZE];
		
		for(int row=0; row<GRID_SIZE; ++row) {
			for(int col=0; col<GRID_SIZE; ++col) {
				imageBlocks[row][col] = new ImageBlock(BLOCK_SIZE, BLOCK_SIZE);
				add(imageBlocks[row][col]);				
			}
		}
		
		maxTimestamp = 0;
		
	}
	/**
	 * Set the whole image
	 * @param image Image to be displayed
	 */
	public void setImage(BufferedImage image) {
		maxTimestamp = System.currentTimeMillis();
		BufferedImage newImage = new BufferedImage(BLOCK_SIZE*GRID_SIZE, BLOCK_SIZE*GRID_SIZE, BufferedImage.TYPE_INT_RGB);
		Graphics g = newImage.getGraphics();
		g.drawImage(image.getScaledInstance(BLOCK_SIZE*GRID_SIZE, BLOCK_SIZE*GRID_SIZE, Image.SCALE_FAST), 0, 0, null);
		g.dispose();
		for(int row=0; row<GRID_SIZE; ++row) {
			for(int col=0; col<GRID_SIZE; ++col) {
				imageBlocks[row][col].setImage(maxTimestamp, newImage.getRGB(col*BLOCK_SIZE, row*BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE, null, 0, BLOCK_SIZE));
			}
		}
	}
	/**
	 * Prepare a request to be sent out
	 * @return Request message to be sent
	 */
	public RequestMessage getRequest() {
		RequestMessage request = new RequestMessage();
		request.timestamp = maxTimestamp;
		request.blockNeeded = new boolean[GRID_SIZE][GRID_SIZE];
		for(int r=0;r<GRID_SIZE;++r) {
			for(int c=0; c<GRID_SIZE; ++c) {
				request.blockNeeded[r][c] = (maxTimestamp==0 || (imageBlocks[r][c].getTimestamp()<maxTimestamp));
			}
		}
		return request;
	}
	private synchronized int[] getUpdateBlock(RequestMessage request) {
		for(int i=0; i<GRID_SIZE*GRID_SIZE;++i) {
			currentCol++;
			if(currentCol >= GRID_SIZE) {
				currentCol %= GRID_SIZE;
				currentRow = (currentRow + 1)%GRID_SIZE;
			}
			if(imageBlocks[currentRow][currentCol].getTimestamp() > 0 && (imageBlocks[currentRow][currentCol].getTimestamp() > request.timestamp || request.blockNeeded[currentRow][currentCol])) {
				return new int[]{currentRow, currentCol};
			}
		} 
		return null;
	}
	/**
	 * Process a request and prepare a response accordingly
	 * @param request Request message received
	 * @return Response message to be sent back
	 */
	public ResponseMessage processRequest(RequestMessage request) {
		int [] updateBlock = getUpdateBlock(request);
		ResponseMessage response = null;
		if(updateBlock != null) {
			response = new ResponseMessage();
			response.row = updateBlock[0];
			response.col = updateBlock[1];
			response.block = imageBlocks[response.row][response.col];

		}
		return response;
	}	
	/**
	 * Process a response message
	 * @param response Response message received
	 */
	public void processResponse(ResponseMessage response) {
		long blockTimestamp = response.block.getTimestamp();
		if(blockTimestamp>maxTimestamp) {
			maxTimestamp = blockTimestamp;
		} 
		if(imageBlocks[response.row][response.col].getTimestamp() < blockTimestamp) {
			imageBlocks[response.row][response.col].setImage(response.block);
			System.out.println("Received subimage from "+response.peerId+" ("+response.row+","+response.col+")");
		}
	}
}
