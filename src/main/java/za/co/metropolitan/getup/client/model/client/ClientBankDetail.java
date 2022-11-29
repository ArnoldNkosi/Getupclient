package za.co.metropolitan.getup.client.model.client;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(schema = "client", name = "tbl_client_banking_detail")
public class ClientBankDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @Column(name = "client_id")
    private BigInteger clientID;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "bank_account_number")
    private String AccountNumber;
    @Column(name = "bank_account_type")
    private String AccountType;
    @Column(name = "bank_branch_code")
    private String branchCode;
    @Column(name = "account_holder_name")
    private String accountHolderName;

    @Column(name = "created_at"  ,nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Column(name = "validation_at")
    private Timestamp validationAt;
    @Column(name = "verification_at")
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
