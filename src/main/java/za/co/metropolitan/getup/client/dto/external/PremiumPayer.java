package za.co.metropolitan.getup.client.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
            "accountType",
            "deductionDay",
            "accountHolderSurname",
            "bank",
            "branchCode",
            "accountNumber",
            "paymentType",
})

public class PremiumPayer implements Serializable {

    @JsonProperty("accountType")
    private String accountType = null;
    @JsonProperty("deductionDay")
    private int deductionDay;
    @JsonProperty("accountHolderSurname")
    private String accountHolderSurname;
    @JsonProperty("bank")
    private String bank;
    @JsonProperty("branchCode")
    private String branchCode;
    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("paymentType")
    private String paymentType;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getDeductionDay() {
        return deductionDay;
    }

    public void setDeductionDay(int deductionDay) {
        this.deductionDay = deductionDay;
    }

    public String getAccountHolderSurname() {
        return accountHolderSurname;
    }

    public void setAccountHolderSurname(String accountHolderSurname) {
        this.accountHolderSurname = accountHolderSurname;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
