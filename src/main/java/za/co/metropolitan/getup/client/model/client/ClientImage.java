package za.co.metropolitan.getup.client.model.client;


import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(schema = "client", name = "tbl_client_image")
public class ClientImage {

    @Id
    @Column(name = "client_id")
    private BigInteger clientId;

    @Column(name = "client_image")
    private byte[] clientImage;

    public BigInteger getClientId() {
        return clientId;
    }

    public void setClientId(BigInteger clientId) {
        this.clientId = clientId;
    }

    public byte[] getClientImage() {
        return clientImage;
    }

    public void setClientImage(byte[] clientImage) {
        this.clientImage = clientImage;
    }

    @Override
    public String toString() {
        return "ClientImage{" +
                "clientId=" + clientId +
                ", clientImage=" + clientImage +
                '}';
    }
}
