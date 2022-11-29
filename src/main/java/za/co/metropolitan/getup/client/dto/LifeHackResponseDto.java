package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;
import java.util.Date;

public class LifeHackResponseDto implements Serializable {

    private String lifeHack;
    private String value;
    private String valueType;
    private String optedInDate;
    private String uploadedDate;


    public String getLifeHack() {
        return lifeHack;
    }

    public void setLifeHack(String lifeHack) {
        this.lifeHack = lifeHack;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public String getOptedInDate() {
        return optedInDate;
    }

    public void setOptedInDate(String optedInDate) {
        this.optedInDate = optedInDate;
    }

    public String getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(String uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "LifeHackResponseDto{" +
                "lifeHack='" + lifeHack + '\'' +
                ", value='" + value + '\'' +
                ", valueType='" + valueType + '\'' +
                ", optedInDate=" + optedInDate +
                ", uploadedDate=" + uploadedDate +
                '}';
    }
}
