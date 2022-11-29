package za.co.metropolitan.getup.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person implements Serializable {

    private String firstName;
    private String lastName;
    private String initials;
    private String email;
    private String cellphone;
    private String idNumber;
    private int age;
    private String dateOfBirth;
    private String gender;
    private Double income;
    private Boolean medicalAid;
    private String maritalStatus;
    private String industryOfEmployment;
    private String province;
    private String educationLevel;
    private Double premium;
    private Double coverAmount;
    private Double previousCoverAmount;
    private String relation;
    private String uniqueIdentifier;


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

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Boolean getMedicalAid() {
        return medicalAid;
    }

    public void setMedicalAid(Boolean medicalAid) {
        this.medicalAid = medicalAid;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getIndustryOfEmployment() {
        return industryOfEmployment;
    }

    public void setIndustryOfEmployment(String industryOfEmployment) {
        this.industryOfEmployment = industryOfEmployment;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }

    public Double getCoverAmount() {
        return coverAmount;
    }

    public void setCoverAmount(Double coverAmount) {
        this.coverAmount = coverAmount;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public Double getPreviousCoverAmount() {
        return previousCoverAmount;
    }

    public void setPreviousCoverAmount(Double previousCoverAmount) {
        this.previousCoverAmount = previousCoverAmount;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", initials='" + initials + '\'' +
                ", email='" + email + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", age=" + age +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", income=" + income +
                ", medicalAid=" + medicalAid +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", industryOfEmployment='" + industryOfEmployment + '\'' +
                ", province='" + province + '\'' +
                ", educationLevel='" + educationLevel + '\'' +
                ", premium=" + premium +
                ", coverAmount=" + coverAmount +
                ", previousCoverAmount=" + previousCoverAmount +
                ", relation='" + relation + '\'' +
                ", uniqueIdentifier='" + uniqueIdentifier + '\'' +
                '}';
    }
}


