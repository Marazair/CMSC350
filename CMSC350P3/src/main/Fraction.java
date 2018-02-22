/*
 * File Name: Fraction.java
 * Name: Nick Mills
 * Date: 2/15/18
 * Purpose: Provide structure for a Fraction data type.
 */

package main;

public class Fraction implements Comparable<Fraction>{
	private double numValue;
	private String stringValue;
	private String[] fraction;
	
	//Checks if the fraction is formed properly, then calculates the numeric value of the fraction and stores its string form.
	public Fraction(String stringValue) throws MalformedFractionException {
		if(!stringValue.matches("\\d+/\\d+")) {
			throw new MalformedFractionException();
		}
		
		this.stringValue = stringValue;
		fraction = stringValue.split("/");
		numValue = Double.parseDouble(fraction[0]) / Double.parseDouble(fraction[1]);
		
	}
	
	//Returns the original string form of the fraction.
	public String toString() {
		return stringValue;
	}
	
	//Compares two fractions. Returns > 0 if passed fraction is smaller, < 0 if it is larger, and 0 if they are equal.
	@Override
	public int compareTo(Fraction fraction) {
		if (numValue > fraction.getNum()) {
			return 1;
		}
		else if (numValue < fraction.getNum()) {
			return -1;
		}
		else {
			return 0;
		}
		
	}
	
	//Returns the numeric value of the fraction.
	public double getNum() {
		return numValue;
	}

}
