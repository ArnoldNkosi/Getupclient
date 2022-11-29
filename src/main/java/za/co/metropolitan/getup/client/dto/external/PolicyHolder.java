package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
            "rolePlayerId",
            "percentage"
})

public class PolicyHolder implements Serializable {

    @JsonProperty("rolePlayerId")
    private String rolePlayerId;
    @JsonProperty("percentage")
    private String percentage;

    public String getRolePlayerId() {
        return rolePlayerId;
    }

    public void setRolePlayerId(String rolePlayerId) {
        this.rolePlayerId = rolePlayerId;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
