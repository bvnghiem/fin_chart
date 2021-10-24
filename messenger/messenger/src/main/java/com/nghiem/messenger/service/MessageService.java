package com.nghiem.messenger.service;

import static com.nghiem.messenger.database.Database.messages;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.nghiem.messenger.exception.DataNotFoundException;
import com.nghiem.messenger.model.Message;

public class MessageService {

    public MessageService() {
    }

    public List<Message> getAllMessages() {
        return new ArrayList<Message>(messages.values());
    }

    public List<Message> getAllMessagesForYear(int year) {
        return messages.values().parallelStream().filter((msg) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(msg.getCreated());
            return calendar.get(Calendar.YEAR) == year;
        }).collect(Collectors.toList());
    }

    public List<Message> getAllMessagesPaginated(int startIndex, int size) {
        return messages.values().stream()
                .sorted((m1, m2) -> Long.compare(m1.getId(), m2.getId()))
                .skip(startIndex)
                .limit(size)
                .collect(Collectors.toList());
    }

    public void addMessages(Message msg) {
        messages.put(msg.getId(), msg);
    }

    public Message updateMessage(Message msg) {
        Message message = getMessage(msg.getId());
        if(message == null) {
            return null;
        }
        if (msg.getMessage() != null) {
            message.setMessage(msg.getMessage());
        }
        if (msg.getAuthor() != null) {
            message.setAuthor(msg.getAuthor());
        }
        if (msg.getCreated() != null) {
            message.setCreated(msg.getCreated());
        }
        return message;
    }

    public Message getMessage(long id) {
        Message message = messages.get(id);
        if(message == null) {
            throw new DataNotFoundException("No message found with requested id: " + id);
        }
        return message;
    }

    public Message deleteMessage(long id) {
        return messages.remove(id);
    }
}
