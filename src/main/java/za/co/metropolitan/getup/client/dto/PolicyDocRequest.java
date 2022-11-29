package za.co.metropolitan.getup.client.dto;

import java.io.Serializable;

public class PolicyDocRequest implements Serializable {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PolicyDocRequest{" +
                "url='" + url + '\'' +
                '}';
    }
}
