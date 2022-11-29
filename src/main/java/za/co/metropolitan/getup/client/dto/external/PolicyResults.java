package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "results",
})
public class PolicyResults  implements Serializable {

    @JsonProperty("results")
    private List<Results> results = null;

    @JsonProperty("results")
    public List<Results> getResults() {
        return results;
    }

    @JsonProperty("results")
    public void setResults(List<Results> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "PolicyResults{" +
               results +
                '}';
    }
}
