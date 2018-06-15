package exercise.fa.calcultax.exceptions;

public class TaxServiceInvalidParam extends TaxServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TaxServiceInvalidParam() {
		super();
	}

	public TaxServiceInvalidParam(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TaxServiceInvalidParam(String message, Throwable cause) {
		super(message, cause);
	}

	public TaxServiceInvalidParam(String message) {
		super(message);
	}

	public TaxServiceInvalidParam(Throwable cause) {
		super(cause);
	}
	

}
