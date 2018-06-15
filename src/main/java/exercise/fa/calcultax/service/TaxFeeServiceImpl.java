package exercise.fa.calcultax.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import exercise.fa.calcultax.exceptions.TaxServiceException;
import exercise.fa.calcultax.exceptions.TaxServiceFeeNotFoundException;
import exercise.fa.calcultax.model.Category;
import exercise.fa.calcultax.model.CategoryEnum;

public class TaxFeeServiceImpl implements TaxFeeService {
	private static final BigDecimal IMPORTE_TAX = new BigDecimal(5.0);
	private static Map<CategoryEnum, BigDecimal> mapCategoryTaxRate = new HashMap<CategoryEnum, BigDecimal>();
	
	private static final Logger logger = Logger.getLogger(TaxFeeServiceImpl.class.getName());
	static{
		mapCategoryTaxRate.put(CategoryEnum.BOOK, BigDecimal.ZERO);
		mapCategoryTaxRate.put(CategoryEnum.FOOD, BigDecimal.ZERO);
		mapCategoryTaxRate.put(CategoryEnum.MEDICAMENT, BigDecimal.ZERO);
		mapCategoryTaxRate.put(CategoryEnum.OTHER, new BigDecimal(10.0));
	}
	public BigDecimal getCategoryGeneralTaxRate(Category category) throws TaxServiceException {
		logger.info("get general tax rate for "+category );
		BigDecimal taxPercent = mapCategoryTaxRate.get(category.getCategory());
		if(taxPercent == null) throw new TaxServiceFeeNotFoundException("Tax fee can not be found for the category");

		logger.info("general tax rate:"+taxPercent);
		return taxPercent;
	}

	public BigDecimal getCategoryImportTaxRate(Category category)
			throws TaxServiceException {
		logger.info("get import tax rate for "+category );
		BigDecimal rate = null;
		if(category.isImported())
		{
			rate = IMPORTE_TAX;
		}
		else 
			rate = BigDecimal.ZERO;
		logger.info(" import tax rate :"+rate );
		return rate;
	}

}
