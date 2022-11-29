package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.MarketingConsentLookup;

public interface MarketingConsentLookupRepository extends JpaRepository<MarketingConsentLookup, Integer> {

    public MarketingConsentLookup findByConsentType(String consentType);
}
