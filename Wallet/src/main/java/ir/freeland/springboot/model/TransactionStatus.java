package ir.freeland.springboot.model;

public enum TransactionStatus {
	PENDING,   // has initiated but is not yet completed
	DONE,      // success
	FAILED,    // could not be completed
	CANCELLED  // cancelled by the user

}
