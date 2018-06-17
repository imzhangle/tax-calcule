package exercise.fa.calcultax.service;

import javax.ws.rs.ext.Provider;

import exercise.fa.calcultax.model.Category;
import exercise.fa.calcultax.model.CategoryEnum;
import exercise.fa.calcultax.model.Product;
import exercise.fa.calcultax.util.StringNormalizer;

@Provider
public class CategoryServiceImpl implements CategoryService {

	private static final String KEYWORDS_IMPORTE = "importe";


	public Category getCategory(Product product) {
		//FIXME: for the real case, we should search category by the product id: product.getId();
		Category category = new Category();
		String productName = StringNormalizer.stripAccents(product.getName());
		
		if(productName.contains("livre"))
		{
			category.setCategory(CategoryEnum.BOOK);
		}
		else if(productName.contains("chocolat"))
		{
			category.setCategory(CategoryEnum.FOOD);
		}
		else if(productName.contains("pilule"))
		{
			category.setCategory(CategoryEnum.MEDICAMENT);
		}
		else
			category.setCategory(CategoryEnum.OTHER);

		if(StringNormalizer.stripAccents(productName).toLowerCase().contains(KEYWORDS_IMPORTE))
		{
			category.setImported(true);
		}
		else 
			category.setImported(false);
		return category;
	}


}
