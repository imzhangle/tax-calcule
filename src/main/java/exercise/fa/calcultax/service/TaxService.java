package exercise.fa.calcultax.service;

import exercise.fa.calcultax.exceptions.TaxServiceException;
import exercise.fa.calcultax.model.Bill;
import exercise.fa.calcultax.model.Panier;
/**
 * 
 * @author ZHANG Le
 *
 */
public interface TaxService {
	/**
	 * 
	 * @param panier: the list of product ordered:  
	 * @return the bill to be printed.
	 * @throws TaxServiceException
	 */
	public Bill calculBill(Panier panier) throws TaxServiceException;
}
