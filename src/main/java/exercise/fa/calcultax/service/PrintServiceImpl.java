package exercise.fa.calcultax.service;

import java.util.List;

import javax.ws.rs.ext.Provider;

import exercise.fa.calcultax.exceptions.TaxServiceException;
import exercise.fa.calcultax.model.Bill;
import exercise.fa.calcultax.model.OrderTaxIncluded;

@Provider
public class PrintServiceImpl implements PrintService {

	public String printBill(Bill bill) throws TaxServiceException {
		bill.validate();
		StringBuffer billBuffer = new StringBuffer();
		List<OrderTaxIncluded> ordersTaxIncluded = bill.getOrdersTaxIncluded();
		for (OrderTaxIncluded orderTaxIncluded : ordersTaxIncluded) {
			billBuffer.append(orderTaxIncluded.getQuantity() + "\t" + orderTaxIncluded.getName() + "\t:\t" +orderTaxIncluded.getPriceTtc() );
			billBuffer.append("\n");
			
		}
		billBuffer.append("Montant des taxes\t:\t"+bill.getTaxAmount());
		billBuffer.append("\n");
		billBuffer.append("Total\t:\t"+bill.getTotalAmount());
		return billBuffer.toString();
	}

}
