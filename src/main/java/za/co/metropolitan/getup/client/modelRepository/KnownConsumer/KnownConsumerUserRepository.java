package za.co.metropolitan.getup.client.modelRepository.KnownConsumer;
import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerModel;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerUser;
import java.math.BigInteger;

public interface KnownConsumerUserRepository  extends JpaRepository<KnownConsumerUser, Integer> {

    public KnownConsumerUser findById(BigInteger id);
    public KnownConsumerUser findByKnownConsumerNumber(String knownConsumerNumber);

}
