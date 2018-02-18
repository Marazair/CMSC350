package main;

public class Fraction implements Comparable{
	private double numValue;
	private String stringValue;
	private String[] fraction;
	
	public Fraction(String stringValue) {
		this.stringValue = stringValue;
		
		fraction = stringValue.split("/");
		numValue = Double.parseDouble(fraction[0]) / Double.parseDouble(fraction[1]);
		
	}
	
	public String toString() {
		return stringValue;
	}
	
	@Override
	public int compareTo(Object fraction) {
		if (this.numValue > ((Fraction)fraction).getNum())
			return 1;
		else
			return 0;
		
	}
	
	public double getNum() {
		return numValue;
	}

}
