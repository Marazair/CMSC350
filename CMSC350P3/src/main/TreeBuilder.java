package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TreeBuilder {
	BST<?> tree;
	List<? extends Comparable> list;
	
	public TreeBuilder(String string, String type, String sort) {
		if(type.equals("fraction")) {
			list = getTokensFrac(string);
			tree = new BST<Fraction>((Fraction) Collections.max(list));
		}
	}
	
	private List<Fraction> getTokensFrac(String string) throws NumberFormatException {
		List<Fraction> tokens = new ArrayList<Fraction>();
		Scanner scanner = new Scanner(string);
		String currentToken;
		
		//Add tokens if they match the pattern. If not, throw an exception.
		while (scanner.hasNext()) {
			currentToken = scanner.next();
			if(currentToken.matches("\\d+/\\d+"))
				tokens.add(new Fraction(currentToken));
			else
				throw new NumberFormatException();
		}
		
		scanner.close();
		return tokens;
	}
	
	private List<Integer> getTokensInt(String string) throws NumberFormatException {
		List<Integer> tokens = new ArrayList<Integer>();
		Scanner scanner = new Scanner(string);
		String currentToken;
		
		//Add tokens if they match the pattern. If not, throw an exception.
		while (scanner.hasNext()) {
			currentToken = scanner.next();
			if(currentToken.matches("\\d+"))
				tokens.add(Integer.parseInt(currentToken));
			else
				throw new NumberFormatException();
		}
		
		scanner.close();
		return tokens;
	}
}
