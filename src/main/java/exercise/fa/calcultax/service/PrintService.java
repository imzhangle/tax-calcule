package exercise.fa.calcultax.service;

import exercise.fa.calcultax.exceptions.TaxServiceException;
import exercise.fa.calcultax.model.Bill;
/**
 * 
 * @author ZHANG Le
 *
 */
public interface PrintService {
	/**
	 * 
	 * @param bill is the whole bill calculated according to the panier.  
	 * @return the bill content printed.
	 * @throws TaxServiceException
	 */
	public String printBill(Bill bill) throws TaxServiceException;
}
