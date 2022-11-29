package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "policyIndicator",
        "product_system",
        "agreement_number",
        "role_players",
        "policy_book",
        "product_description",
        "in_force"
})
public class Agreements  implements Serializable {


    @JsonProperty("policyIndicator")
    private String policyIndicator;

    @JsonProperty("product_system")
    private String product_system;

    @JsonProperty("agreement_number")
    private String agreement_number;

    @JsonProperty("role_players")
    private List<Object> role_players = null;

    @JsonProperty("policy_book")
    private String policy_book;

    @JsonProperty("product_description")
    private String product_description;

    @JsonProperty("in_force")
    private Boolean in_force;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();


    public String getPolicyIndicator() {
        return policyIndicator;
    }

    public void setPolicyIndicator(String policyIndicator) {
        this.policyIndicator = policyIndicator;
    }

    public String getProduct_system() {
        return product_system;
    }

    public void setProduct_system(String product_system) {
        this.product_system = product_system;
    }

    public String getAgreement_number() {
        return agreement_number;
    }

    public void setAgreement_number(String agreement_number) {
        this.agreement_number = agreement_number;
    }

    public List<Object> getRole_players() {
        return role_players;
    }

    public void setRole_players(List<Object> role_players) {
        this.role_players = role_players;
    }

    public String getPolicy_book() {
        return policy_book;
    }

    public void setPolicy_book(String policy_book) {
        this.policy_book = policy_book;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public Boolean getIn_force() {
        return in_force;
    }

    public void setIn_force(Boolean in_force) {
        this.in_force = in_force;
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
        return "{" +
                "policyIndicator='" + policyIndicator + '\'' +
                ", product_system='" + product_system + '\'' +
                ", agreement_number='" + agreement_number + '\'' +
                ", role_players=" + role_players +
                ", policy_book='" + policy_book + '\'' +
                ", product_description='" + product_description + '\'' +
                ", in_force=" + in_force +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
