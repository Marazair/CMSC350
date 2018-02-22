package main;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.*;

public class P3GUI extends JPanel implements ActionListener{
	
	private TextField inputField;
	private TextField outputField;
	private JRadioButton ascend;
	private JRadioButton descend;
	private JRadioButton fraction;
	private JRadioButton integer;
	private String currentOrder;
	
	private static JFrame PopupFrame = new JFrame("PopUp");
	
	public P3GUI() {
		
		//Create the main panel, padding the edges.
		super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		this.setBorder(padding);
		
		//Create panels for storing the different types of buttons and fields and lay them out appropriately.
		JPanel inputPanel = new JPanel(new FlowLayout());
		JPanel outputPanel = new JPanel(new FlowLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JPanel radioPanel = new JPanel(new FlowLayout());
		JPanel orderPanel = new JPanel(new FlowLayout());
		JPanel typePanel = new JPanel(new FlowLayout());
		
		//Create the sort button and prepare to listen.
		JButton sort = new JButton("Perform Sort");
		sort.setActionCommand("sort");
		sort.addActionListener(this);
		
		//Create radio buttons for sort order and prepare to listen.
		ascend = new JRadioButton("Ascending");
		ascend.setActionCommand("ascend");
		ascend.setSelected(true);
		
		currentOrder = "ascend";
		
		ascend.addActionListener(this);
		
		descend = new JRadioButton("Descending");
		descend.setActionCommand("descend");
		descend.addActionListener(this);
		
		//Group the sort order radio buttons.
		ButtonGroup order = new ButtonGroup();
		order.add(ascend);
		order.add(descend);
		
		//Create radio buttons for input type and prepare to listen.
		integer = new JRadioButton("Integer");
		integer.setActionCommand("int");
		integer.setSelected(true);
		
		integer.addActionListener(this);
		
		fraction = new JRadioButton("Fraction");
		fraction.setActionCommand("fraction");
		fraction.addActionListener(this);
		
		//Group the input type radio buttons.
		ButtonGroup type = new ButtonGroup();
		type.add(integer);
		type.add(fraction);
		
		//Create labels for the text boxes.
		JLabel inputLabel = new JLabel("Original List: ");
		JLabel resultLabel = new JLabel("Sorted List: ");
		
		//Create the input field
		inputField = new TextField("");
		inputField.setColumns(30);
		
		//Create the output field.
		outputField = new TextField("");
		outputField.setColumns(20);
		outputField.setEditable(false);
		
		//Add objects to the panel.
		add(inputPanel);
		inputPanel.add(inputLabel);
		inputPanel.add(inputField);
		
		add(outputPanel);
		outputPanel.add(resultLabel);
		outputPanel.add(outputField);
		
		add(buttonPanel);
		buttonPanel.add(sort);
		
		add(radioPanel);
		radioPanel.add(orderPanel);
		radioPanel.add(typePanel);
		
		orderPanel.add(ascend);
		orderPanel.add(descend);
		
		typePanel.add(integer);
		typePanel.add(fraction);
	}

	public static void main(String[] args) {
		//Create the top-level frame with a P1GUI in it.
		JFrame frame = new JFrame("Binary Search Tree Sort");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		P3GUI gui = new P3GUI();
		gui.setOpaque(true);
		
		frame.setContentPane(gui);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("sort")) {
			TreeBuilder<?> tree;
			if(fraction.isSelected()) {
				Constructor<Fraction> constructor;
				try {
					constructor = (Constructor<Fraction>) Class.forName("main.Fraction").getConstructor(String.class);
					tree = new TreeBuilder<Fraction>(inputField.getText(), constructor, currentOrder);
					try {
						tree.constructTree();
					} 
					catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(PopupFrame, "Please input the value in the proper format.");
					}
				} 
				catch (NoSuchMethodException | SecurityException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if (integer.isSelected()) {
				Constructor<Integer> constructor;
				try {
					constructor = (Constructor<Integer>) Class.forName("java.lang.Integer").getConstructor(String.class);
					tree = new TreeBuilder<Integer>(inputField.getText(), constructor, currentOrder);
					try {
						tree.constructTree();
					} 
					catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(PopupFrame, "Please input the value in the proper format.");
					}
				} 
				catch (NoSuchMethodException | SecurityException | ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
		}
		
	}
}
