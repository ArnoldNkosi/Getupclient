package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "policyNum",
        "links"
})

public class PolicyContractList {

    @JsonProperty("policyNum")
    private String policyNum;

    @JsonProperty("links")
    public List<PolicyContractLinkList> links = null;

    public String getPolicyNum() {
        return policyNum;
    }

    public void setPolicyNum(String policyNum) {
        this.policyNum = policyNum;
    }

    public List<PolicyContractLinkList> getLinks() {
        return links;
    }

    public void setLinks(List<PolicyContractLinkList> links) {
        this.links = links;
    }
}
