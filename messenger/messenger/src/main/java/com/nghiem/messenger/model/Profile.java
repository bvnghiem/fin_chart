package com.nghiem.messenger.model;

public class Profile {

    public Profile() {
    }
    
    public Profile(long id, String name, String firstName, String lastName) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    private long id;
    private String name;
    private String firstName;
    private String lastName;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Profile [id=" + id + ", name=" + name + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
