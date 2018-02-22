package main;

public class Fraction implements Comparable<Fraction>{
	private double numValue;
	private String stringValue;
	private String[] fraction;
	
	public Fraction(String stringValue) throws MalformedFractionException {
		if(!stringValue.matches("\\d+/\\d+")) {
			throw new MalformedFractionException();
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
		if (numValue > fraction.numValue) {
			return 1;
		}
		else if (numValue < fraction.numValue) {
			return -1;
		}
		else {
			return 0;
		}
		
	}
	
	public double getNum() {
		return numValue;
	}

}
