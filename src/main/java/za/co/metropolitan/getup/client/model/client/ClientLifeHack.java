package za.co.metropolitan.getup.client.model.client;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(schema = "client", name = "tbl_client_lifehack")
public class ClientLifeHack {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "client_id")
    private BigInteger clientId;

    @Column(name = "product")
    private String product;

    @Column(name = "lifehack")
    private String lifeHack;

    @Column(name = "user_signon")
    private String userSignOn;

    @Column(name = "created_at"  ,nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "ended_at")
    private Timestamp endedAt;

    @Column(name = "lifehack_score")
    private Integer lifeHackScore;

    @Column(name = "lifehack_score_at")
    private Timestamp lifeHackScoreAt;




    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getClientId() {
        return clientId;
    }

    public void setClientId(BigInteger clientId) {
        this.clientId = clientId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getLifeHack() {
        return lifeHack;
    }

    public void setLifeHack(String lifeHack) {
        this.lifeHack = lifeHack;
    }

    public String getUserSignOn() {
        return userSignOn;
    }

    public void setUserSignOn(String userSignOn) {
        this.userSignOn = userSignOn;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Timestamp endedAt) {
        this.endedAt = endedAt;
    }

    public Integer getLifeHackScore() {
        return lifeHackScore;
    }

    public void setLifeHackScore(Integer lifeHackScore) {
        this.lifeHackScore = lifeHackScore;
    }

    public Timestamp getLifeHackScoreAt() {
        return lifeHackScoreAt;
    }

    public void setLifeHackScoreAt(Timestamp lifeHackScoreAt) {
        this.lifeHackScoreAt = lifeHackScoreAt;
    }

    @Override
    public String toString() {
        return "ClientLifeHack{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", product='" + product + '\'' +
                ", lifeHack='" + lifeHack + '\'' +
                ", userSignOn='" + userSignOn + '\'' +
                ", createdAt=" + createdAt +
                ", endedAt=" + endedAt +
                ", lifeHackScore=" + lifeHackScore +
                ", lifeHackScoreAt=" + lifeHackScoreAt +
                '}';
    }
}
