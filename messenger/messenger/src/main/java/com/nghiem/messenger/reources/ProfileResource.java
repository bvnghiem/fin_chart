package com.nghiem.messenger.reources;

import java.util.List;

import com.nghiem.messenger.model.Profile;
import com.nghiem.messenger.service.ProfileService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {

    private ProfileService profileService = new ProfileService();
    
    @GET
    public List<Profile> getAllProfiles() {
        return profileService.getAllProfiles();
    }
    
    @GET
    @Path("/{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName) {
        return profileService.getProfile(profileName);
    }
    
    @POST
    public Profile addProfile(Profile profile) {
        profile.setId(profileService.getAllProfiles().size() + 1);
        return profileService.addProfile(profile);
    }
    
    @PUT
    @Path("/{profileName}")
    public Profile updateProfile(@PathParam("profileName") String profileName, Profile profile) {
        profile.setName(profileName);
        return profileService.updateProfile(profile);
    }

    @DELETE
    @Path("/{profileName}")
    public Profile deleteProfile(@PathParam("profileName") String profileName) {
        Profile deleteProfile = profileService.deleteProfile(profileName);
        if (deleteProfile == null) {
            throw new NotFoundException("Profile not found");
        }
        return deleteProfile;
    }
}
