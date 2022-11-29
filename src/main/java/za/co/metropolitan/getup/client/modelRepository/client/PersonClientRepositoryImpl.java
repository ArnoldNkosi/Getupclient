package za.co.metropolitan.getup.client.modelRepository.client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;

public class PersonClientRepositoryImpl implements PersonClientRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;


      public String getNextClientNumber() {
        String clientNumber = "GU-";
        String queryStr = 	"select nextval('CLIENT.SQ_GETUP_CLIENT_NUMBER')" ;
        Query query = entityManager.createNativeQuery(queryStr);
        BigInteger results = (BigInteger) query.getSingleResult();
        clientNumber=clientNumber+results;
        return clientNumber;
    }
}
