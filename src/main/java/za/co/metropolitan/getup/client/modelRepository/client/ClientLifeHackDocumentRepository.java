package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.ClientLifeHack;

public interface ClientLifeHackDocumentRepository extends JpaRepository<ClientLifeHack, Integer> {
}
