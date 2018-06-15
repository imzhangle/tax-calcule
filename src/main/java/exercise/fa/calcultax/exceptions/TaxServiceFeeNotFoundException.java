package exercise.fa.calcultax.exceptions;

public class TaxServiceFeeNotFoundException extends TaxServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TaxServiceFeeNotFoundException() {
		super();
	}

	public TaxServiceFeeNotFoundException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public TaxServiceFeeNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public TaxServiceFeeNotFoundException(String message) {
		super(message);

	}

	public TaxServiceFeeNotFoundException(Throwable cause) {
		super(cause);

	}
	
}
