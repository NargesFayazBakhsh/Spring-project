package ir.freeland.springboot.exception;


public class TransactionNotFoundException extends RuntimeException{
	public TransactionNotFoundException (String message) {
		super(message);
	}
}
