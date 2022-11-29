package za.co.metropolitan.getup.client.model.client;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(schema = "client", name = "tbl_client_identification_type_lkp")
public class ClientIdTypeLookup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "id_type")
    private String idType;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    @Override
    public String toString() {
        return "ClientIdTypeLookup{" +
                "id=" + id +
                ", idType='" + idType + '\'' +
                '}';
    }
}
