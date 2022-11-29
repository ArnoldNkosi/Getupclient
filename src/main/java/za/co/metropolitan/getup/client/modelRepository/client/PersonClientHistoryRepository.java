package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.PersonClientHistory;

public interface PersonClientHistoryRepository extends JpaRepository<PersonClientHistory, Integer> {
}
