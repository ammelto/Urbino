package io.seamoss.urbino.data.models;

/**
 * Created by Alexander Melton on 2/16/2017.
 */

public class User {
    private String firstName;
    private String lastName;
    private final int UUID;

    public User(int Uuid){
        this.UUID = Uuid;
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
}
