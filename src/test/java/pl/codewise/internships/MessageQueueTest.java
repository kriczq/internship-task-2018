package pl.codewise.internships;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class MessageQueueTest {
    @Test
    public void numberOfErrorMessagesTest() {
        MessageComponent component = new MessageComponent();

        ExecutorService executor = Executors.newFixedThreadPool(4);

        IntStream.range(0, 205).forEach(i -> {
            executor.submit(() -> component.add(new Message("ua", i)));
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        assertEquals(99, component.numberOfErrorMessages());
    }

    @Test
    public void snapshotSizeTest() {
        MessageComponent component = new MessageComponent();

        ExecutorService executor = Executors.newFixedThreadPool(4);

        IntStream.range(0, 1000).forEach(i -> {
            executor.submit(() -> component.add(new Message("ua", i)));
        });

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);

        Snapshot snap = component.snapshot();

        assertEquals(100, snap.getMessages().size());
    }
}