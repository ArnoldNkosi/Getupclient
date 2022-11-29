package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;

public class EducationDetailsDto implements Serializable {

    private String nqfCode;
    private String educationlevel;


    public String getNqfCode() {
        return nqfCode;
    }

    public void setNqfCode(String nqfCode) {
        this.nqfCode = nqfCode;
    }

    public String getEducationlevel() {
        return educationlevel;
    }

    public void setEducationlevel(String educationlevel) {
        this.educationlevel = educationlevel;
    }

    @Override
    public String toString() {
        return "EducationDetailsDto{" +
                "nqfCode='" + nqfCode + '\'' +
                ", educationlevel='" + educationlevel + '\'' +
                '}';
    }
}
