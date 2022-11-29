package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.EmploymentTypeLookup;

public interface EmploymentTypeLookupRepository extends JpaRepository<EmploymentTypeLookup, Integer> {

    public EmploymentTypeLookup findByEmploymentType(String employmentType);
}

