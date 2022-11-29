package za.co.metropolitan.getup.client.model.knownconsumer;


import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Entity
@Table(schema = "client", name = "tbl_known_consumer")
public class KnownConsumerModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name="id_number")
    private String idNumber;

    @Column(name= "known_consumer_number")
    private String knownConsumerNumber;

    @Column(name="id_type")
    private String id_type;

    @Column(name="date_of_birth")
    private Date date_of_birth;

    @Column(name="first_name")
    private String firstName;

    @Column(name="surname")
    private String surname;

    @Column(name="initials")
    private String initials;

    @Column(name="title")
    private String title;

    @Column(name="gender")
    private String gender;

    @Column(name="marital_status")
    private String marital_status;

    @Column(name="ethnicity")
    private String ethnicity;

    @Column(name="education_level")
    private String education_level;

    @Column(name="getup_client_number")
    private String getup_client_number;

    @Column(name="originating_source")
    private String originating_source;

    @Column(name="unsubscribed_at")
    private Timestamp unsubscribed_at;

    @Column(name="created_at")
    private Timestamp created_at;

    @Column(name="updated_at")
    private Timestamp updated_at;


    @OneToMany(targetEntity = KnownConsumerContact.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "known_consumer_id",referencedColumnName = "id")
    private List<KnownConsumerContact> contact;

    @OneToMany(targetEntity = KnownConsumerConsent.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "known_consumer_id",referencedColumnName = "id")
    private List<KnownConsumerConsent> consent;

    public List<KnownConsumerContact> getContact() {
        return contact;
    }

    public void setContact(List<KnownConsumerContact> contact) {
        this.contact = contact;
    }

    public List<KnownConsumerConsent> getConsent() {
        return consent;
    }

    public void setConsent(List<KnownConsumerConsent> consent) {
        this.consent = consent;
    }

    public KnownConsumerModel() {
    }

    public KnownConsumerModel(String knownConsumerNumber,String idNumber, String id_type, Date date_of_birth, String firstName, String surname, String initials, String title, String gender, String marital_status, String ethnicity, String education_level, String getup_client_number, String originating_source, Timestamp unsubscribed_at, Timestamp created_at, Timestamp updated_at) {
        this.idNumber = idNumber;
        this.knownConsumerNumber = knownConsumerNumber;
        this.id_type = id_type;
        this.date_of_birth = date_of_birth;
        this.firstName = firstName;
        this.surname = surname;
        this.initials = initials;
        this.title = title;
        this.gender = gender;
        this.marital_status = marital_status;
        this.ethnicity = ethnicity;
        this.education_level = education_level;
        this.getup_client_number = getup_client_number;
        this.originating_source = originating_source;
        this.unsubscribed_at = unsubscribed_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public KnownConsumerModel(BigInteger id, String Known_consumer_number, String idNumber, String id_type, Date date_of_birth, String firstName, String surname, String initials, String title, String gender, String marital_status, String ethnicity, String education_level, String getup_client_number, String originating_source, Timestamp unsubscribed_at, Timestamp created_at, Timestamp updated_at) {
        this.id = id;
        this.knownConsumerNumber=Known_consumer_number;
        this.idNumber = idNumber;
        this.id_type = id_type;
        this.date_of_birth = date_of_birth;
        this.firstName = firstName;
        this.surname = surname;
        this.initials = initials;
        this.title = title;
        this.gender = gender;
        this.marital_status = marital_status;
        this.ethnicity = ethnicity;
        this.education_level = education_level;
        this.getup_client_number = getup_client_number;
        this.originating_source = originating_source;
        this.unsubscribed_at = unsubscribed_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }


    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
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

    public String getKnownConsumerNumber() {
        return knownConsumerNumber;
    }

    public void setKnownConsumerNumber(String knownConsumerNumber) {
        this.knownConsumerNumber = knownConsumerNumber;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getEducation_level() {
        return education_level;
    }

    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }

    public String getGetup_client_number() {
        return getup_client_number;
    }

    public void setGetup_client_number(String getup_client_number) {
        this.getup_client_number = getup_client_number;
    }

    public String getOriginating_source() {
        return originating_source;
    }

    public void setOriginating_source(String originating_source) {
        this.originating_source = originating_source;
    }

    public Timestamp getUnsubscribed_at() {
        return unsubscribed_at;
    }

    public void setUnsubscribed_at(Timestamp unsubscribed_at) {
        this.unsubscribed_at = unsubscribed_at;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "KnownConsumerModel{" +
                "id=" + id +
                ", idNumber='" + idNumber + '\'' +
                ", knownConsumerNumber='" + knownConsumerNumber + '\'' +
                ", id_type='" + id_type + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", first_name='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", initials='" + initials + '\'' +
                ", title='" + title + '\'' +
                ", gender='" + gender + '\'' +
                ", marital_status='" + marital_status + '\'' +
                ", ethnicity='" + ethnicity + '\'' +
                ", education_level='" + education_level + '\'' +
                ", getup_client_number='" + getup_client_number + '\'' +
                ", originating_source='" + originating_source + '\'' +
                ", unsubscribed_at=" + unsubscribed_at +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}

