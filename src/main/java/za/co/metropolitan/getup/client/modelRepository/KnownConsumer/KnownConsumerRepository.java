package za.co.metropolitan.getup.client.modelRepository.KnownConsumer;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerModel;;
import java.math.BigInteger;
import java.util.List;

public interface KnownConsumerRepository  extends JpaRepository<KnownConsumerModel, Integer> , KnownConsumerRepositoryCustom {
      public KnownConsumerModel findById(BigInteger id);
      public KnownConsumerModel findByKnownConsumerNumber(String knownConsumerNumber);
      public List<KnownConsumerModel> findByFirstNameAndSurname(String firstname,String surname);

      public KnownConsumerModel findFirstByIdNumber(String idNumber);



}
