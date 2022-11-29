package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.NQFLevelsLookup;

public interface NQFLevelsLookupRepository  extends JpaRepository<NQFLevelsLookup, Integer> {

    public NQFLevelsLookup findByEducationDisplayText(String educationDisplayText);
}
