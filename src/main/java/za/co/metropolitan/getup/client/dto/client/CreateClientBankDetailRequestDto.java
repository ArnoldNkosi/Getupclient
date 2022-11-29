package za.co.metropolitan.getup.client.dto.client;

import java.math.BigInteger;
import java.sql.Timestamp;

public class CreateClientBankDetailRequestDto {
    private Long id;
    private BigInteger clientID;
    private String bankName;
    private String accountNumber;
    private String accountType;
    private String branchCode;
    private String accountHolderName;
    private Timestamp updatedAt;
    private Timestamp validationAt;
    private Timestamp verificationAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
