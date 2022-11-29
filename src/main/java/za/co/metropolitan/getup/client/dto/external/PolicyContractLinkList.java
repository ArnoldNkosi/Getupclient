package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "documentType",
        "link"
})
public class PolicyContractLinkList implements Serializable {
    @JsonProperty("documentType")
    private String documentType;
    @JsonProperty("link")
    private String link;

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "PolicyContractLinkList{" +
                "documentType='" + documentType + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
