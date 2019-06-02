package com.revolut.restController;

import com.revolut.cache.InMemoryCache;
import com.revolut.controller.Transfer;
import com.revolut.model.Account;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "transfer" path)
 */
@Path("transfer")
public class TransferRestService {

    public TransferRestService() {
        Account account = new Account();
        account.setAccountNumber();
    }

    /**
     * Method handling transfer requests.
     *
     * @return String that returns if transaction is successful or not.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getClient(Integer recipientAccountNumber, Integer senderAccountNumber, Integer amount){
        Transfer transfer = new Transfer();
        return transfer.start(recipientAccountNumber, senderAccountNumber, amount);
    }
}
