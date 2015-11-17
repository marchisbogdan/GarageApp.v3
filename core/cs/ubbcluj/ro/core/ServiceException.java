package cs.ubbcluj.ro.core;

public class ServiceException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 729249557175018854L;

	public ServiceException(Exception e){
		super(e);
	}
	
	public ServiceException(String message){
		super(message);
	}
}
