package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;

public class ResponseDto implements Serializable {

        private boolean errorStatus = false;
        private String responseMessage = null;

        public boolean isErrorStatus() {
            return errorStatus;
        }

        public void setErrorStatus(boolean errorStatus) {
            this.errorStatus = errorStatus;
        }

        public String getResponseMessage() {
            return responseMessage;
        }

        public void setResponseMessage(String responseMessage) {
            this.responseMessage = responseMessage;
        }

        @Override
        public String toString() {
            return "ResponseDto{" +
                    "errorStatus=" + errorStatus +
                    ", responseMessage='" + responseMessage + '\'' +
                    '}';
        }

}
