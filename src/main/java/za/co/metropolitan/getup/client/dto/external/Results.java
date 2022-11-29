package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "agreements",
        "metkey",
        "link",
        "party"
})
public class Results  implements Serializable {
    @JsonProperty("agreements")
    private List<Agreements> agreements = null;
    @JsonProperty("metkey")
    private String metkey;
    @JsonProperty("link")
    private Link link;
    @JsonProperty("party")
    private PolicyInfoParty party = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("agreements")
    public List<Agreements> getAgreements() {
        return agreements;
    }

    @JsonProperty("agreements")
    public void setAgreements(List<Agreements> agreements) {
        this.agreements = agreements;
    }

    @JsonProperty("metkey")
    public String getMetkey() {
        return metkey;
    }

    @JsonProperty("metkey")
    public void setMetkey(String metkey) {
        this.metkey = metkey;
    }

    @JsonProperty("link")
    public Link getLink() {
        return link;
    }

    @JsonProperty("link")
    public void setLink(Link link) {
        this.link = link;
    }

    @JsonProperty("party")
    public PolicyInfoParty getParty() {
        return party;
    }

    @JsonProperty("party")
    public void setParty(PolicyInfoParty party) {
        this.party = party;
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
        return "Results{" +
                "agreements=" + agreements +
                ", metkey='" + metkey + '\'' +
                ", link=" + link +
                ", party=" + party +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
