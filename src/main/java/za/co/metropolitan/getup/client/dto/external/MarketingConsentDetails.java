package za.co.metropolitan.getup.client.dto.external;

import za.co.metropolitan.getup.client.dto.ClientMarketConsentDetailsDto;

import java.io.Serializable;
import java.util.List;

public class MarketingConsentDetails implements Serializable {

    private String idNumber;
    private List<ClientMarketConsentDetailsDto> optInConsentList;

    public String getIdNumber() {
     return idNumber;
    }

    public void setIdNumber(String idNumber) {
     this.idNumber = idNumber;
    }

    public List<ClientMarketConsentDetailsDto> getOptInConsentList() {
     return optInConsentList;
    }

    public void setOptInConsentList(List<ClientMarketConsentDetailsDto> optInConsentList) {
     this.optInConsentList = optInConsentList;
    }

    @Override
    public String toString() {
     return "MarketingConsentDetails{" +
             "idNumber='" + idNumber + '\'' +
             ", optInConsentList=" + optInConsentList +
             '}';
    }
}