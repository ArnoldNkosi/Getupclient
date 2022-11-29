package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.ClientEmploymentDetails;

import java.math.BigInteger;

public interface ClientEmploymentDetailsRepository  extends JpaRepository<ClientEmploymentDetails, Integer> {

    ClientEmploymentDetails findByClientId(BigInteger clientId);
    ClientEmploymentDetails findByIdAndClientId(BigInteger id, BigInteger clientId);

}