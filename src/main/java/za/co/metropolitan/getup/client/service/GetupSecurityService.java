package za.co.metropolitan.getup.client.service;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import za.co.metropolitan.getup.client.dto.CreateUserProfileDto;
import za.co.metropolitan.getup.client.errors.InvalidRequestException;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetupSecurityService {

private static final Logger log = LoggerFactory.getLogger(GetupSecurityService.class);

@Value("${getupsecurity.url}")
private String securityURL;

@Value("${thirdparty_role}")
private String thirdpartyRole;

        private HttpHeaders createClientHttpHeaders() {
            HttpHeaders headers = new HttpHeaders();
            headers.add("User-Agent", "Spring's RestTemplate");
            List<MediaType> mediaTypeList = new ArrayList<MediaType>();
            mediaTypeList.add(MediaType.APPLICATION_JSON);
            headers.setAccept(mediaTypeList);
            headers.setContentType(MediaType.APPLICATION_JSON);

            return headers;
        }

        public RestTemplate restTemplateWithoutSSL() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
            TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

            SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                    .loadTrustMaterial(null, acceptingTrustStrategy)
                    .build();

            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

            CloseableHttpClient httpClient = HttpClients.custom()
                    .setSSLSocketFactory(csf)
                    .build();

            HttpComponentsClientHttpRequestFactory requestFactory =
                    new HttpComponentsClientHttpRequestFactory();

            requestFactory.setHttpClient(httpClient);
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            return restTemplate;
        }

        public ResponseEntity<String> callPostService(String url, HttpHeaders headers, String jsonRequest)
                throws Exception {

            HttpEntity<String> entity = new HttpEntity<String>(jsonRequest, headers);
            RestTemplate restTemplate = this.restTemplateWithoutSSL();
            ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            return result;
        }

        public Boolean autoCreateUser(CreateUserProfileDto request){
            Boolean created = false;

            if(request== null ||
                    StringUtils.isEmpty(request.getRole()) ||
                            !request.getRole().equals(thirdpartyRole))
                throw new InvalidRequestException("invalid user profile request body");

            // setup headers
            HttpHeaders headers = this.createClientHttpHeaders();

            String url = securityURL + "/registerUser";

            Gson gson = new Gson();
            String json = gson.toJson(request);
            // call service

            log.info("AUTO CREATE USER PROFILE " + json);
            ResponseEntity<String> entityResponse = null;
            try {
                entityResponse = this.callPostService(url, headers, json);
            } catch (Exception e) {
                log.info(e.getMessage());
            }

            if(entityResponse != null) {
                log.info("SAVED RESPONSE " + entityResponse.getBody());
                created = true;
            }

            return created;
        }

    }