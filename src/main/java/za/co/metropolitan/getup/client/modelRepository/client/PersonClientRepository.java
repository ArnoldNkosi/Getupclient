package za.co.metropolitan.getup.client.modelRepository.client;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.client.PersonClient;

import java.math.BigInteger;


public interface PersonClientRepository  extends JpaRepository<PersonClient, Integer> , PersonClientRepositoryCustom {

    public PersonClient findByIdNumber(String idNumber);

    public PersonClient findByGetupClientNumber(String getupClientNumber);

    public PersonClient findByFirstNameAndSurnameAndInitials(String firstName, String surname, String initials);

    public PersonClient findById(BigInteger id);


}
