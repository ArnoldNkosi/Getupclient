package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.ClientPrefInboundMedium;
import za.co.metropolitan.getup.client.model.client.ClientPrefOutboundMedium;

import java.math.BigInteger;
import java.util.List;

public interface ClientPrefOutboundMediumRepository extends JpaRepository<ClientPrefOutboundMedium, Integer> {

    public List<ClientPrefOutboundMedium> findByClientId(BigInteger clientId);
    ClientPrefOutboundMedium findByMediumAndClientId(String medium, BigInteger clientId);


}
