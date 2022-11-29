package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Children implements Serializable {
    @JsonProperty("rolePlayerId")
    private String rolePlayerId;

    public String getRolePlayerId() {
        return rolePlayerId;
    }

    public void setRolePlayerId(String rolePlayerId) {
        this.rolePlayerId = rolePlayerId;
    }
}
