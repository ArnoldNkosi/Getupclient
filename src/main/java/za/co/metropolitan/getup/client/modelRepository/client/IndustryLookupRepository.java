package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.IndustryLookup;

public interface IndustryLookupRepository  extends JpaRepository<IndustryLookup, Integer> {

    public IndustryLookup findByMetIndustryName(String metIndustryName);
}

