package Animals;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.Random;

/**
 * Class AnimalGUI for GUI.
 * @author hyzhang
 *
 */
public class AnimalGUI {
	
	/**
	 * Frame, Panel for table using graphics, button for switching modes.
	 */
	private JFrame jf = new JFrame("Main");
	private JPanel table = new DrawTable();
	private JButton jb = new JButton("Auto Mode");
	
	/**
	 * Forest: storing information of animals.
	 * MouseState: event handling.
	 * attacker: the specified animal.
	 * defender: the animal being attacked if exists.
	 * nextMovements: number of options at next move.
	 * nextMove: moving options as vectors.
	 */
	private Forest newForest;
	private MouseState mouse = new MouseState();
	private int attacker = -1, defender = -1;
	private int nextMovements = 0;
	private Point[] nextMove = new Point[8];
	
	/**
	 * Some constants for constructing the GUI.
	 */
	final private int width = 725;
	final private int height = 775;	
	final private int side = 40;
	final private int line = 5;
	final private int area = 15;
	final private int startX = 10;
	final private int startY = 10;
	final private int lineLen = area * (side + line) + line;
	
	/**
	 * Same in StartupMenu.
	 */
	private String[] animalIcon;
	private boolean[] animalSelected;
	
	/**
	 * Constructor of AnimalGUI.
	 * @param _animalIcon Same in StartupMenu. Paths of icons.
	 * @param _animalSelected Same in StartupMenu. Deciding whether an animal is included.
	 */
	AnimalGUI(String[] _animalIcon, boolean[] _animalSelected) {
		
		animalIcon = new String[_animalIcon.length];
		for (int i = 0; i < _animalIcon.length; i++)
			animalIcon[i] = _animalIcon[i];
		
		animalSelected = new boolean[_animalSelected.length];
		for (int i = 0; i < _animalSelected.length; i++)
			animalSelected[i] = _animalSelected[i];
		
		start();
		
		for (int i = 0; i < 8; i++)
			nextMove[i] = new Point();
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(width, height);
		jf.setLocationRelativeTo(null);
		jf.getContentPane().setBackground(Color.white);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.white);
		buttonPanel.add(jb);
		table.setBackground(Color.white);
		
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				autoMode();
			}
		});
		
		MouseListener mouseListener = new MouseEvent();
		MouseMotionListener mouseMotionListener = new MouseEvent();
		table.addMouseListener(mouseListener);
		table.addMouseMotionListener(mouseMotionListener);
		
		jf.getContentPane().add(BorderLayout.CENTER, table);	
		jf.getContentPane().add(BorderLayout.SOUTH, buttonPanel);
		jf.setResizable(false);

		jf.setVisible(true);		
		
	}
	
	/**
	 * Painter using graphics.
	 * Firstly clear the panel. Draw the table and animals in the forest.
	 * Make some changes when the mouse is pressed.
	 * Make some changes when the mouse is released.
	 * Make some changes when the mouse is dragged.
	 * @author hyzhang
	 *
	 */
	@SuppressWarnings("serial")
	class DrawTable extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			
			g.setColor(Color.white);
			g.fillRect(0, 0, width, height);	
			g.setColor(Color.black);
			for (int i = 0; i <= area; i++) {
				g.fillRect(startX, startY + (line + side) * i, lineLen, line);
				g.fillRect(startY + (line + side) * i, startX, line, lineLen);	
			}
			for (int i = 0; i < newForest.size; i++) {
				if (newForest.getAnimal(newForest.forest[i].getPosition()) == -1) continue;
				Point pos = getAxis(newForest.forest[i].getPosition());
				g.drawImage(newForest.forest[i].getImageSrc(), pos.getX(), pos.getY(), side, side, this);
			}
			
			if (mouse.pressed) {
				g.setColor(Color.orange);
				for (int i = 0; i < nextMovements; i++) {
					Point nextPoint = nextMove[i];
					if (nextPoint.getX() < 0 || nextPoint.getX() >= area || nextPoint.getY() < 0 || nextPoint.getY() >= area)
						continue;
					Point rectStart = getAxis(nextPoint);
					if (newForest.getAnimal(nextPoint) != -1) g.setColor(Color.red);
					g.fillRect(rectStart.getX(), rectStart.getY(), side, side);
					g.setColor(Color.orange);
				}
				mouse.pressed = false;
			}
			
			if (mouse.released) {
				if (!canReach(attacker)) nextMovements = 0;
				else {
					int thisAnimal = newForest.getAnimal(mouse.releasePoint);
					if (thisAnimal != -1) {
						if (thisAnimal != attacker) {
							defender = thisAnimal;
							newForest.attack(attacker, defender);
						}
					} else {
						newForest.move(attacker, newForest.forest[attacker].getPosition(), mouse.releasePoint);
					}
					if (newForest.getAlive() == 1) restart();
				}
				mouse.released = false;
				table.repaint();
			}
			
			if (mouse.dragged) {
				boolean isNextMove = false;
				for (int i = 0; i < nextMovements; i++) {
					if (nextMove[i].equal(mouse.dragPoint)) isNextMove = true;
				}
				for (int i = 0; i < nextMovements; i++) {
					Point nextPoint = nextMove[i];
					if (nextPoint.getX() < 0 || nextPoint.getX() >= area || nextPoint.getY() < 0 || nextPoint.getY() >= area)
						continue;		
					Point rectStart = getAxis(nextPoint);
					boolean dragToNextMove = nextPoint.equal(mouse.dragPoint);
					boolean hasAnimal = (newForest.getAnimal(nextPoint) != -1);
					if (isNextMove && dragToNextMove) g.setColor(Color.blue);
					else if (hasAnimal) g.setColor(Color.red);
					else g.setColor(Color.orange);
					g.fillRect(rectStart.getX(), rectStart.getY(), side, side);
				}
				mouse.dragged = false;
			}
			
		}
	}
	
	/**
	 * MouseAdapter handling mousePressed, mouseReleased and mouseDragged.
	 * @author hyzhang
	 *
	 */
	class MouseEvent extends MouseAdapter {

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			mouse.pressPoint = getRegion(e.getX(), e.getY());
			if (mouse.pressPoint == null) {
				attacker = -1;
				return;
			}
			int thisAnimal = newForest.getAnimal(mouse.pressPoint);
			attacker = thisAnimal;
			if (thisAnimal != -1) {
				nextMovements = newForest.forest[thisAnimal].oneMove.length;
				for (int i = 0; i < nextMovements; i++)
					nextMove[i] = newForest.forest[thisAnimal].getPosition().add(newForest.forest[thisAnimal].oneMove[i]);
				mouse.pressed = true;
				table.repaint();
			}
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			if (attacker == -1) return;
			if (getRegion(e.getX(), e.getY()) == null) {
				attacker = -1;
				table.repaint();
				return;
			}
			mouse.released = true;
			mouse.releasePoint = getRegion(e.getX(), e.getY());
			table.repaint();
		}

		@Override
		public void mouseDragged(java.awt.event.MouseEvent e) {
			if (attacker == -1) return;
			mouse.dragged = true;
			mouse.dragPoint = getRegion(e.getX(), e.getY());
			table.repaint();
		}
		
	}
	
	/**
	 * Get the top-left pixel of each cell.
	 * @param region The specified cell.
	 * @return The pixel as a Point.
	 */
	Point getAxis(Point region) {		
		return region.mul(side + line).add(new Point(startX + line, startY + line));		
	}
	
	/**
	 * Get (i, j) as a region. i in [0, 15). j in [0, 15). Take the pixel coordinate as an argument.
	 * @param X x-coordinate of a pixel.
	 * @param Y y-coordinate of a pixel.
	 * @return The region (i, j) as a point.
	 */
	Point getRegion(int X, int Y) {
		if (X < startX || Y < startY) return null;
		Point region = new Point(X - startX, Y - startY).dividedBy(side + line);
		if (region.getX() >= area || region.getY() >= area) return null;		
		return region;	
	}
	
	/**
	 * Checking if the animal can jump to the target position we specify at one move.
	 * @param movedAnimal
	 * @return The boolean value as the result.
	 */
	boolean canReach(int movedAnimal) {
		for (int i = 0; i < nextMovements; i++) {
			if (mouse.releasePoint.equal(
					nextMove[i]))
				return true;
		}
		return false;
	}
	
	/**
	 * Auto mode. Terminates when there is only one animal in the forest, immediately after the attack.
	 */
	void autoMode() {
		boolean[] hasMoved = new boolean[7];
		Random r = new Random();
		while (newForest.getAlive() > 1) {
			for (int i = 0; i < 7; i++)
				hasMoved[i] = false;	
			for (int i = 0; i < area; i++)
				for (int j = 0; j < area; j++) {
					int attacker = newForest.getAnimal[i][j];
					if (attacker == -1) continue;
					if (hasMoved[attacker]) continue;
					int nextMovements = newForest.forest[attacker].oneMove.length;
					int randomMove;
					Point start = newForest.forest[attacker].getPosition();
					Point end;
					do {
						randomMove = r.nextInt(nextMovements);
						end = start.add(newForest.forest[attacker].oneMove[randomMove]);
					} while (end.getX() < 0 || end.getX() >= area || end.getY() < 0 || end.getY() >= area);
					if (newForest.getAnimal(end) != -1) newForest.attack(attacker, newForest.getAnimal(end));
					else newForest.move(attacker, start, end);
					hasMoved[attacker] = true;
					table.paintImmediately(0, 0, width, height);
					try {
						Thread.sleep(50);
					} catch (Exception args0) {}
					if (newForest.getAlive() == 1) {
						restart();
						return;
					}
				}
		}
	}
	
	/**
	 * Initialization.
	 */
	void start() {
		newForest = new Forest(animalIcon, animalSelected); 
		mouse = new MouseState();
		attacker = -1;
		defender = -1;
		nextMovements = 0;
		table.repaint();
	}
	
	/**
	 * Start a new round.
	 */
	void restart() {
		System.out.println("-------- End --------");
		int survivor = -1;
		for (int i = 0; i < area; i++) {
			for (int j = 0; j < area; j++)
				if (newForest.getAnimal[i][j] != -1) {
					survivor = newForest.getAnimal[i][j];
					break;
				}
			if (survivor != -1) break;
		}
		System.out.println("The survivor is " + newForest.forest[survivor].getName() + " at " + newForest.forest[survivor].getPosition().getX() + ", " + newForest.forest[survivor].getPosition().getY());
		start();
	}
	
	/**
	 * Main method.
	 * @param args args.
	 */
	public static void main(String args[]) {
		@SuppressWarnings("unused")
		StartupMenu startupMenu = new StartupMenu();
	}

}
