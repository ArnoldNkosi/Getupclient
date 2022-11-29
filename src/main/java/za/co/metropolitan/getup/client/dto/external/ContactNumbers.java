package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ContactNumbers implements Serializable {
    @JsonProperty("contactType")
        private String contactType;
    @JsonProperty("number")
        private String number = null;


    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
