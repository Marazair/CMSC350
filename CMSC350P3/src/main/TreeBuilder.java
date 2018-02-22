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
	
	public void constructTree() throws NumberFormatException {
		list = getTokens();
		tree = new BST<T> (Collections.max(list));
		for (int i = 0; i < list.size(); i++) {
			tree.insertNode(list.get(i));
		}
	}
	
	private List<T> getTokens() throws NumberFormatException {
		List<T> tokens = new ArrayList<T>();
		Scanner scanner = new Scanner(string);
		String currentToken;
		
		while(scanner.hasNext()) {
			currentToken = scanner.next();
			try {
				tokens.add(type.newInstance(currentToken));
			}
			catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
				throw new NumberFormatException();
			}
			
		}
		
		scanner.close();
		System.out.println(tokens);
		return tokens;
	}
}
