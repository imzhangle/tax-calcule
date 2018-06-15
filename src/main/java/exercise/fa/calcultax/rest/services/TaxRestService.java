package exercise.fa.calcultax.rest.services;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import exercise.fa.calcultax.model.Panier;


@Path("/tax")
public class TaxRestService {

	
	private static final Logger logger = Logger.getLogger(TaxRestService.class.getName());
	
    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createProduct(Panier panier) throws WebApplicationException {

        logger.info("panier:"+panier);

        

        return Response.status(Response.Status.OK).build();
    }
}
