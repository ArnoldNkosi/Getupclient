package za.co.metropolitan.getup.client.model.knownconsumer;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(schema = "client", name =  "tbl_known_consumer_source_lkp")
public class KnownConsumerSourcelkp {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "originating_source")
    private String originating_source;

    public KnownConsumerSourcelkp() {
    }

    public KnownConsumerSourcelkp(BigInteger id, String originating_source) {
        this.id = id;
        this.originating_source = originating_source;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getOriginating_source() {
        return originating_source;
    }

    public void setOriginating_source(String originating_source) {
        this.originating_source = originating_source;
    }

    @Override
    public String toString() {
        return "KnownConsumerSourcelkp{" +
                "id=" + id +
                ", originating_source='" + originating_source + '\'' +
                '}';
    }
}
