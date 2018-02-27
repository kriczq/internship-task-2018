package pl.codewise.internships;

import java.util.ArrayList;
import java.util.List;

public class Snapshot {
    private List<Message> messages = new ArrayList<>();

    public void addMessage(Message m) {
        messages.add(m);
    }

    public List<Message> getMessages() {
        return messages;
    }
}
