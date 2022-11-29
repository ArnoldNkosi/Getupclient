package za.co.metropolitan.getup.client.model.client;


import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(schema = "client", name = "tbl_lifehack_lkp")
public class LifeHackLookup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "lifehack")
    private String lifeHack;

    @Column(name = "ended_at")
    private Timestamp endedAt;


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getLifeHack() {
        return lifeHack;
    }

    public void setLifeHack(String lifeHack) {
        this.lifeHack = lifeHack;
    }

    public Timestamp getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Timestamp endedAt) {
        this.endedAt = endedAt;
    }

    @Override
    public String toString() {
        return "LifeHackLookup{" +
                "id=" + id +
                ", lifeHack='" + lifeHack + '\'' +
                ", endedAt=" + endedAt +
                '}';
    }
}
