package ir.freeland.springboot.exception;


public class PersonNotFoundException extends RuntimeException{
	public PersonNotFoundException (String message) {
		super(message);
	}
}
