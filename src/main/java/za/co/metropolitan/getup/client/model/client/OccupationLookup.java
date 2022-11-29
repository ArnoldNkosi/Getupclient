package za.co.metropolitan.getup.client.model.client;


import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(schema = "client", name = "tbl_occupation_lkp")
public class OccupationLookup {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "occupation_type_code")
    private String occupationTypeCode;

    @Column(name = "occupation_type")
    private String occupationType;

    @Column(name = "occ_disability_class_code")
    private String occDisabilityClassCode;

    @Column(name = "occ_accident_class_code")
    private String occAccidentClassCode;

    @Column(name = "loading_type_code")
    private String loadingTypeCode;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getOccupationTypeCode() {
        return occupationTypeCode;
    }

    public void setOccupationTypeCode(String occupationTypeCode) {
        this.occupationTypeCode = occupationTypeCode;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }

    public String getOccDisabilityClassCode() {
        return occDisabilityClassCode;
    }

    public void setOccDisabilityClassCode(String occDisabilityClassCode) {
        this.occDisabilityClassCode = occDisabilityClassCode;
    }

    public String getOccAccidentClassCode() {
        return occAccidentClassCode;
    }

    public void setOccAccidentClassCode(String occAccidentClassCode) {
        this.occAccidentClassCode = occAccidentClassCode;
    }

    public String getLoadingTypeCode() {
        return loadingTypeCode;
    }

    public void setLoadingTypeCode(String loadingTypeCode) {
        this.loadingTypeCode = loadingTypeCode;
    }

    @Override
    public String toString() {
        return "OccupationLookup{" +
                "id=" + id +
                ", occupationTypeCode='" + occupationTypeCode + '\'' +
                ", occupationType='" + occupationType + '\'' +
                ", occDisabilityClassCode='" + occDisabilityClassCode + '\'' +
                ", occAccidentClassCode='" + occAccidentClassCode + '\'' +
                ", loadingTypeCode='" + loadingTypeCode + '\'' +
                '}';
    }
}
