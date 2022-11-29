package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.EthnicityLookup;

public interface EthnicityLookupRepository extends JpaRepository<EthnicityLookup, Integer> {

    public EthnicityLookup findByEthnicity(String ethnicity);


}
