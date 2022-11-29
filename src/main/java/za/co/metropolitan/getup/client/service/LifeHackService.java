package za.co.metropolitan.getup.client.service;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import za.co.metropolitan.getup.client.modelRepository.client.ClientLifeHackRepository;
import za.co.metropolitan.getup.client.modelRepository.product.ProductLifeHackLookupRepository;
import za.co.metropolitan.getup.client.dto.ClientDetailsDto;
import za.co.metropolitan.getup.client.dto.ClientLifeHackDetailsDto;
import za.co.metropolitan.getup.client.dto.LifeHackResponseDto;
import za.co.metropolitan.getup.client.model.client.ClientLifeHack;
import za.co.metropolitan.getup.client.model.product.ProductLifeHackLookup;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LifeHackService {

    @Autowired
    private SharedService sharedService;
    @Autowired
    private ClientLifeHackRepository clientLifeHackRepository;

    @Autowired
    private ProductLifeHackLookupRepository productLifeHackLookupRepository;


    @Value("${cpb.creditreport.url}")
    private String cpbCreditReportURL;

    private static final Logger log = LoggerFactory.getLogger(LifeHackService.class);


    public List<LifeHackResponseDto> getValidLifeHacksForClient(String clientIdNumber, String productName, Date startPeriod, Date endPeriod) throws Exception{
        log.info("ABOUT TO GET VALID LIFE HACKS FOR CLIENT " + clientIdNumber);

        List<LifeHackResponseDto>  lifeHackList = new ArrayList<>();

        ClientDetailsDto client = sharedService.findClientDetails(clientIdNumber);
        if(client != null){
            List<ClientLifeHackDetailsDto> lifeHacks = client.getLifeHackDetails();

            for(ClientLifeHackDetailsDto details : lifeHacks){
                if(details.getProduct().equals(productName)){
                    LifeHackResponseDto response = new LifeHackResponseDto();
                    response.setLifeHack(details.getLifeHack());

                    log.info("LIFE HACK " + details.getLifeHack());

                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date optedIn =new Date(details.getOptedInDate().getTime());
                    String strOptedIn = dateFormat.format(optedIn);
                    response.setOptedInDate(strOptedIn);



                    if(details.getLifeHack().equals("Be boss with your credit")){
                        Integer creditScore = this.getCreditScore(clientIdNumber);
                        response.setValueType("credit score");
                        if(creditScore != null){
                            response.setValue(creditScore.toString());

                            ClientLifeHack clHack = clientLifeHackRepository.findByIdAndClientId(details.getId(),details.getClientId());
                            clHack.setLifeHackScore(creditScore);
                            Date date = new Date();
                            Timestamp ts=new Timestamp(date.getTime());
                            clHack.setLifeHackScoreAt(ts);


                            Date uploaded =new Date(ts.getTime());
                            String strUploaded = dateFormat.format(uploaded);
                            response.setUploadedDate(strUploaded);

                            clientLifeHackRepository.saveAndFlush(clHack);
                        }



                    }else if(details.getLifeHack().equals("Be an all-star employee")){
                        Timestamp scoreUploadedAtTS = details.getLifeHackScoreAt();
                        Date scoreUploadedAt = null;
                        if(scoreUploadedAtTS != null){
                            scoreUploadedAt = new Date(scoreUploadedAtTS.getTime());
                            String strScoreUploadedAt = dateFormat.format(scoreUploadedAt);
                            response.setUploadedDate(strScoreUploadedAt);
                        }

                        response.setValueType("rating");

//                        Date optedIn =new Date(details.getOptedInDate().getTime());
//                        String strOptedIn = dateFormat.format(optedIn);
//                        response.setOptedInDate(strOptedIn);


                        if(scoreUploadedAt!= null){
                            log.info("Rating uploaded at > " + scoreUploadedAt);
                            log.info("rebate start > " + startPeriod);
                            log.info("rebate end > " + endPeriod);
                            log.info("isAfter " + scoreUploadedAt.after(startPeriod));
                            log.info("isBefore " + scoreUploadedAt.before(endPeriod));
                        }



                        if(scoreUploadedAt!= null && scoreUploadedAt.after(startPeriod) && scoreUploadedAt.before(endPeriod)) {
                            Integer score = details.getLifeHackScore();
                            if (score != null) {
                                response.setValue(score.toString());
                            } else {
                                response.setValue(null);
                            }
                        }
                    }else if(details.getLifeHack().equals("Improved skills for improved performance and new career opportunities")){
                        Timestamp scoreUploadedAtTS = details.getLifeHackScoreAt();
                        Date scoreUploadedAt = null;

                        if(scoreUploadedAtTS != null){
                            scoreUploadedAt = new Date(scoreUploadedAtTS.getTime());
                            String strScoreUploadedAt = dateFormat.format(scoreUploadedAt);
                            response.setUploadedDate(strScoreUploadedAt);

                        }
                        response.setValueType("nqf level");


//                        Date optedIn =new Date(details.getOptedInDate().getTime());
//                        String strOptedIn = dateFormat.format(optedIn);
//                        response.setOptedInDate(strOptedIn);


                        if(scoreUploadedAt!= null) {
                            log.info("nqf level uploaded at > " + scoreUploadedAt);
                            log.info("rebate start > " + startPeriod);
                            log.info("rebate end > " + endPeriod);
                            log.info("isAfter " + scoreUploadedAt.after(startPeriod));
                            log.info("isBefore " + scoreUploadedAt.before(endPeriod));
                        }


                        if(scoreUploadedAt!= null && scoreUploadedAt.after(startPeriod) && scoreUploadedAt.before(endPeriod)) {
                            Integer score = details.getLifeHackScore();
                            if (score != null) {
                                response.setValue(score.toString());
                            } else {
                                response.setValue(null);
                            }
                        }

                    }
                    lifeHackList.add(response);
                }
            }
        }

        return lifeHackList;

    }


    public List<String> getValidLifeHacksForProduct(String productName) {
        log.info("ABOUT TO GET VALID LIFE HACKS FOR PRODUCT " + productName);

        List<String>  lifeHackList = new ArrayList<>();

        List<ProductLifeHackLookup> productList = productLifeHackLookupRepository.findByProduct(productName);
        for(ProductLifeHackLookup hack: productList){
            if(hack.getEndedAt() == null){
              lifeHackList.add(hack.getLifehack());
            }
        }

        return lifeHackList;

    }



    private HttpHeaders createCPBHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Spring's RestTemplate");
        List<MediaType> mediaTypeList = new ArrayList<MediaType>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypeList);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Bean
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

    private Integer getCreditScore(String clientIDNumber) throws Exception{
        Integer creditScore = null;
        try {
            HttpHeaders headers = this.createCPBHeaders();

            String url = cpbCreditReportURL + "?idNumber=" + clientIDNumber;
            String jsonRequest = "{}";
            log.info("URL " + url);
            ResponseEntity<String> entityResponse = this.callPostService(url, headers, jsonRequest);
            log.info("CREDIT SCORE RESPONSE " + entityResponse);
            if (entityResponse != null) {
                JSONObject creditScoreObject = new JSONObject(entityResponse.getBody());
                JSONArray creditScoreList = creditScoreObject.getJSONArray("CreditScoreList");
                JSONObject item = (JSONObject) creditScoreList.get(0);
                creditScore = item.getInt("CreditScore");

            }
        }catch(Exception ex){
            log.info("CANNOT GET CREDIT SCORE FOR THIS USER "  + clientIDNumber) ;
           // ex.printStackTrace();
        }

        return creditScore;
    }

}
