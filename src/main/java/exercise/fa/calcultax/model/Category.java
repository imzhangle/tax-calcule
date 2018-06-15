package exercise.fa.calcultax.model;

import java.io.Serializable;

public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * categoryenum serves category types to determine if exempted. :
	 */
	private CategoryEnum category;
	private boolean isImported;
	
	public CategoryEnum getCategory() {
		return category;
	}
	public void setCategory(CategoryEnum category) {
		this.category = category;
	}
	public boolean isImported() {
		return isImported;
	}
	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}
	@Override
	public String toString() {
		return "Category [category=" + category + ", isImported=" + isImported
				+ "]";
	}
	
	
	
	

}
