package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.ClientImage;

import java.math.BigInteger;

public interface ClientImageRepository  extends JpaRepository<ClientImage, Integer> {

    public ClientImage findByClientId(BigInteger clientId);

    public ClientImage findByClientImage (byte[] clientImage);
}
