package za.co.metropolitan.getup.client.dto;

import java.sql.Timestamp;

public class CreditReportLogDTO {

    private String getupClientNumber;

    private String cpbEndPoint;

    private String ipAddressRequestFrom;

    private String cpbRequestRef;

    private String cpbResponseMsg;

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

    public String getGetupClientNumber() {
        return getupClientNumber;
    }

    public void setGetupClientNumber(String getupClientNumber) {
        this.getupClientNumber = getupClientNumber;
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
        return "CreditReportLogDTO{" +
                "getupClientNumber='" + getupClientNumber + '\'' +
                ", cpbEndPoint='" + cpbEndPoint + '\'' +
                ", ipAddressRequestFrom='" + ipAddressRequestFrom + '\'' +
                ", cpbRequestRef='" + cpbRequestRef + '\'' +
                '}';
    }
}
