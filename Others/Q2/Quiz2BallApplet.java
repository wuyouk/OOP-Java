import java.applet.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Ball applet for Quiz 2
 * @author yklam2
 */
public class BallApplet extends Applet implements MouseListener, MouseMotionListener {
	private int cx;
	private int cy;
	private int r;
	private boolean isDragging;
	private boolean canDrag;
	
	
	/* (non-Javadoc)
	 * @see java.applet.Applet#init()
	 * 
	 * Initialization of Applet
	 */
	public void init() {
		cx = 100;
		cy = 100;
		r = 10;
		isDragging = false;
		canDrag = true;
		setBackground(Color.WHITE);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 * 
	 * Painting applet
	 */
	public void paint(Graphics g) {
        super.paint(g);
		g.setColor(Color.BLACK);
		g.fillOval(cx-r, cy-r, r*2, r*2);
		g.drawString("("+cx+","+cy+")", 0, 15);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 * 
	 * Mouse pressed: 
	 * Enable dragging if dragging allowed (not in animation) and click within ball
	 */
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
        if(canDrag && Math.pow(x-cx, 2)+Math.pow(y-cy, 2) <= r*r) {
			isDragging = true;
		} else {
			isDragging = false;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 * 
	 * Mouse dragged:
	 * Update ball position if dragging enabled (in MousePressed event) 
	 * Call repaint to update view
	 */
	public void mouseDragged(MouseEvent e) {
		if(isDragging) {
			cx = e.getX();
			cy = e.getY();
			repaint();
		}
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 * 
	 * Mouse released:
	 * Reset dragging status
	 * Start animation on a new Thread if mouse released outside canvas
	 *   (put animation on a new Thread to release the 
	 *    event dispatching thread for GUI update)
	 */
	public void mouseReleased(MouseEvent e) {
		isDragging = false;
		int w = getWidth();
		int h = getHeight();
		int x = e.getX();
		int y = e.getY();
		if(x < 0 || x >= w || y < 0 || y >= h) {
			canDrag = false;
			new Animator().start();
		}
	}

	public void mouseMoved(MouseEvent e) {
	}
	public void mouseClicked(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
    
	private class Animator extends Thread {
		private static final int TARGET_X = 100;
		private static final int TARGET_Y = 100;
		private static final int ANIMATION_SPEED = 100;
		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 * 
		 * Animation:
		 * Move towards target (100,100) at a time interval
		 */
		public void run() {
			int diffX = cx - TARGET_X;
			int diffY = cy - TARGET_Y;
			while(Math.abs(diffX) + Math.abs(diffY) > 3) {
				cx = cx - diffX/2;
				cy = cy - diffY/2;
				repaint();
				try {
					Thread.sleep(ANIMATION_SPEED);
				} catch (InterruptedException e) {
				}		
				diffX = cx - TARGET_X;
				diffY = cy - TARGET_Y;
			}
			cx = TARGET_X;
			cy = TARGET_Y;
			repaint();
			canDrag = true;
		}
	}
}
