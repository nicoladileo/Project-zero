package io.github.nicoladileo.exception;

public class DAOException extends Exception {
	
	public DAOException() {
		super();
	}
	
	public DAOException(String t) {
		super(t);
	}
	
	public DAOException(Throwable t) {
		super(t);
	}
}
