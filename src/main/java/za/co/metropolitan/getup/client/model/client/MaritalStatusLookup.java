package za.co.metropolitan.getup.client.model.client;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(schema = "client", name = "tbl_marital_status_lkp")
public class MaritalStatusLookup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "marital_status")
    private String maritalStatus;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Override
    public String toString() {
        return "MaritalStatusLookup{" +
                "id=" + id +
                ", maritalStatus='" + maritalStatus + '\'' +
                '}';
    }
}
