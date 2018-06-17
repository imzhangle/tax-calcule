package exercise.fa.calcultax.rest.services;



import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exercise.fa.calcultax.exceptions.TaxServiceException;
import exercise.fa.calcultax.exceptions.TaxServiceInvalidParam;
import exercise.fa.calcultax.model.Bill;
import exercise.fa.calcultax.model.Panier;
import exercise.fa.calcultax.service.PrintService;
import exercise.fa.calcultax.service.TaxService;


@Path("/tax")
public class TaxRestService {

	@Inject TaxService taxService;
	@Inject PrintService printService;
	private static final Logger logger = LoggerFactory.getLogger(TaxRestService.class.getName());
	
    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("text/plain")
    public Response createProduct(Panier panier) throws WebApplicationException {

        logger.info("panier:"+panier);
        String billPrinted = "";
        try {
			Bill bill = taxService.calculBill(panier);
			billPrinted = printService.printBill(bill);
		} catch (TaxServiceInvalidParam e) {
			
			logger.error(e.getMessage(), e);
			
			throw new WebApplicationException(Status.BAD_REQUEST);
		}catch (TaxServiceException e) {
			logger.error(e.getMessage(), e);
			throw new WebApplicationException(Status.SERVICE_UNAVAILABLE);
		}
        return Response.ok(billPrinted, MediaType.TEXT_PLAIN).build();
        
    }
}
