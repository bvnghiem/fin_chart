package com.nghiem.messenger.service;

import static com.nghiem.messenger.database.Database.profiles;

import java.util.ArrayList;
import java.util.List;

import com.nghiem.messenger.model.Profile;

public class ProfileService {


    public ProfileService() {
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile addProfile(Profile profile) {
        profiles.put(profile.getName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        Profile prof = getProfile(profile.getName());
        if(profile.getFirstName() != null) {
            prof.setFirstName(profile.getFirstName());
        }
        if(profile.getLastName() != null) {
            prof.setLastName(profile.getLastName());
        }
        profiles.put(prof.getName(), prof);
        return prof;
    }

    public Profile getProfile(String name) {
        return profiles.get(name);
    }

    public Profile deleteProfile(String name) {
        return profiles.remove(name);
    }
}
