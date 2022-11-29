package za.co.metropolitan.getup.client.service;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.co.metropolitan.getup.client.dto.KnownConsumer.KnownConsumerDetailsDto;

import java.math.BigInteger;
import java.security.SecureRandom;

@SpringBootTest
public class KnownConsumerServiceTest {
    @Autowired
    private KnownConsumerService knownConsumerDetailService;
  //  @Test
//    public void TestInsertKnownConsumerDataIntoDb(){
//        KnownConsumerDetailsDto model = new KnownConsumerDetailsDto();
//
//        model.setId_number("97020454545081");
//        model.setFirst_name("Arnold");
//        model.setSurname("Nkosi");
//        model.setEthnicity("African");
//        model.setEducation_level("Degree");
//        model.setGender("male");
//
//       BigInteger bigInteger = new BigInteger("1233252332662931411241");
//       model.setId(bigInteger);
//       model.setKnown_consumer_number(bigInteger.toString());
//       KnownConsumerDetailsDto details =  knownConsumerDetailService.createKnownConsumerIntoDb(model);
//
//       if(details==null){
//           model.setSurname("loser");
//       }
   }