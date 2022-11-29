package za.co.metropolitan.getup.client.modelRepository.KnownConsumer;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerConsent;
import java.math.BigInteger;
import java.util.List;

public interface KnownConsumerMarketConsentRepository  extends JpaRepository<KnownConsumerConsent, Integer> {
    public List<KnownConsumerConsent> findByKnownconsumerid(BigInteger id);

}