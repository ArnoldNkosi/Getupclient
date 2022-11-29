package za.co.metropolitan.getup.client.model.client;


import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(schema = "client", name = "tbl_nqf_levels_lkp")
public class NQFLevelsLookup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "nqf_code")
    private String nqfCode;

    @Column(name = "nqf_desc")
    private String nqfDescription;

    @Column(name = "education_display_text")
    private String educationDisplayText;

    @Column(name = "old_nqf_code")
    private String oldNqfCode;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getNqfCode() {
        return nqfCode;
    }

    public void setNqfCode(String nqfCode) {
        this.nqfCode = nqfCode;
    }

    public String getNqfDescription() {
        return nqfDescription;
    }

    public void setNqfDescription(String nqfDescription) {
        this.nqfDescription = nqfDescription;
    }

    public String getEducationDisplayText() {
        return educationDisplayText;
    }

    public void setEducationDisplayText(String educationDisplayText) {
        this.educationDisplayText = educationDisplayText;
    }

    public String getOldNqfCode() {
        return oldNqfCode;
    }

    public void setOldNqfCode(String oldNqfCode) {
        this.oldNqfCode = oldNqfCode;
    }

    @Override
    public String toString() {
        return "NQFLevelsLookup{" +
                "id=" + id +
                ", nqfCode='" + nqfCode + '\'' +
                ", nqfDescription='" + nqfDescription + '\'' +
                ", educationDisplayText='" + educationDisplayText + '\'' +
                ", oldNqfCode='" + oldNqfCode + '\'' +
                '}';
    }
}
