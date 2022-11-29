package za.co.metropolitan.getup.client.model.zendesk;
import java.io.Serializable;
public class Requester  implements Serializable {

    private Integer locale_id;
    private String name;
    private String email;
    private String number;

    public Integer getLocale_id() {
        return locale_id;
    }
    public void setLocale_id(Integer locale_id) {
        this.locale_id = locale_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
}
