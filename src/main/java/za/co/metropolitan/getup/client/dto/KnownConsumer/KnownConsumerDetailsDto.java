package za.co.metropolitan.getup.client.dto.KnownConsumer;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class KnownConsumerDetailsDto implements Serializable {

    private BigInteger id;

    private String Known_consumer_number;

    private String id_number;

    private String OtpToken;
    private String id_type;

    private Date date_of_birth;

    private String first_name;

    private String surname;

    private String initials;

    private String title;

    private String gender;

    private String marital_status;

    private String ethnicity;

    private String education_level;

    private String getup_client_number;

    private String originating_source;

    private Timestamp unsubscribed_at;

    private Timestamp created_at;

    private Timestamp updated_at;

    private List<KnownConsumerContactDto> knownConsumerContactDto;
    private List<KnownConsumerMarketConsentDetailsDto> knownConsumerConsents;

    public List<KnownConsumerMarketConsentDetailsDto> getKnownConsumerConsents() {
        return knownConsumerConsents;
    }

    public void setKnownConsumerConsents(List<KnownConsumerMarketConsentDetailsDto> knownConsumerConsents) {
        this.knownConsumerConsents = knownConsumerConsents;
    }

    public List<KnownConsumerContactDto> getKnownConsumerContactDto() {
        return knownConsumerContactDto;
    }

    public void setKnownConsumerContactDto(List<KnownConsumerContactDto> knownConsumerContactDto) {
        this.knownConsumerContactDto = knownConsumerContactDto;
    }

    public KnownConsumerDetailsDto() {
    }

    public String getOtpToken() {
        return OtpToken;
    }

    public void setOtpToken(String otpToken) {
        OtpToken = otpToken;
    }

    public KnownConsumerDetailsDto(BigInteger id, String known_consumer_number, String id_number, String otpToken, String id_type, Date date_of_birth, String first_name, String surname, String initials, String title, String gender, String marital_status, String ethnicity, String education_level, String getup_client_number, String originating_source, Timestamp unsubscribed_at, Timestamp created_at, Timestamp updated_at, List<KnownConsumerContactDto> knownConsumerContactDto) {
        this.id = id;
        Known_consumer_number = known_consumer_number;
        this.id_number = id_number;
        OtpToken = otpToken;
        this.id_type = id_type;
        this.date_of_birth = date_of_birth;
        this.first_name = first_name;
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
        this.knownConsumerContactDto = knownConsumerContactDto;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getKnown_consumer_number() {
        return Known_consumer_number;
    }

    public void setKnown_consumer_number(String known_consumer_number) {
        Known_consumer_number = known_consumer_number;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
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

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
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
        return "KnownConsumerDetailsDto{" +
                "id=" + id +
                ", Known_consumer_number='" + Known_consumer_number + '\'' +
                ", id_number='" + id_number + '\'' +
                ", OtpToken='" + OtpToken + '\'' +
                ", id_type='" + id_type + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", first_name='" + first_name + '\'' +
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
                ", knownConsumerContactDto=" + knownConsumerContactDto +
                '}';
    }
}