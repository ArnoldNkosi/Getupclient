package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.OccupationLookup;

public interface OccupationLookupRepository  extends JpaRepository<OccupationLookup, Integer> {

    public OccupationLookup findByOccupationType(String occupationType);


}

