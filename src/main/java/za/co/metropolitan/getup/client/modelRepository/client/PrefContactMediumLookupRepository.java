package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.PrefContactMediumLookup;

public interface PrefContactMediumLookupRepository  extends JpaRepository<PrefContactMediumLookup, Integer> {

    PrefContactMediumLookup findByMedium(String medium);
}
