package za.co.metropolitan.getup.client.dto.client;

import java.math.BigInteger;

public class ContactMediumRequestDto {

    private Boolean whatsApp;
    private Boolean facebookMsg;
    private Boolean sms;
    private Boolean email;
    private Boolean pushNotifications;


    public Boolean getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(Boolean whatsApp) {
        this.whatsApp = whatsApp;
    }

    public Boolean getFacebookMsg() {
        return facebookMsg;
    }

    public void setFacebookMsg(Boolean facebookMsg) {
        this.facebookMsg = facebookMsg;
    }

    public Boolean getSms() {
        return sms;
    }

    public void setSms(Boolean sms) {
        this.sms = sms;
    }

    public Boolean getEmail() {
        return email;
    }

    public void setEmail(Boolean email) {
        this.email = email;
    }

    public Boolean getPushNotifications() {
        return pushNotifications;
    }

    public void setPushNotifications(Boolean pushNotifications) {
        this.pushNotifications = pushNotifications;
    }

    @Override
    public String toString() {
        return "ContactMediumDto{" +
                "whatsApp=" + whatsApp +
                ", facebookMsg=" + facebookMsg +
                ", sms=" + sms +
                ", email=" + email +
                ", pushNotifications=" + pushNotifications +
                '}';
    }
}
