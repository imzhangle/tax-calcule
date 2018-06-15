package exercise.fa.calcultax.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import exercise.fa.calcultax.exceptions.TaxServiceInvalidParam;

/**
 * @author X159410
 *
 */
/**
 * @author X159410
 *
 */
public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private Integer quantity;
	private BigDecimal priceHt = BigDecimal.ZERO;
	
	public Order() {
	
	}
	public Order(Order order) {
		this.id = order.id;
		this.name = order.name;
		this.quantity = order.quantity;
		setPriceHt(order.priceHt);
		
	}
		
	public Order(String id, String name, Integer quantity, BigDecimal priceHt) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		setPriceHt(priceHt);
		

	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPriceHt() {
		return priceHt;
	}
	public void setPriceHt(BigDecimal priceHt) {
		this.priceHt = priceHt;
		if(this.priceHt != null)
			this.priceHt = priceHt.setScale(2, RoundingMode.HALF_UP);
		
	}
	
	public void validate() throws TaxServiceInvalidParam{
		if(this.name == null || this.name.trim().equals(""))
		{
			throw new TaxServiceInvalidParam("product name is empty");
		}
		if(this.quantity== null || this.quantity <= 0)
		{
			throw new TaxServiceInvalidParam("quantity should be >=0");
		}
		if(this.priceHt== null || this.priceHt.compareTo(BigDecimal.ZERO) <= 0)
		{
			throw new TaxServiceInvalidParam("price ht should be >=0");
		}		
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", quantity=" + quantity
				+ ", priceHt=" + priceHt + "]";
	}
	
	

}	

