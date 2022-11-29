package za.co.metropolitan.getup.client.model.client;


import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(schema = "client", name = "tbl_employment_type_lkp")
public class EmploymentTypeLookup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "employment_type")
    private String employmentType;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    @Override
    public String toString() {
        return "EmploymentTypeLookup{" +
                "id=" + id +
                ", employmentType='" + employmentType + '\'' +
                '}';
    }
}
