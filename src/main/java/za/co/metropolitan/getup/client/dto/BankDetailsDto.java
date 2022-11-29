package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;

public class BankDetailsDto implements Serializable {

        /**
         *
         */
        private static final long serialVersionUID = 1L;

        private String accountHolder;
        private String bank;
        private String branchCode;
        private String accountNumber;
        private String bankStatus;

        public String getAccountHolder() {
            return accountHolder;
        }

        public void setAccountHolder(String accountHolder) {
            this.accountHolder = accountHolder;
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

        public String getBankStatus() {
            return bankStatus;
        }

        public void setBankStatus(String bankStatus) {
            this.bankStatus = bankStatus;
        }

        @Override
        public String toString() {
            return "BankDetails{" +
                    "accountHolder='" + accountHolder + '\'' +
                    ", bank='" + bank + '\'' +
                    ", branchCode='" + branchCode + '\'' +
                    ", accountNumber='" + accountNumber + '\'' +
                    ", bankStatus='" + bankStatus + '\'' +
                    '}';
        }
    }
