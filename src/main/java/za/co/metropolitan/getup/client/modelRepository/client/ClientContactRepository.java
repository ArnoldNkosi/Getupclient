package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.ClientContact;

import java.math.BigInteger;
import java.util.List;

public interface ClientContactRepository extends JpaRepository<ClientContact, Integer> {

    public ClientContact findByContactValue(String contactValue);

    public ClientContact findByContactValueAndClientId(String contactValue , BigInteger clientId);

    public ClientContact findById(BigInteger id);

    public List<ClientContact> findByClientId(BigInteger clientId);

    public ClientContact findByIdAndClientId(BigInteger id, BigInteger clientId);

    public ClientContact findByContactTypeAndClientId (String contactType , BigInteger clientId);

}
