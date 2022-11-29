package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.ClientMarketConsent;

import java.math.BigInteger;
import java.util.List;

public interface ClientMarketConsentRepository   extends JpaRepository<ClientMarketConsent, Integer> {

    List<ClientMarketConsent> findByClientId(BigInteger clientId);
    ClientMarketConsent findByIdAndClientId(BigInteger id, BigInteger clientId);
    ClientMarketConsent findByConsentTypeAndClientId(String consent_type, BigInteger clientId);

}
