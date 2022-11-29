package za.co.metropolitan.getup.client.dto.external;

        import com.fasterxml.jackson.annotation.*;

        import java.io.Serializable;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id_number",
        "id_type",
        "title",
        "first_name",
        "last_name",
        "initials",
        "date_of_birth",
        "gender",
        "audit_user",
        "audit_time"
})
public class PolicyInfoParty  implements Serializable {

    @JsonProperty("id_number")
    private String id_number;
    @JsonProperty("id_type")
    private String id_type;
    @JsonProperty("title")
    private String title;
    @JsonProperty("first_name")
    private String first_name;
    @JsonProperty("last_name")
    private String last_name;
    @JsonProperty("initials")
    private String initials;
    @JsonProperty("date_of_birth")
    private String date_of_birth;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("audit_user")
    private String audit_user;
    @JsonProperty("audit_time")
    private String audit_time;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
    @JsonProperty("id_number")
    public String getId_number() {
        return id_number;
    }
    @JsonProperty("id_number")
    public void setId_number(String id_number) {
        this.id_number = id_number;
    }
    @JsonProperty("id_type")
    public String getId_type() {
        return id_type;
    }
    @JsonProperty("id_type")
    public void setId_type(String id_type) {
        this.id_type = id_type;
    }
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }
    @JsonProperty("first_name")
    public String getFirst_name() {
        return first_name;
    }
    @JsonProperty("first_name")
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    @JsonProperty("last_name")
    public String getLast_name() {
        return last_name;
    }
    @JsonProperty("last_name")
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    @JsonProperty("initials")
    public String getInitials() {
        return initials;
    }
    @JsonProperty("initials")
    public void setInitials(String initials) {
        this.initials = initials;
    }
    @JsonProperty("date_of_birth")
    public String getDate_of_birth() {
        return date_of_birth;
    }
    @JsonProperty("date_of_birth")
    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }
    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }
    @JsonProperty("audit_user")
    public String getAudit_user() {
        return audit_user;
    }
    @JsonProperty("audit_user")
    public void setAudit_user(String audit_user) {
        this.audit_user = audit_user;
    }
    @JsonProperty("audit_time")
    public String getAudit_time() {
        return audit_time;
    }
    @JsonProperty("audit_time")
    public void setAudit_time(String audit_time) {
        this.audit_time = audit_time;
    }


    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


    @Override
    public String toString() {
        return "PolicyInfoParty{" +
                "id_number='" + id_number + '\'' +
                ", id_type='" + id_type + '\'' +
                ", title='" + title + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", initials='" + initials + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", gender='" + gender + '\'' +
                ", audit_user='" + audit_user + '\'' +
                ", audit_time='" + audit_time + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}