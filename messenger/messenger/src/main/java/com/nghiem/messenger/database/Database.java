package com.nghiem.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.nghiem.messenger.model.Message;
import com.nghiem.messenger.model.Profile;

public class Database {

    public static Map<Long, Message> messages = new HashMap<Long, Message>();
    public static Map<String, Profile> profiles = new HashMap<String, Profile>();

}
