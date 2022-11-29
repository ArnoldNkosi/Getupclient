package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;

public class PaymentDto implements Serializable {

        private String paymentMethod;
        private int deductionDay;
        private BankDetailsDto bankDetails;


        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public int getDeductionDay() {
            return deductionDay;
        }

        public void setDeductionDay(int deductionDay) {
            this.deductionDay = deductionDay;
        }

        public BankDetailsDto getBankDetails() {
            return bankDetails;
        }

        public void setBankDetails(BankDetailsDto bankDetails) {
            this.bankDetails = bankDetails;
        }
    }
