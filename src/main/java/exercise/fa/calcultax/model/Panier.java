package exercise.fa.calcultax.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Panier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Order> orders = new ArrayList<Order>();

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Panier [orders=" + orders + "]";
	}
	
	
	
}
