package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.MaritalStatusLookup;

public interface MaritalStatusLookupRepository extends JpaRepository<MaritalStatusLookup, Integer> {

    public MaritalStatusLookup findByMaritalStatus(String maritalStatus);
}
