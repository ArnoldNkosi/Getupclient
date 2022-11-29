package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    private String idNumber;
    private String base64Image;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "idNumber='" + idNumber + '\'' +
                ", base64Image='" + base64Image + '\'' +
                '}';
    }
}
