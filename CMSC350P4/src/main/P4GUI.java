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
		pattern = "Class[A-Z]";
		
		super.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		this.setBorder(padding);
		
		JPanel inputPanel = new JPanel(new GridLayout(2,3));
		JPanel outputPanel = new JPanel(new FlowLayout());
		
		outputPanel.setBorder(BorderFactory.createTitledBorder("Recompilation Order"));
		
		JLabel fileLabel = new JLabel("Input file name:");
		JLabel orderLabel = new JLabel("Class to recompile:");
		outputLabel = new JLabel("");
		
		fileField = new TextField("");
		fileField.setColumns(30);
		
		orderField = new TextField("");
		fileField.setColumns(30);
		
		JButton build = new JButton("Build Directed Graph");
		build.setActionCommand("build");
		build.addActionListener(this);
		
		JButton order = new JButton("Topological Order");
		order.setActionCommand("sort");
		order.addActionListener(this);
		
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
		JFrame frame = new JFrame("Binary Search Tree Sort");
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
				
				graph.buildDGraph(spec);
				JOptionPane.showMessageDialog(PopupFrame, "Graph built successfully!");
			} 
			catch (FileNotFoundException fnfe) {
				JOptionPane.showMessageDialog(PopupFrame, "File did not open.");
			}
			catch (InvalidClassName ice) {
				JOptionPane.showMessageDialog(PopupFrame, "File contained an invalid class.");
			}
			
		}
		else if (e.getActionCommand().equals("sort")) {
			try {
				outputLabel.setText(graph.topOrdGeneration(orderField.getText()));
			}
			catch (CycleDetected cd) {
				JOptionPane.showMessageDialog(PopupFrame, "Cycle detected.");
			} 
			catch (InvalidClassName ice) {
				JOptionPane.showMessageDialog(PopupFrame, "Class not found.");
			}
		}
		
	}

}
