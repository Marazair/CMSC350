package main;

public class InvalidTokenException extends Exception{
	private String token;
	
	public InvalidTokenException() {
		
	}
	
	public InvalidTokenException(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
}
