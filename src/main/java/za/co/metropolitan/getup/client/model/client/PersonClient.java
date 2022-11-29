package za.co.metropolitan.getup.client.model.client;


import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(schema = "client", name = "tbl_person_client")
public class PersonClient {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "getup_client_number")
    private String getupClientNumber;

    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "id_type")
    private String idType;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "initials")
    private String initials;

    @Column(name = "title")
    private String title;

    @Column(name = "gender")
    private String gender;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "ethnicity")
    private String ethnicity;

    @Column(name = "education_level")
    private String educationLevel;

    @Column(name = "user_signon")
    private String userSignon;

//    @Column(name = "created_at")
//    private Timestamp createdAt;


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

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
        return "PersonClient{" +
                "id=" + id +
                ", getupClientNumber='" + getupClientNumber + '\'' +
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
