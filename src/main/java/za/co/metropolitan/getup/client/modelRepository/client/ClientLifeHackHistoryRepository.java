package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.ClientLifeHackHistory;

public interface ClientLifeHackHistoryRepository  extends JpaRepository<ClientLifeHackHistory, Integer> {
}
