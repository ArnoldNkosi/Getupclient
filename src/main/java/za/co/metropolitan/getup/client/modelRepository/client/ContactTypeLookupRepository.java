package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.ContactTypeLookup;

public interface ContactTypeLookupRepository extends JpaRepository<ContactTypeLookup, Integer> {

    public ContactTypeLookup findByContactType(String contactType);
}
