package com.revolut;

import com.revolut.model.TransactionResult;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertEquals;

public class TransactionRestServiceTest {
    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() {
        server.stop();
    }

    /**
     * Test to see transaction is successful.
     */
    @Test
    public void testTransfer() {
        TransactionResult transferResponseMsg = target.path("transaction/transfer")
                .queryParam("recipientAccountNumber", 0)
                .queryParam("senderAccountNumber", 1)
                .queryParam("amount", 100)
                .request().get(TransactionResult.class);
        assertEquals("transaction successful", transferResponseMsg.getMessage());
    }

    /**
     * Test to see transaction failed.
     */
    @Test
    public void testTransferFailed() {
        TransactionResult transferResponseMsg = target.path("transaction/transfer")
                .queryParam("recipientAccountNumber", 10)
                .queryParam("senderAccountNumber", 11)
                .queryParam("amount", 100)
                .request().get(TransactionResult.class);
        assertEquals("transaction failed", transferResponseMsg.getMessage());
    }
}
