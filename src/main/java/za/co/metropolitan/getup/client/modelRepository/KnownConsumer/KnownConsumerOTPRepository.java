package za.co.metropolitan.getup.client.modelRepository.KnownConsumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerUser;

public interface KnownConsumerOTPRepository extends JpaRepository<KnownConsumerUser, Integer> {
    public KnownConsumerUser findByKnownConsumerNumberAndOtp(String knownConsumerNumber, String otp);
    @Query(value = "SELECT kcu FROM KnownConsumerUser kcu WHERE kcu.knownConsumerNumber = ?1 AND kcu.otp = ?2 ORDER BY kcu.otp_created_at DESC")
    KnownConsumerUser findByOTPForUser(String knownConsumerNumber, String otp);
}