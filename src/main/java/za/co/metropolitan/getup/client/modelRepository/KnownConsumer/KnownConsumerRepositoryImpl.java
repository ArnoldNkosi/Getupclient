package za.co.metropolitan.getup.client.modelRepository.KnownConsumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;

public class KnownConsumerRepositoryImpl implements KnownConsumerRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;


    public String getNextKnownConsumerNumber() {
        String knownConsumerNumber = "KC-";
        String queryStr = 	"select nextval('client.sq_getup_known_consumer_number')" ;
        Query query = entityManager.createNativeQuery(queryStr);
        BigInteger results = (BigInteger) query.getSingleResult();
        knownConsumerNumber=knownConsumerNumber+results;
        return knownConsumerNumber;
    }
}
