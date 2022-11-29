package za.co.metropolitan.getup.client.model.product;


import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(schema = "pdtpfl", name = "tbl_product_lifehack_lkp")
public class ProductLifeHackLookup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "product")
    private String product;

    @Column(name = "lifehack")
    private String lifehack;

    @Column(name = "ended_at")
    private Timestamp endedAt;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getLifehack() {
        return lifehack;
    }

    public void setLifehack(String lifehack) {
        this.lifehack = lifehack;
    }

    public Timestamp getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(Timestamp endedAt) {
        this.endedAt = endedAt;
    }

    @Override
    public String toString() {
        return "ProductLifeHackLookup{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", lifehack='" + lifehack + '\'' +
                ", endedAt=" + endedAt +
                '}';
    }
}
