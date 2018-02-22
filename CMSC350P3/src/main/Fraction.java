package main;

public class Fraction implements Comparable<Fraction>{
	private double numValue;
	private String stringValue;
	private String[] fraction;
	
	public Fraction(String stringValue) throws NumberFormatException {
		if(stringValue.matches("\\d+/\\d+")) {
			throw new NumberFormatException();
		}
		
		this.stringValue = stringValue;
		fraction = stringValue.split("/");
		numValue = Double.parseDouble(fraction[0]) / Double.parseDouble(fraction[1]);
		
	}
	
	public String toString() {
		return stringValue;
	}
	
	@Override
	public int compareTo(Fraction fraction) {
		return (int)(numValue - fraction.getNum());
		
	}
	
	public double getNum() {
		return numValue;
	}

}
