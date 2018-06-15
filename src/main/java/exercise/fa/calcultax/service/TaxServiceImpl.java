package exercise.fa.calcultax.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import exercise.fa.calcultax.exceptions.TaxServiceException;
import exercise.fa.calcultax.model.Bill;
import exercise.fa.calcultax.model.Category;
import exercise.fa.calcultax.model.Order;
import exercise.fa.calcultax.model.OrderTaxIncluded;
import exercise.fa.calcultax.model.Panier;
import exercise.fa.calcultax.model.Product;

public class TaxServiceImpl implements TaxService {

	private CategoryService categoryService;
	private ProductService productService;
	private TaxFeeService taxFeeService;
	private static final Logger logger = Logger.getLogger(TaxServiceImpl.class.getName());
	public TaxServiceImpl() {
	
	}
	public TaxServiceImpl(CategoryService categoryService,
			ProductService productService, TaxFeeService taxFeeService) {
		super();
		this.categoryService = categoryService;
		this.productService = productService;
		this.taxFeeService = taxFeeService;
	}
	public Bill calculBill(final Panier panier) throws TaxServiceException {
		logger.info("validating orders");
		validateOrders(panier);
		logger.info("calcul tax from panier:"+panier);
		Bill bill = new Bill();
		BigDecimal totalTax = BigDecimal.ZERO;
		BigDecimal totalTtc = BigDecimal.ZERO;
		for (Order order : panier.getOrders()) {
			Product product = productService.searchProductInfo(order);
			Category category = categoryService.getCategory(product);	
			
			
			
			BigDecimal tax = taxByOrder(order, category);
			BigDecimal priceTtc =  order.getPriceHt().add(tax);
			
			priceTtc =  priceTtc.multiply(new BigDecimal(order.getQuantity()));
			tax = tax.multiply(new BigDecimal(order.getQuantity()));
			

			totalTtc = totalTtc.add(priceTtc);
			totalTax = totalTax.add(tax);
			
			OrderTaxIncluded orderTaxIncluded = new OrderTaxIncluded(order);
			orderTaxIncluded.setPriceTtc(priceTtc);
			
			bill.getOrdersTaxIncluded().add(orderTaxIncluded);
		}
		bill.setTaxAmount(totalTax);
		bill.setTotalAmount(totalTtc);
		
		logger.info("calcul tax END. bill:"+bill);
		
		return bill;
	}
	private BigDecimal calculGeneralTax(Order order, Category category) throws TaxServiceException
	{
		
		BigDecimal categoryGeneralTax = taxFeeService.getCategoryGeneralTaxRate(category);
		return calculTax(order, categoryGeneralTax);

	}
	private BigDecimal calculImportTax(Order order, Category category)  throws TaxServiceException
	{
		BigDecimal categoryImportTax = taxFeeService.getCategoryImportTaxRate(category);
		return calculTax(order, categoryImportTax);
	}
	private BigDecimal calculTax(Order order, BigDecimal categoryTax)
	{
		logger.fine("categorytax: "+categoryTax);
		BigDecimal taxCalcule = order.getPriceHt().multiply(categoryTax).divide(new BigDecimal(100));
		logger.fine("taxCalcule: "+taxCalcule);
		BigDecimal taxImputee = taxImputee(taxCalcule);
		logger.fine("taximputee:"+taxImputee);
		return taxImputee;
	}
	private BigDecimal taxByOrder(Order order, Category category)  throws TaxServiceException
	{


		BigDecimal generalTax = calculGeneralTax(order, category);
		logger.fine("generalTax:"+generalTax);
		BigDecimal importeTax = calculImportTax(order, category);
		logger.fine("importeTax:"+importeTax);
		return generalTax.add(importeTax).setScale(2, RoundingMode.HALF_UP);
		

	}
	private  void validateOrders(final Panier panier) throws TaxServiceException
	{
		
		for (Order order : panier.getOrders()) {
			if(order!=null) order.validate();
		}
	}
	public static BigDecimal taxImputee(BigDecimal tax)
	{
		return round(tax, new BigDecimal(0.05), RoundingMode.CEILING).setScale(2, RoundingMode.HALF_UP);
	}
	
	public static BigDecimal round(BigDecimal value, BigDecimal increment,
            RoundingMode roundingMode) {
		if (increment.signum() == 0) {
			// 0 increment does not make much sense, but prevent division by 0
			return value;
		} else {
			BigDecimal divided = value.divide(increment, 0, roundingMode);
			BigDecimal result = divided.multiply(increment);
			return result;
		}
	}

}
