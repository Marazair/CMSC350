/*
 * File Name: TreeBuilder.java
 * Name: Nick Mills
 * Date: 2/20/18
 * Purpose: Contains the structure for building a BST.
 */


package main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreeBuilder<T extends Comparable<T>> {
	private BST<T> tree;
	private List<T> list;
	private String string;
	private Constructor<T> type;
	private String sort;
	
	//Constructor takes a string to parse, a constructor indicating which object should be made,
		//and a string to indicate sort order.
	public TreeBuilder(String string, Constructor<T> type, String sort) {
		this.string = string;
		this.type = type;
		this.sort = sort;
	}
	
	//Creates a new tree and fills it with objects made from tokens of the passed string.
	public void constructTree() throws NumberFormatException, MalformedFractionException {
		list = getTokens();
		tree = new BST<T>(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			tree.insertNode(list.get(i));
		}
	}
	
	//Returns the tree in string form depending on the appropriate order.
	public String getTree() {
		return tree.inOrderTraversal(sort);
	}
	
	//Returns a list containing objects made from the tokens of the passed string.
	private List<T> getTokens() throws NumberFormatException, MalformedFractionException {
		List<T> tokens = new ArrayList<T>();
		Scanner scanner = new Scanner(string);
		String currentToken;
		
		//Parses the string into tokens.
		while(scanner.hasNext()) {
			currentToken = scanner.next();
			
			//Ensure that there are no characters in each token other than forward slashes and digits.
			if(!currentToken.matches("[\\d/]+"))
				throw new NumberFormatException();
			
			//Attempt to create a new instance of the correct object based on the passed constructor and the current token.
			try {
				tokens.add(type.newInstance(currentToken));
			}
			//If it throws an exception, catch it if it is a NumberFormatException or MalformedFractionException to
				//inform the user in the GUI. If it is neither of those, something has gone wrong; show the stack trace.
			catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
				if(e.getCause() instanceof NumberFormatException) {
					throw new NumberFormatException();
				}
				else if(e.getCause() instanceof MalformedFractionException) {
					throw new MalformedFractionException();
				}
				else {
					e.printStackTrace();
				}
			}
			
		}
		
		//Close the scanner and return the list.
		scanner.close();
		return tokens;
	}
}
