package exercise.fa.calcultax.service;

import exercise.fa.calcultax.model.Category;
import exercise.fa.calcultax.model.Product;

public interface CategoryService {
	/**
	 * 
	 * @param product get the category info of a product. In a real case, category should be obtained from a database SQL/NOSQL by product id. In this example, I tricked the process by guessing the category from the product name.   
	 * @return
	 */
	public Category getCategory(Product product);
		
}
