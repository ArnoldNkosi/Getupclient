package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.ClientIdTypeLookup;

public interface ClientIdTypeLookupRepository  extends JpaRepository<ClientIdTypeLookup, Integer> {

    public ClientIdTypeLookup findByIdType(String idType);
}
