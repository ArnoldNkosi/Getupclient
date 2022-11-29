package za.co.metropolitan.getup.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Spouse extends Person implements Serializable {

    private Boolean livingWithMainMember;


    public Boolean getLivingWithMainMember() {
        return livingWithMainMember;
    }

    public void setLivingWithMainMember(Boolean livingWithMainMember) {
        this.livingWithMainMember = livingWithMainMember;
    }

    @Override
    public String toString() {
        return "Partner{" +
                "livingWithMainMember=" + livingWithMainMember +
                '}';
    }
}

