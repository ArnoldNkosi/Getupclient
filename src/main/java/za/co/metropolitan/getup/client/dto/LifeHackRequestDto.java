package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;
import java.util.Date;

public class LifeHackRequestDto implements Serializable {

    private String clientIDNumber;
    private String productName;
    private Date startPeriod;
    private Date endPeriod;

    public String getClientIDNumber() {
        return clientIDNumber;
    }

    public void setClientIDNumber(String clientIDNumber) {
        this.clientIDNumber = clientIDNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Date startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Date getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(Date endPeriod) {
        this.endPeriod = endPeriod;
    }

    @Override
    public String toString() {
        return "LifeHackRequestDto{" +
                "clientIDNumber='" + clientIDNumber + '\'' +
                ", productName='" + productName + '\'' +
                ", startPeriod=" + startPeriod +
                ", endPeriod=" + endPeriod +
                '}';
    }
}
