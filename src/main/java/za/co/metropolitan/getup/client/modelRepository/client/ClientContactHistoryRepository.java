package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.ClientContactHistory;

import java.math.BigInteger;

public interface ClientContactHistoryRepository extends JpaRepository<ClientContactHistory, Integer> {

    ClientContactHistory findByClientId(BigInteger clientId);
}
