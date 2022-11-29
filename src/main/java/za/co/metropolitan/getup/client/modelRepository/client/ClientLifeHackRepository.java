package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.ClientLifeHack;

import java.math.BigInteger;
import java.util.List;

public interface ClientLifeHackRepository  extends JpaRepository<ClientLifeHack, Integer> {

    public List<ClientLifeHack> findByClientId(BigInteger clientId);
    ClientLifeHack findByIdAndClientId(BigInteger id, BigInteger clientId);

}
