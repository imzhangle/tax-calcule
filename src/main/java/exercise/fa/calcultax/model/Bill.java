package exercise.fa.calcultax.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import exercise.fa.calcultax.exceptions.TaxServiceInvalidParam;

/**
 * 
 * @author ZHANG Le
 *
 */
public class Bill implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** The list of order with TTC*/
	private List<OrderTaxIncluded> ordersTaxIncluded = new ArrayList<OrderTaxIncluded>();
	
	/**tax amount */
	private BigDecimal taxAmount;
	
	/** ttc total.*/
	private BigDecimal totalAmount;
	
	public List<OrderTaxIncluded> getOrdersTaxIncluded() {
		return ordersTaxIncluded;
	}
	public void setOrdersTaxIncluded(List<OrderTaxIncluded> ordersTaxIncluded) {
		this.ordersTaxIncluded = ordersTaxIncluded;
	}
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public void validate() throws TaxServiceInvalidParam
	{
		
		for (OrderTaxIncluded orderTaxIncluded : ordersTaxIncluded) {
			orderTaxIncluded.validate();
		}
		if(this.taxAmount== null || this.taxAmount.compareTo(BigDecimal.ZERO) <= 0)
		{
			throw new TaxServiceInvalidParam("taxAmount should be >=0");
		}		
		if(this.totalAmount== null || this.totalAmount.compareTo(BigDecimal.ZERO) <= 0)
		{
			throw new TaxServiceInvalidParam("totalAmount should be >=0");
		}		
		
	}
	@Override
	public String toString() {
		return "Bill [ordersTaxIncluded=" + ordersTaxIncluded + ", taxAmount="
				+ taxAmount + ", totalAmount=" + totalAmount + "]";
	}
	
	
	
}
