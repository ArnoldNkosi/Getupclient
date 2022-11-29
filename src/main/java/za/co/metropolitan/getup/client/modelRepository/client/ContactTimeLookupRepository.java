package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.ContactTimeLookup;

public interface ContactTimeLookupRepository extends JpaRepository<ContactTimeLookup, Integer> {

    public ContactTimeLookup findByContactTime(String contactTime);
}
