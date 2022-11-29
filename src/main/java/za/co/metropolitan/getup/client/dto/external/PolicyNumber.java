package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
            "policyAlpha",
            "policyNumber"
})

public class PolicyNumber implements Serializable {

    @JsonProperty("policyAlpha")
    private String policyAlpha ;
    @JsonProperty("policyNumber")
    private String policyNumber;

    public String getPolicyAlpha() {
        return policyAlpha;
    }

    public void setPolicyAlpha(String policyAlpha) {
        this.policyAlpha = policyAlpha;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
}
