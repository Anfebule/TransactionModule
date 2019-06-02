package com.revolut;

import com.revolut.cache.InMemoryCache;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InMemoryCacheTest {

    @Test
    private void addRemoveObjects() {

        // Test with timeToLive = 200 seconds
        // timerInterval = 500 seconds
        // maxItems = 6
        InMemoryCache<String, String> cache = new InMemoryCache<>(200, 500, 6);

        cache.put("eBay", "eBay");
        cache.put("Paypal", "Paypal");
        cache.put("Google", "Google");
        cache.put("Microsoft", "Microsoft");
        cache.put("IBM", "IBM");
        cache.put("Facebook", "Facebook");

        assertEquals(cache.size(), 6);

        cache.remove("IBM");
        assertEquals(cache.size(), 5);

        cache.put("Twitter", "Twitter");
        cache.put("SAP", "SAP");
        assertEquals(cache.size(), 6);

    }

    @Test
    private void expiredCacheObjects() throws InterruptedException {

        // Test with timeToLive = 1 second
        // timerInterval = 1 second
        // maxItems = 10
        InMemoryCache<String, String> cache = new InMemoryCache<>(1, 1, 10);

        cache.put("eBay", "eBay");
        cache.put("Paypal", "Paypal");
        // Adding 3 seconds sleep.. Both above objects will be removed from
        // Cache because of timeToLiveInSeconds value
        Thread.sleep(3000);

        assertEquals(cache.size(), 0);

    }

    @Test
    private void objectsCleanupTime() throws InterruptedException {
        int size = 500000;

        // Test with timeToLiveInSeconds = 100 seconds
        // timerIntervalInSeconds = 100 seconds
        // maxItems = 500000

        InMemoryCache<String, String> cache = new InMemoryCache<>(100, 100, 500000);

        for (int i = 0; i < size; i++) {
            String value = Integer.toString(i);
            cache.put(value, value);
        }
        assertEquals(cache.size(), 6);

        Thread.sleep(200);

        long start = System.currentTimeMillis();
        cache.cleanup();
        assertEquals(cache.size(), 0);
    }
}
