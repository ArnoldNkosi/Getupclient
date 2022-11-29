package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

public class ClientBankDetailDto implements Serializable {
    private BigInteger id;
    private BigInteger clientID;
    private String bankName;
    private String AccountNumber;
    private String AccountType;
    private String branchCode;
    private String accountHolderName;
    private Timestamp updatedAt;
    private Timestamp validationAt;
    private Timestamp verificationAt;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getClientID() {
        return clientID;
    }

    public void setClientID(BigInteger clientID) {
        this.clientID = clientID;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getValidationAt() {
        return validationAt;
    }

    public void setValidationAt(Timestamp validationAt) {
        this.validationAt = validationAt;
    }

    public Timestamp getVerificationAt() {
        return verificationAt;
    }

    public void setVerificationAt(Timestamp verificationAt) {
        this.verificationAt = verificationAt;
    }
}
