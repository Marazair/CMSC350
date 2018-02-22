package main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TreeBuilder<T extends Comparable<T>> {
	private BST<T> tree;
	private List<T> list;
	private String string;
	private Constructor<T> type;
	private String sort;
	
	public TreeBuilder(String string, Constructor<T> type, String sort) {
		this.string = string;
		this.type = type;
		this.sort = sort;
	}
	
	public void constructTree() throws NumberFormatException, MalformedFractionException {
		list = getTokens();
		tree = new BST<T> (Collections.max(list));
		for (int i = 0; i < list.size(); i++) {
			tree.insertNode(list.get(i));
		}
	}
	
	public String getTree() {
		if (sort.equals("ascend")) {
			return tree.inOrderTraversal();
		}
		else {
			return tree.inOrderTraversal();
		}
	}
	
	private List<T> getTokens() throws NumberFormatException, MalformedFractionException {
		List<T> tokens = new ArrayList<T>();
		Scanner scanner = new Scanner(string);
		String currentToken;
		
		while(scanner.hasNext()) {
			currentToken = scanner.next();
			if(!currentToken.matches("[\\d/]+"))
				throw new NumberFormatException();
			
			try {
				tokens.add(type.newInstance(currentToken));
			}
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
		
		scanner.close();
		System.out.println(tokens);
		return tokens;
	}
}
