package za.co.metropolitan.getup.client.dto;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

public class PersonClientDto implements Serializable {

    private String getupClientNumber;

    private String idNumber;

    private String idType;

    private Date dateOfBirth;

    private String firstName;

    private String surname;

    private String initials;

    private String title;

    private String gender;

    private String maritalStatus;

    private String ethnicity;

    private String educationLevel;

    private String userSignon;


    public String getGetupClientNumber() {
        return getupClientNumber;
    }

    public void setGetupClientNumber(String getupClientNumber) {
        this.getupClientNumber = getupClientNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getUserSignon() {
        return userSignon;
    }

    public void setUserSignon(String userSignon) {
        this.userSignon = userSignon;
    }

    @Override
    public String toString() {
        return "PersonClientDto{" +
                "getupClientNumber='" + getupClientNumber + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", idType='" + idType + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", initials='" + initials + '\'' +
                ", title='" + title + '\'' +
                ", gender='" + gender + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", ethnicity='" + ethnicity + '\'' +
                ", educationLevel='" + educationLevel + '\'' +
                ", userSignon='" + userSignon + '\'' +
                '}';
    }
}
