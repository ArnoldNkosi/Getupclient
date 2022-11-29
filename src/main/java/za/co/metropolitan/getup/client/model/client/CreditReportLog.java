package za.co.metropolitan.getup.client.model.client;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(schema = "client", name = "tbl_client_credit_report_log")
public class CreditReportLog {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "getup_client_number")
    private String getupClientNumber;

    @Column(name = "cpb_end_point")
    private String cpbEndPoint;

    @Column(name = "credit_report_request_at")
    private Timestamp creditReportRequestAt;

    @Column(name = "ip_address_request_from")
    private String ipAddressRequestFrom;

    @Column(name = "cpb_request_ref_number")
    private String cpbRequestRef;

    @Column(name = "cpb_response_msg")
    private String cpbResponseMsg;

    @Column(name = "cpb_http_code")
    private String cpbHttpCode;

    public String getCpbResponseMsg() {
        return cpbResponseMsg;
    }

    public void setCpbResponseMsg(String cpbResponseMsg) {
        this.cpbResponseMsg = cpbResponseMsg;
    }

    public String getCpbHttpCode() {
        return cpbHttpCode;
    }

    public void setCpbHttpCode(String cpbHttpCode) {
        this.cpbHttpCode = cpbHttpCode;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getGetupClientNumber() {
        return getupClientNumber;
    }

    public void setGetupClientNumber(String getupClientNumber) {
        this.getupClientNumber = getupClientNumber;
    }

    public Timestamp getCreditReportRequestAt() {
        return creditReportRequestAt;
    }

    public void setCreditReportRequestAt(Timestamp creditReportRequestAt) {
        this.creditReportRequestAt = creditReportRequestAt;
    }

    public String getIpAddressRequestFrom() {
        return ipAddressRequestFrom;
    }

    public void setIpAddressRequestFrom(String ipAddressRequestFrom) {
        this.ipAddressRequestFrom = ipAddressRequestFrom;
    }

    public String getCpbRequestRef() {
        return cpbRequestRef;
    }

    public void setCpbRequestRef(String cpbRequestRef) {
        this.cpbRequestRef = cpbRequestRef;
    }

    public String getCpbEndPoint() {
        return cpbEndPoint;
    }

    public void setCpbEndPoint(String cpbEndPoint) {
        this.cpbEndPoint = cpbEndPoint;
    }

    @Override
    public String toString() {
        return "CreditReportLog{" +
                "id=" + id +
                ", getupClientNumber='" + getupClientNumber + '\'' +
                ", cpbEndPoint='" + cpbEndPoint + '\'' +
                ", creditReportRequestAt=" + creditReportRequestAt +
                ", ipAddressRequestFrom='" + ipAddressRequestFrom + '\'' +
                ", cpbRequestRef='" + cpbRequestRef + '\'' +
                '}';
    }
}
