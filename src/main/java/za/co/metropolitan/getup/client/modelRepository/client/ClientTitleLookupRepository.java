package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.ClientTitleLookup;

public interface ClientTitleLookupRepository  extends JpaRepository<ClientTitleLookup, Integer> {

    public ClientTitleLookup findByTitle(String title);
}
