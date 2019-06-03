package com.revolut.restController;

import com.revolut.controller.TransactionController;
import com.revolut.model.TransactionResult;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "transfer" path)
 */
@Path("transaction")
public class TransactionRestService {

    /**
     * Method handling transfer requests.
     *
     * @return String that returns if transaction is successful or not.
     */
    @GET
    @Path("transfer")
    @Produces(MediaType.APPLICATION_JSON)
    public TransactionResult transfer(@QueryParam("recipientAccountNumber") Integer recipientAccountNumber,
                                      @QueryParam("senderAccountNumber") Integer senderAccountNumber,
                                      @QueryParam("amount") Integer amount){
        TransactionController transactionController = new TransactionController();
        return transactionController.transfer(recipientAccountNumber, senderAccountNumber, amount);
    }
}
