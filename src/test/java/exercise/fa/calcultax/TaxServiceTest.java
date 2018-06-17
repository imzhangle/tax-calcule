package exercise.fa.calcultax;

import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import com.google.gson.Gson;

import exercise.fa.calcultax.exceptions.TaxServiceException;
import exercise.fa.calcultax.exceptions.TaxServiceInvalidParam;
import exercise.fa.calcultax.model.Bill;
import exercise.fa.calcultax.model.Order;
import exercise.fa.calcultax.model.OrderTaxIncluded;
import exercise.fa.calcultax.model.Panier;
import exercise.fa.calcultax.service.CategoryServiceImpl;
import exercise.fa.calcultax.service.PrintServiceImpl;
import exercise.fa.calcultax.service.ProductServiceImpl;
import exercise.fa.calcultax.service.TaxFeeServiceImpl;
import exercise.fa.calcultax.service.TaxServiceImpl;

public class TaxServiceTest {

	private TaxServiceImpl taxService = new TaxServiceImpl(new CategoryServiceImpl(), new ProductServiceImpl(), new TaxFeeServiceImpl());
	private PrintServiceImpl printService = new PrintServiceImpl();
	@Test
	public void testTaxImputee()
	{
		Assertions.assertThat(TaxServiceImpl.taxImputee(new BigDecimal(0.99))).hasToString("1.00");
		Assertions.assertThat(TaxServiceImpl.taxImputee(new BigDecimal(1.00))).hasToString("1.00");
		Assertions.assertThat(TaxServiceImpl.taxImputee(new BigDecimal(1.01))).hasToString("1.05");
		Assertions.assertThat(TaxServiceImpl.taxImputee(new BigDecimal(1.02))).hasToString("1.05");
	}
	@Test
	public void testInputPanier1() throws TaxServiceException
	{
		Panier panier = new Panier();
		panier.getOrders().add(new Order("1", "livre", 1, new BigDecimal(12.49)));
		panier.getOrders().add(new Order("2", "CD musical", 1, new BigDecimal(14.99)));
		panier.getOrders().add(new Order("3", "barre de chocolat", 1, new BigDecimal(0.85)));
		Gson gson = new Gson();
		String json = gson.toJson(panier);
		System.out.println("input 1:"+json);
		
		Bill bill = taxService.calculBill(panier);
		
		assertOutputOrders1(bill.getOrdersTaxIncluded());
		Assertions.assertThat(bill.getTaxAmount()).hasToString("1.50");
		Assertions.assertThat(bill.getTotalAmount()).hasToString("29.83");
		
		System.out.println(printService.printBill(bill));

	}
	private static void assertOutputOrders1(List<OrderTaxIncluded> orderList)
	{
		Assertions.assertThat(orderList.get(0).getPriceTtc()).hasToString("12.49");
		Assertions.assertThat(orderList.get(1).getPriceTtc()).hasToString("16.49");
		Assertions.assertThat(orderList.get(2).getPriceTtc()).hasToString("0.85");
	}

	
	@Test
	public void testInputPanier2() throws TaxServiceException
	{
		Panier panier = new Panier();
		panier.getOrders().add(new Order("4", "boîte de chocolats importée", 1, new BigDecimal(10.00)));
		panier.getOrders().add(new Order("5", "flacon de parfum importé", 1, new BigDecimal(47.50)));
		Gson gson = new Gson();
		String json = gson.toJson(panier);
		System.out.println("input 2: "+json);		
		
		Bill bill = taxService.calculBill(panier);
		
		assertOutputOrders2(bill.getOrdersTaxIncluded());
		Assertions.assertThat(bill.getTaxAmount()).hasToString("7.65");
		Assertions.assertThat(bill.getTotalAmount()).hasToString("65.15");

		System.out.println(printService.printBill(bill));

	}
	private static void assertOutputOrders2(List<OrderTaxIncluded> orderList)
	{
		Assertions.assertThat(orderList.get(0).getPriceTtc()).hasToString("10.50");
		Assertions.assertThat(orderList.get(1).getPriceTtc()).hasToString("54.65");
	}	

	
	@Test
	public void testInputPanier3() throws TaxServiceException
	{
		Panier panier = new Panier();
		panier.getOrders().add(new Order("6", "flacon de parfum importé", 1, new BigDecimal(27.99)));
		panier.getOrders().add(new Order("7", "flacon de parfum", 1, new BigDecimal(18.99)));
		panier.getOrders().add(new Order("8", "boîte de pilules contre la migraine", 1, new BigDecimal(9.75)));
		panier.getOrders().add(new Order("9", "boîte de chocolats importés", 1, new BigDecimal(11.25)));
		
		Gson gson = new Gson();
		String json = gson.toJson(panier);
		System.out.println("input 3:"+json);		
		
		Bill bill = taxService.calculBill(panier);
		
		assertOutputOrders3(bill.getOrdersTaxIncluded());
		Assertions.assertThat(bill.getTaxAmount()).hasToString("6.70");
		Assertions.assertThat(bill.getTotalAmount()).hasToString("74.68");
		
		System.out.println(printService.printBill(bill));


	}
	private static void assertOutputOrders3(List<OrderTaxIncluded> orderList)
	{
		Assertions.assertThat(orderList.get(0).getPriceTtc()).hasToString("32.19");
		Assertions.assertThat(orderList.get(1).getPriceTtc()).hasToString("20.89");
		Assertions.assertThat(orderList.get(2).getPriceTtc()).hasToString("9.75");
		Assertions.assertThat(orderList.get(3).getPriceTtc()).hasToString("11.85");
	}	
	@Test
	public void testShouldRaiseInvalidParam1() throws TaxServiceException
	{
		Panier panier = new Panier();
		panier.getOrders().add(new Order("1", "", 1, new BigDecimal(12.49)));


		
		
		Assertions.assertThatThrownBy(() -> {taxService.calculBill(panier);}).isInstanceOf(TaxServiceInvalidParam.class).hasMessageContaining("product");


	}	
	@Test
	public void testShouldRaiseInvalidParam2() throws TaxServiceException
	{
		Panier panier = new Panier();
		panier.getOrders().add(new Order("1", "livre", 1, new BigDecimal(-1)));

		Assertions.assertThatThrownBy(() -> {taxService.calculBill(panier);}).isInstanceOf(TaxServiceInvalidParam.class).hasMessageContaining("price");


	}		
	
}
