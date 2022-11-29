package za.co.metropolitan.getup.client.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import za.co.metropolitan.getup.client.dto.KnownConsumer.KnownConsumerUserDto;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerModel;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerUser;
import za.co.metropolitan.getup.client.modelRepository.KnownConsumer.KnownConsumerUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class AuditUserDetails {
    private static final Logger log = LoggerFactory.getLogger(za.co.metropolitan.getup.client.service.ClientDetailService.class);
    @Autowired
    private KnownConsumerUserRepository userRepository;
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveUserDetails(KnownConsumerUserDto knownConsumerUserDto,KnownConsumerModel fetchModel) throws Exception {
        //check if the known consumer exists
        if (fetchModel != null) {
            KnownConsumerUser user = userRepository.findByKnownConsumerNumber(knownConsumerUserDto.getKnown_consumer_number());
            if (user != null) {
                user.setOtp(knownConsumerUserDto.getOtp());
                user.setOtp_created_at(Timestamp.valueOf(LocalDateTime.now()));
                userRepository.saveAndFlush(user);
            }
            else {
                  KnownConsumerUser newUser = new KnownConsumerUser();

                newUser.setKnownConsumerNumber(fetchModel.getKnownConsumerNumber());
                newUser.setOtp(knownConsumerUserDto.getOtp());
                newUser.setOtp_created_at(Timestamp.valueOf(LocalDateTime.now()));
                userRepository.saveAndFlush(newUser);
            }
        }
    }
}
