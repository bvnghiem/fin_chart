package com.nghiem.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.nghiem.messenger.model.Message;

public class MessageService {

    static List<Message> messages = new ArrayList<Message>();

    public MessageService() {
    }

    public List<Message> getAllMessages() {
        return messages;
    }

    public void addMessages(Message msg) {
        messages.add(msg);
    }

    public Message updateMessage(Message msg) {
        Message message = getMessage(msg.getId());
        message.setMessage(msg.getMessage());
        int i = messages.indexOf(message);
        messages.set(i, message);
        return message;
    }

    public Message getMessage(long id) {
        Optional<Message> result = messages.parallelStream().filter((msg) -> msg.getId() == id).findFirst();
        return result.orElse(null);
    }

    public Message deleteMessage(long id) {
        Optional<Message> message = messages.parallelStream().filter((msg) -> msg.getId() == id).findAny();
        if (message.isPresent()) {
            Message msg = message.get();
            messages.remove(msg);
            return msg;
        }
        return null;
    }
}
