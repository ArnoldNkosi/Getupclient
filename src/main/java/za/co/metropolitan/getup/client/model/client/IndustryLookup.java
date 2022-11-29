package za.co.metropolitan.getup.client.model.client;


import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(schema = "client", name = "tbl_met_industry_lkp")
public class IndustryLookup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "met_industry_name")
    private String metIndustryName;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getMetIndustryName() {
        return metIndustryName;
    }

    public void setMetIndustryName(String metIndustryName) {
        this.metIndustryName = metIndustryName;
    }

    @Override
    public String toString() {
        return "IndustryLookup{" +
                "id=" + id +
                ", metIndustryName='" + metIndustryName + '\'' +
                '}';
    }
}
