package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TreeBuilder {
	private BST<?> tree;
	private List<? extends Comparable> list;
	private String string;
	private String type;
	private String sort;
	
	public TreeBuilder(String string, String type, String sort) {
		this.string = string;
		this.type = type;
		this.sort = sort;
	}
	
	public void constructTree() {
		tree = new BST (Collections.max(getTokens(string)));
	}
	
	public List<? extends Comparable> getTokens(String string) throws NumberFormatException{
		List<? extends Comparable> tokens;
		Scanner scanner = new Scanner(string);
		String currentToken;
		String regex = "";
		
		if (type.equals("fraction")) {
			regex = "\\d+/\\d+";
			tokens = new ArrayList<Fraction>();
			while(scanner.hasNext()) {
				currentToken = scanner.next();
				if(currentToken.matches(regex))
					tokens.add(new Fraction(currentToken));
				else
					throw new NumberFormatException();
			}
		}
		else if (type.equals("int")){
			regex = "\\d+";
			tokens = new ArrayList<Integer>();
			while(scanner.hasNext()) {
				currentToken = scanner.next();
				if(currentToken.matches(regex))
					tokens.add(Integer.parseInt(currentToken));
				else
					throw new NumberFormatException();
			}
		}
		
		scanner.close();
		return tokens;
	}
}
