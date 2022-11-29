package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
            "rolePlayerId",
            "firstName",
            "surname",
            "initials",
            "idNumber",
            "gender",
            "contactNumbers"
})

public class Roleplayer implements Serializable {

    @JsonProperty("rolePlayerId")
    private String rolePlayerId ;
    @JsonProperty("firstName")
    private String firstName ;
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("initials")
    private String initials;
    @JsonProperty("idNumber")
    private String idNumber;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;
    @JsonProperty("contactNumbers")
    private List<ContactNumbers> contactNumbers = null;

    public List<ContactNumbers> getContactNumbers() {
        return contactNumbers;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setContactNumbers(List<ContactNumbers> contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public String getRolePlayerId() {
        return rolePlayerId;
    }

    public void setRolePlayerId(String rolePlayerId) {
        this.rolePlayerId = rolePlayerId;
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

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Roleplayer{" +
                "rolePlayerId='" + rolePlayerId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", initials='" + initials + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", contactNumbers=" + contactNumbers +
                '}';
    }
}
