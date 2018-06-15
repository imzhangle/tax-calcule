package exercise.fa.calcultax.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import exercise.fa.calcultax.exceptions.TaxServiceInvalidParam;

public class OrderTaxIncluded extends Order{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal priceTtc;
	public OrderTaxIncluded(Order order) {
		super(order);
		
	}

	public BigDecimal getPriceTtc() {
		if(this.priceTtc != null)
			this.priceTtc.setScale(2, RoundingMode.HALF_UP);
		return priceTtc;
	}

	public void setPriceTtc(BigDecimal priceTtc) {
		this.priceTtc = priceTtc;
	}
	@Override
	public void validate() throws TaxServiceInvalidParam {

		super.validate();
		if(this.priceTtc== null || this.priceTtc.compareTo(BigDecimal.ZERO) <= 0)
		{
			throw new TaxServiceInvalidParam("price ttc should be >=0");
		}		

	}

	@Override
	public String toString() {
		return "OrderTaxIncluded [priceTtc=" + priceTtc + " " + super.toString() + "]";
	}
	
	
}
