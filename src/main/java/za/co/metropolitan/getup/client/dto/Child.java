package za.co.metropolitan.getup.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Child extends Person implements Serializable {

    private Boolean dependentOnParents;
    private Boolean disability;
    private Boolean fullTimeStudent;

    public Boolean getDependentOnParents() {
        return dependentOnParents;
    }

    public void setDependentOnParents(Boolean dependentOnParents) {
        this.dependentOnParents = dependentOnParents;
    }

    public Boolean getDisability() {
        return disability;
    }

    public void setDisability(Boolean disability) {
        this.disability = disability;
    }

    public Boolean getFullTimeStudent() {
        return fullTimeStudent;
    }

    public void setFullTimeStudent(Boolean fullTimeStudent) {
        this.fullTimeStudent = fullTimeStudent;
    }

    @Override
    public String toString() {
        return "Child{" +
                "dependentOnParents=" + dependentOnParents +
                ", disability=" + disability +
                ", fullTimeStudent=" + fullTimeStudent +
                '}';
    }
}
