package exercise.fa.calcultax.service;

import exercise.fa.calcultax.model.Order;
import exercise.fa.calcultax.model.Product;
/**
 * 
 * @author ZHANG Le
 *
 */
public interface ProductService {
	/**
	 * In the real case, we should have the Entity Relation: Order, Product and Category. Cardinality: Order->Product: n->1 and Product->Category: n->1  
	 * @param order: for each order item in the panier, we search in the database for his product information. In this example, I tricked it with copying the order information.   
	 * @return
	 */
	public Product searchProductInfo(Order order);
}
