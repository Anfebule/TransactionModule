package com.revolut;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import static org.junit.Assert.assertEquals;

public class TransferTest {
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
        String transferResponseMsg = target.path("transfer").request().get(String.class);
        assertEquals("Transaction successful", transferResponseMsg);
    }

    /**
     * Test to see transaction failed.
     */
    @Test
    public void testTransferFailed() {
        String transferResponseMsg = target.path("transfer").request().get(String.class);
        assertEquals("Transaction failed", transferResponseMsg);
    }
}
