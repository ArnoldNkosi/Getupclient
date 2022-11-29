package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.dto.ClientBankDetailDto;
import za.co.metropolitan.getup.client.dto.ClientDetailsDto;
import za.co.metropolitan.getup.client.model.client.ClientBankDetail;

import java.math.BigInteger;

public interface ClientBankDetailRepository extends JpaRepository<ClientBankDetail,Long> {

    public ClientBankDetail findByClientID(BigInteger clientId);
}
