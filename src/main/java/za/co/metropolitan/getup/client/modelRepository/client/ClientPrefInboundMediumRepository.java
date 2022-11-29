package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.ClientPrefInboundMedium;

import java.math.BigInteger;
import java.util.List;

public interface ClientPrefInboundMediumRepository extends JpaRepository<ClientPrefInboundMedium, Integer> {
        List<ClientPrefInboundMedium> findByClientId(BigInteger clientId);
        ClientPrefInboundMedium findByMediumAndClientId(String medium, BigInteger clientId);
// ClientPrefContactTimeDto findByIdAndClientId(BigInteger id, BigInteger clientId)

}
