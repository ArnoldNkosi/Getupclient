package za.co.metropolitan.getup.client.modelRepository.KnownConsumer;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerContact;

import java.math.BigInteger;
import java.util.List;

public interface KnownConsumerContactRepository extends JpaRepository<KnownConsumerContact,Integer> {
    public List<KnownConsumerContact> findByKnownConsumerId(BigInteger knownconsumerid);
    public List<KnownConsumerContact> findByContactValueLike(String contactValue);
}
