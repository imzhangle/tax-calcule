package exercise.fa.calcultax.service;

import javax.ws.rs.ext.Provider;

import exercise.fa.calcultax.model.Order;
import exercise.fa.calcultax.model.Product;

@Provider
public class ProductServiceImpl implements ProductService {

	
	public Product searchProductInfo(Order order) {
		//FIXME: there is not yet the product base, we just package the order into product.
		Product product = new Product();
		product.setName(order.getName());
		product.setId(order.getId());
		return product;
	}

}
