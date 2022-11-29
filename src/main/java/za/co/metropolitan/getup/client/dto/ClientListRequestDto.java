package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;

public class ClientListRequestDto implements Serializable {

    private String name;
    private String surname;
    private String initials;
    private String idNumber;

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

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return "ClientListRequestDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", initials='" + initials + '\'' +
                ", idNumber='" + idNumber + '\'' +
                '}';
    }
}
