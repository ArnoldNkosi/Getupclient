package za.co.metropolitan.getup.client.dto.external;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "birthDate",
        "cellphoneNo",
        "countryOfIssue",
        "email",
        "firstName",
        "idNumber",
        "lastName",
        "passportNumber",
        "postalAddress",
        "residentialAddress",
        "residentialTelephoneNo",
        "title",
        "workTelephoneNo"
})
public class ClientDetail {

    @JsonProperty("birthDate")
    private String birthDate;
    @JsonProperty("cellphoneNo")
    private String cellphoneNo;
    @JsonProperty("countryOfIssue")
    private String countryOfIssue;
    @JsonProperty("email")
    private String email;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("idNumber")
    private String idNumber;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("passportNumber")
    private String passportNumber;
    @JsonProperty("postalAddress")
    private String postalAddress;
    @JsonProperty("residentialAddress")
    private String residentialAddress;
    @JsonProperty("residentialTelephoneNo")
    private String residentialTelephoneNo;
    @JsonProperty("title")
    private String title;
    @JsonProperty("workTelephoneNo")
    private String workTelephoneNo;

    @JsonProperty("birthDate")
    public String getBirthDate() {
        return birthDate;
    }

    @JsonProperty("birthDate")
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @JsonProperty("cellphoneNo")
    public String getCellphoneNo() {
        return cellphoneNo;
    }

    @JsonProperty("cellphoneNo")
    public void setCellphoneNo(String cellphoneNo) {
        this.cellphoneNo = cellphoneNo;
    }

    @JsonProperty("countryOfIssue")
    public String getCountryOfIssue() {
        return countryOfIssue;
    }

    @JsonProperty("countryOfIssue")
    public void setCountryOfIssue(String countryOfIssue) {
        this.countryOfIssue = countryOfIssue;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("idNumber")
    public String getIdNumber() {
        return idNumber;
    }

    @JsonProperty("idNumber")
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("passportNumber")
    public String getPassportNumber() {
        return passportNumber;
    }

    @JsonProperty("passportNumber")
    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @JsonProperty("postalAddress")
    public String getPostalAddress() {
        return postalAddress;
    }

    @JsonProperty("postalAddress")
    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    @JsonProperty("residentialAddress")
    public String getResidentialAddress() {
        return residentialAddress;
    }

    @JsonProperty("residentialAddress")
    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    @JsonProperty("residentialTelephoneNo")
    public String getResidentialTelephoneNo() {
        return residentialTelephoneNo;
    }

    @JsonProperty("residentialTelephoneNo")
    public void setResidentialTelephoneNo(String residentialTelephoneNo) {
        this.residentialTelephoneNo = residentialTelephoneNo;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("workTelephoneNo")
    public String getWorkTelephoneNo() {
        return workTelephoneNo;
    }

    @JsonProperty("workTelephoneNo")
    public void setWorkTelephoneNo(String workTelephoneNo) {
        this.workTelephoneNo = workTelephoneNo;
    }

    @Override
    public String toString() {
        return "ClientDetail{" +
                "birthDate='" + birthDate + '\'' +
                ", cellphoneNo='" + cellphoneNo + '\'' +
                ", countryOfIssue='" + countryOfIssue + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                ", postalAddress='" + postalAddress + '\'' +
                ", residentialAddress='" + residentialAddress + '\'' +
                ", residentialTelephoneNo='" + residentialTelephoneNo + '\'' +
                ", title='" + title + '\'' +
                ", workTelephoneNo='" + workTelephoneNo + '\'' +
                '}';
    }
}
