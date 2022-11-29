package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.GenderLookup;

public interface GenderLookupRepository  extends JpaRepository<GenderLookup, Integer> {

    public GenderLookup findByGender(String gender);
}
