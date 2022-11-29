package za.co.metropolitan.getup.client.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import za.co.metropolitan.getup.client.dto.KnownConsumer.KnownConsumerContactDto;
import za.co.metropolitan.getup.client.dto.KnownConsumer.KnownConsumerUserDto;
import za.co.metropolitan.getup.client.dto.UpdateResponseDto;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerModel;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerUser;
import za.co.metropolitan.getup.client.modelRepository.KnownConsumer.KnownConsumerOTPRepository;
import za.co.metropolitan.getup.client.modelRepository.KnownConsumer.KnownConsumerRepository;
import java.sql.Timestamp;
import java.util.Random;

@Service
public class OTPTokenService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private KnownConsumerOTPRepository otpRepository;

    @Autowired
    private KnownConsumerRepository knownConsumerRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private KnownConsumerContactService contactService;
    @Autowired
    private AuditUserDetails auditUserDetails;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public KnownConsumerUserDto GenerateOTP(KnownConsumerUserDto knownConsumerUserDto) throws Exception {
        Random random = new Random();
        Integer token = (int) 1000 + random.nextInt(9000);

        if (knownConsumerUserDto.getKnownConsumerContactDto().size() > 0) {
            KnownConsumerModel fetchModel = knownConsumerRepository.findByKnownConsumerNumber(knownConsumerUserDto.getKnown_consumer_number());
            knownConsumerUserDto.setOtp(token.toString());
            auditUserDetails.saveUserDetails(knownConsumerUserDto, fetchModel);
            contactService.saveContactDetails(knownConsumerUserDto.getKnownConsumerContactDto(), fetchModel);

            for (KnownConsumerContactDto contactDto : knownConsumerUserDto.getKnownConsumerContactDto()) {
                notificationService.sendNotification(contactDto, "otp", "Send OTP", "", token.toString());
            }
        }
        knownConsumerUserDto.setOtp(token.toString());
        return knownConsumerUserDto;
    }

    //this method will fetch the otp and check if it's the same with the one the user has entered.
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Boolean ValidateOTPToken(KnownConsumerUserDto request) throws Exception {
        UpdateResponseDto responseDto = new UpdateResponseDto();
        KnownConsumerUser user = otpRepository.findByKnownConsumerNumberAndOtp(request.getKnown_consumer_number(),request.getOtp());

        Boolean otpValidated = false;
        if ((user != null)){
            if (request.getOtp().equals(user.getOtp())){
                Timestamp now = new Timestamp(System.currentTimeMillis());
                if (compareTwoTimeStamps(now,user.getOtp_created_at()) <= 6) {
                    otpValidated = true;
                }
            }

        }
        return otpValidated;
    }

    public static long compareTwoTimeStamps(Timestamp currentTime, Timestamp oldTime)
    {
        long milliseconds1 = oldTime.getTime();
        long milliseconds2 = currentTime.getTime();

        long diff = milliseconds2 - milliseconds1;

        long diffMinutes = diff / (60 * 1000);


        return diffMinutes;
    }
}
