package exercise.fa.calcultax.service;

import java.math.BigDecimal;

import exercise.fa.calcultax.exceptions.TaxServiceException;
import exercise.fa.calcultax.model.Category;
/**
 * 
 * @author ZHANG Le
 *
 */
public interface TaxFeeService {
	/**
	 * 
	 * @param category 
	 * @return according to the category info, we get different tax rates in percentage.
	 * @throws TaxServiceException
	 */
	public BigDecimal getCategoryGeneralTaxRate(Category category) throws TaxServiceException;
	public BigDecimal getCategoryImportTaxRate(Category category) throws TaxServiceException;
}
