package Animals;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class StartupMenu {
	
	/**
	 * JFrame of startup menu.
	 */
	JFrame startup = new JFrame("Startup Menu");
	
	/**
	 * JButton to start the simulated forest.
	 */
	JButton jb = new JButton("Start");
	
	/**
	 * JLabel displaying the icons of animals in startup menu.
	 */
	JLabel[] image = new JLabel[7];
	
	/**
	 * JCheckbox specifying whether an animal is included or not.
	 */
	Checkbox[] cb = new Checkbox[7];
	
	/**
	 * Names of the animals.
	 */
	static final String[] animals = {"Dog", "Fox", "Wolf", "Cat", "Lion", "Tiger", "Hippo"};
	
	/**
	 * Path of the images for animals.
	 */
	String[] animalIcon = new String[7];
	
	/**
	 * Boolean value indicating whether each animal is included.
	 */
	boolean[] animalSelected = new boolean[7];
	
	/**
	 * Constructor of StartupMenu.
	 */
	StartupMenu() {
		
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
		for (int i = 0; i < 7; i++)
			jp.add(getBox(i));
		
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startup.setVisible(false);
				for (int i = 0; i < 7; i++) {
					animalSelected[i] = cb[i].getState();
				}
				@SuppressWarnings("unused")
				AnimalGUI animalGUI = new AnimalGUI(animalIcon, animalSelected);
			}		
		});
		
		startup.add(BorderLayout.CENTER, jp);
		startup.add(BorderLayout.SOUTH, jb);
		
		startup.pack();
		startup.setLocationRelativeTo(null);
		startup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startup.setResizable(false);
		startup.setVisible(true);
		
	}
	
	/**
	 * Draw a box for each animal. Note that the loaded image is resized to 40 * 40.
	 * @param The animal.
	 * @return JPanel of layout BoxLayout.
	 */
	private JPanel getBox(int x) {
		
		String type = animals[x];
		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.LINE_AXIS));
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		panel.setLayout(new BorderLayout());
		panel.add(innerPanel, BorderLayout.LINE_START);
		
		cb[x] = new Checkbox();
		cb[x].setState(true);
		innerPanel.add(cb[x]);
		JLabel label = new JLabel(type);
		label.setPreferredSize(new Dimension(40, 20));
		innerPanel.add(label);
		
		animalIcon[x] = "Icons/" + type + ".png";
		image[x] = new JLabel(new ImageIcon(animalIcon[x]));
		image[x].setPreferredSize(new Dimension(40, 40));
		innerPanel.add(image[x]);
		
		JButton fileChooser = new JButton("Pick an alternative icon");
		panel.add(fileChooser, BorderLayout.CENTER);
		
		fileChooser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser();
				jfc.showOpenDialog(null);
				if (jfc.getSelectedFile() != null) {
					animalIcon[x] = jfc.getSelectedFile().getAbsolutePath();
					ImageIcon newIcon = new ImageIcon(animalIcon[x]);
					newIcon.setImage(newIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
					image[x].setIcon(newIcon);				
				}
			}		
		});
		
		return panel;
		
	}
	
}
