package za.co.metropolitan.getup.client.service;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import za.co.metropolitan.getup.client.dto.KnownConsumer.KnownConsumerContactDto;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNumeric;
@Service
public class NotificationService {


    @Value("${smsNotification_endpoint.url}")
    private String smsNotificationEndpoint;
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    public void sendNotification(KnownConsumerContactDto knownConsumerDto, String msgType,
                                 String subject, String url, String key)  throws Exception {
        ///determine user request messaging option, email or cell
        String stringEncoded = URLEncoder.encode(url, "UTF-8");

        String contactValue = knownConsumerDto.getContact_value();
        if (isNumeric(contactValue)) {
            //send sms
            this.sendSms(msgType, contactValue, stringEncoded, key, subject);
        }
    }
    public ResponseEntity<String> sendSms(String msgtype, String cellnumber, String url, String key, String subject) throws Exception {
        String jsonRequest = null;

        if (StringUtils.isEmpty(cellnumber)) {
            throw new Exception("cell number is required");
        }
        String smsMsg = this.template_smsMsg(msgtype, url, key, cellnumber);
        // setup headers
        HttpHeaders headers = createHttpHeaders();
        ResponseEntity<String> entityResponse = null;
        entityResponse = callGetSmsService(smsNotificationEndpoint, cellnumber.trim(), smsMsg, headers);
        return entityResponse;
    }

    public static String template_smsMsg(String msgtype, String url, String key, String Username) {
        String smsbody = null;
        if (msgtype.equals("otp")) {
            smsbody = "Metropolitan Getup OTP:"+ key + " to verify your cell number. "
                    + "This OTP expires after 6 minutes but, you can request to resend the OTP.";
        }
        return smsbody;
    }

    public static HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Spring's RestTemplate");
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypeList);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccessControlAllowOrigin("*");
        return headers;
    }

    public static ResponseEntity<String> callGetSmsService(String url, String cell, String sms, HttpHeaders headers)	throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String uri = url.concat("PhoneNumber="+cell+"&MessageText="+sms+"&Notification=IWEB");
        log.info("resttemplate url = " + uri);
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        log.info("resttemplate result = " + result);
        return result;
    }
}
