package main;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Constructor;
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
	private String currentType;
	
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
		JPanel radioPanel = new JPanel(new GridLayout(1, 2));
		JPanel orderPanel = new JPanel(new GridLayout(2, 1));
		JPanel typePanel = new JPanel(new GridLayout(2, 1));
		
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
		
		currentType = "int";
		
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
		
		orderPanel.setBorder(BorderFactory.createTitledBorder("Sort Order"));
		orderPanel.add(ascend);
		orderPanel.add(descend);
		
		typePanel.setBorder(BorderFactory.createTitledBorder("Input Type"));
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
			try {
				TreeBuilder<?> tree;
				if(currentType.equals("fraction")) {
					Constructor<Fraction> constructor;
					constructor = (Constructor<Fraction>) Class.forName("main.Fraction").getConstructor(String.class);
					tree = new TreeBuilder<Fraction>(inputField.getText(), constructor, currentOrder);
				}
				else {
					Constructor<Integer> constructor;
					constructor = (Constructor<Integer>) Class.forName("java.lang.Integer").getConstructor(String.class);
					tree = new TreeBuilder<Integer>(inputField.getText(), constructor, currentOrder);
				}
				
				tree.constructTree();
				outputField.setText(tree.getTree());
			}
			catch (NoSuchMethodException | SecurityException | ClassNotFoundException ge){
				ge.printStackTrace();
			} 
			catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(PopupFrame, "Non-numeric input.");
			} 
			catch (MalformedFractionException mfe) {
				JOptionPane.showMessageDialog(PopupFrame, "Malformed fraction.");
			}
			
		}
		else if(e.getActionCommand().equals("ascend") || e.getActionCommand().equals("descend")) {
			currentOrder = e.getActionCommand();
		}
		else if(e.getActionCommand().equals("fraction") || e.getActionCommand().equals("int")) {
			currentType = e.getActionCommand();
		}
	}
}
