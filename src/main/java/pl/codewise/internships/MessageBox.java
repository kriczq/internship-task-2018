package pl.codewise.internships;

public class MessageBox {
    private final Message message;
    private final long timestamp;

    public MessageBox(Message m){
        this.message = m;
        this.timestamp = System.nanoTime();
    }

    public Message getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
