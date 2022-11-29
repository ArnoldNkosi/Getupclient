package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;

public class OccupationDetailsDto implements Serializable {


    private String occupationCode;
    private String occupation;
    private String occupationDisplayText;

    public String getOccupationCode() {
        return occupationCode;
    }

    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getOccupationDisplayText() {
        return occupationDisplayText;
    }

    public void setOccupationDisplayText(String occupationDisplayText) {
        this.occupationDisplayText = occupationDisplayText;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "OccupationDetailsDto{" +
                "occupationCode='" + occupationCode + '\'' +
                ", occupation='" + occupation + '\'' +
                ", occupationDisplayText='" + occupationDisplayText + '\'' +
                '}';
    }
}
