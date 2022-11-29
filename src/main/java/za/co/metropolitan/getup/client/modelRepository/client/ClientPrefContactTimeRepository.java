package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.ClientPrefContactTime;

import java.math.BigInteger;

public interface ClientPrefContactTimeRepository extends JpaRepository<ClientPrefContactTime, Integer> {

 ClientPrefContactTime findByClientId(BigInteger clientId);
 ClientPrefContactTime findByIdAndClientId(BigInteger id, BigInteger clientId);
 String findClientPrefContactTimeById(BigInteger id);

}
