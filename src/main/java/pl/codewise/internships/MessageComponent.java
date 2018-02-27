package pl.codewise.internships;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MessageComponent implements MessageQueue {
    private final Queue<MessageBox> messages = new ConcurrentLinkedQueue<MessageBox>();
    private final static int capacity = 100;
    private final static double timediff = 300000000000.0;

    @Override
    public void add(Message message) {
        synchronized (messages) {
            if (messages.size() > capacity - 1){
                messages.remove();
            }

            messages.add(new MessageBox(message));
        }
    }


    @Override
    public Snapshot snapshot() {
        long time = System.nanoTime();
        Snapshot snap = new Snapshot();

        synchronized (messages) {
            for (Iterator it = messages.iterator(); it.hasNext();){
                MessageBox mb = (MessageBox) it.next();

                if (time - mb.getTimestamp() <= timediff) {
                    snap.addMessage(mb.getMessage());
                }
            }
        }

        return snap;
    }

    @Override
    public long numberOfErrorMessages() {
        long time = System.nanoTime();
        long i = 0;

        synchronized (messages) {
            for (Iterator it = messages.iterator(); it.hasNext();){
                MessageBox mb = (MessageBox) it.next();

                if (time - mb.getTimestamp() <= timediff && mb.getMessage().getErrorCode() != 200)
                    i++;
            }
        }

        return i;
    }
}
