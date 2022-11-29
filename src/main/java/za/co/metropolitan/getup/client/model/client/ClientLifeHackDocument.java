package za.co.metropolitan.getup.client.model.client;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(schema = "client", name = "tbl_client_lifehack_document")
public class ClientLifeHackDocument {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;


    @Column(name = "client_lifehack_id")
    private BigInteger clientLifeHackId;

    @Column(name = "document_name")
    private String documentName;


    @Column(name = "document_location")
    private String documentLocation;

    @Column(name = "has_been_validated")
    private Boolean hasBeenValidated;

    @Column(name = "created_at"  ,nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getClientLifeHackId() {
        return clientLifeHackId;
    }

    public void setClientLifeHackId(BigInteger clientLifeHackId) {
        this.clientLifeHackId = clientLifeHackId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentLocation() {
        return documentLocation;
    }

    public void setDocumentLocation(String documentLocation) {
        this.documentLocation = documentLocation;
    }

    public Boolean getHasBeenValidated() {
        return hasBeenValidated;
    }

    public void setHasBeenValidated(Boolean hasBeenValidated) {
        this.hasBeenValidated = hasBeenValidated;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "ClientLifeHackDocument{" +
                "id=" + id +
                ", clientLifeHackId=" + clientLifeHackId +
                ", documentName='" + documentName + '\'' +
                ", documentLocation='" + documentLocation + '\'' +
                ", hasBeenValidated=" + hasBeenValidated +
                ", createdAt=" + createdAt +
                '}';
    }
}
