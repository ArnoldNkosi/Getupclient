package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;

public class LoginResponse implements Serializable {

    private Boolean faceAndIDMatch;
    private String name;
    private String surname;
    private Boolean loginSucceeded;
    private Boolean alreadyRegistered;
    private Boolean isClient;


    public Boolean getFaceAndIDMatch() {
        return faceAndIDMatch;
    }

    public void setFaceAndIDMatch(Boolean faceAndIDMatch) {
        this.faceAndIDMatch = faceAndIDMatch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getLoginSucceeded() {
        return loginSucceeded;
    }

    public void setLoginSucceeded(Boolean loginSucceeded) {
        this.loginSucceeded = loginSucceeded;
    }

    public Boolean getAlreadyRegistered() {
        return alreadyRegistered;
    }

    public void setAlreadyRegistered(Boolean alreadyRegistered) {
        this.alreadyRegistered = alreadyRegistered;
    }

    public Boolean getClient() {
        return isClient;
    }

    public void setClient(Boolean client) {
        isClient = client;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "faceAndIDMatch=" + faceAndIDMatch +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", loginSucceeded=" + loginSucceeded +
                ", alreadyRegistered=" + alreadyRegistered +
                ", isClient=" + isClient +
                '}';
    }
}
