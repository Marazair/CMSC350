/*
 * File Name: P4GUI.java
 * Name: Nick Mills
 * Date: 2/28/18
 * Purpose: Provide an interface for interacting with the DGraph class.
 */

package main;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

public class P4GUI extends JPanel implements ActionListener{
	
	private TextField fileField;
	private TextField orderField;
	private DGraph<String> graph;
	private String pattern;
	private JLabel outputLabel;
	
	private static JFrame PopupFrame = new JFrame("PopUp");
	
	public P4GUI() {
		graph = new DGraph<String>();
		
		//Declare the standard form for class naming.
		pattern = "Class[A-Z]";
		
		//Create the main panel, padding the edges.
		super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		this.setBorder(padding);
		
		//Create panels for storing the different types of buttons and fields and lay them out appropriately.
		JPanel inputPanel = new JPanel(new GridLayout(2,3));
		JPanel outputPanel = new JPanel(new FlowLayout());
		
		//Create a titled border for the output.
		outputPanel.setBorder(BorderFactory.createTitledBorder("Recompilation Order"));
		
		//Initialize labels appropriately.
		JLabel fileLabel = new JLabel("Input file name:");
		JLabel orderLabel = new JLabel("Class to recompile:");
		outputLabel = new JLabel(" ");
		
		//Initialize the fields for input.
		fileField = new TextField("");
		fileField.setColumns(30);
		
		orderField = new TextField("");
		orderField.setColumns(30);
		
		//Create the build and order buttons and prepare to listen.
		JButton build = new JButton("Build Directed Graph");
		build.setActionCommand("build");
		build.addActionListener(this);
		
		JButton order = new JButton("Topological Order");
		order.setActionCommand("sort");
		order.addActionListener(this);
		
		//Add everything to the appropriate panels.
		this.add(inputPanel);
		inputPanel.add(fileLabel);
		inputPanel.add(fileField);
		inputPanel.add(build);
		
		inputPanel.add(orderLabel);
		inputPanel.add(orderField);
		inputPanel.add(order);
		
		this.add(outputPanel);
		outputPanel.add(outputLabel);
	}
	
	public static void main(String[] args) {
		//Create the top level frame with a P4GUI in it and display.
		JFrame frame = new JFrame("Class Dependency Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		P4GUI gui = new P4GUI();
		gui.setOpaque(true);
		
		frame.setContentPane(gui);
		frame.pack();
		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("build")) {
			
			try {
				
				//Parses the file into tokens based on lines.
				List<ArrayList<String>> spec = new ArrayList<ArrayList<String>>();
				File file = new File(fileField.getText());
				Scanner fileScanner = new Scanner(file);
				
				while(fileScanner.hasNext()) {
					Scanner lineScanner = new Scanner(fileScanner.nextLine());
					ArrayList<String> lineList = new ArrayList<String>();
					spec.add(lineList);
					
					while(lineScanner.hasNext()) {
						String token = lineScanner.next();
						if(!token.matches(pattern)) {
							lineScanner.close();
							fileScanner.close();
							throw new InvalidClassName();
						}
						
						lineList.add(token);
					}
					lineScanner.close();
				}
				fileScanner.close();
				
				//Builds a graph based on said file and informs the user.
				graph.buildDGraph(spec);
				JOptionPane.showMessageDialog(PopupFrame, "Graph built successfully!");
			}
			//Inform the user if the file couldn't be opened.
			catch (FileNotFoundException fnfe) {
				JOptionPane.showMessageDialog(PopupFrame, "File did not open.");
			}
			//Inform the user if the file contained incorrect naming structure.
			catch (InvalidClassName ice) {
				JOptionPane.showMessageDialog(PopupFrame, "File contained an invalid class.");
			}
			
		}
		else if (e.getActionCommand().equals("sort")) {
			try {
				//Output the topological sort of the graph.
				outputLabel.setText(graph.topOrdGeneration(orderField.getText()));
			}
			//Inform the user if a cycle is detected.
			catch (CycleDetected cd) {
				JOptionPane.showMessageDialog(PopupFrame, "Cycle detected.");
			}
			//Inform the user if the class could not be found.
			catch (InvalidClassName ice) {
				JOptionPane.showMessageDialog(PopupFrame, "Class not found.");
			}
		}
		
	}

}
