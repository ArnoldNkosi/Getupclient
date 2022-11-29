package za.co.metropolitan.getup.client.service;
import com.google.gson.Gson;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import za.co.metropolitan.getup.client.dto.CreateUserProfileDto;
import za.co.metropolitan.getup.client.dto.ResponseDto;
import za.co.metropolitan.getup.client.model.zendesk.CustomFieldValue;
import za.co.metropolitan.getup.client.model.zendesk.Requester;
import za.co.metropolitan.getup.client.model.zendesk.Ticket;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
@Service
public class zendeskManagementService {
    private static final Logger log = LoggerFactory.getLogger(GetupSecurityService.class);
    @Value("${zendeskticket_endpoint}")
    private String zendeskTicketEndpoint;
    @Value("${zendesk_enquiry_id}")
    private Long zendeskEnquiryId;
    @Value("${zendesk_channel_id}")
    private Long zendeskChannelId;
    @Value("${zendesk_sub_channel_id}")
    private Long zendeskSubChannelId;
    @Value("${zendesk_product_id}")
    private Long zendeskProductId;
    @Value("${client_token}")
    private String clientToken;
    public ResponseDto createClientVerificationError(CreateUserProfileDto request) throws Exception {
        ResponseDto response = new ResponseDto();
        response.setErrorStatus(false);
        Ticket ticket = new Ticket();
        ticket.setStatus("NEW");
        ticket.setType("TASK");
        ticket.setPriority("HIGH");
        //  String subject = "Get Up Client Auto registration, Unable to verify ID Number. " + request.getIdNumber();
        String subject = "Testing DO NOT CONTACT " + request.getIdNumber();
        String description = "Manually identify client's ID Number";
        Requester requester = new Requester();
        requester.setEmail(request.getEmail());
        requester.setLocale_id(1);
        requester.setName(request.getName() + " " + request.getSurname());
        ticket.setRequester(requester);

        List<CustomFieldValue> customFields = new ArrayList<>();
        String[] enquiry = {"id_verification_error"};
        String[] channel = {"website"};
        String[] subChannel = {"Met GetUp Website"};
        String[] product = {"atom"};
        customFields.add(new CustomFieldValue(Long.parseLong(zendeskEnquiryId.toString()), enquiry));
        customFields.add(new CustomFieldValue(Long.parseLong(zendeskChannelId.toString()), channel));
        customFields.add(new CustomFieldValue(Long.parseLong(zendeskSubChannelId.toString()), subChannel));
        customFields.add(new CustomFieldValue(Long.parseLong(zendeskProductId.toString()), product));

        List<String> tags = new ArrayList<>();
        tags.add("human");
        ticket.setCustom_fields(customFields);
        ticket.setDescription(description);
        ticket.setSubject(subject);
        ticket.setIs_public(false);

        String createdTicket = createZendeskTicket(ticket);
        if (createdTicket!=null)

        {
            response.setErrorStatus(false);
            response.setResponseMessage("Ticket has been created");
            log.info("Zendesk ticket has been created ----- ");
            return response;
        }
        else {
            response.setErrorStatus(true);
            response.setResponseMessage("Ticket has not been created");
            log.info("Zendesk ticket has not been created ----- ");
            return response;
        }

    }
    public String createZendeskTicket(Ticket request) throws Exception {
        HttpHeaders headers = this.createClientHttpHeaders(clientToken);
        String url = zendeskTicketEndpoint;
        log.info("URL " + url);
        Gson gson = new Gson();
        String json = gson.toJson(request);
        log.info("CREATE ZENDESK REQUEST " + json);
        json = json.replace("publicComment", "public");
        ResponseEntity<String> entityResponse = this.callPostService(url, headers, json);
        log.info("CREATE ZENDESK RESPONSE " + entityResponse.toString());
        log.info("CREATE ZENDESK RESPONSE STATUS " + entityResponse.getStatusCode());
        JSONObject response = new JSONObject(entityResponse.getBody());
        return String.valueOf(response.getInt("id"));
    }
    public ResponseEntity<String> callPostService(String url, HttpHeaders headers, String jsonRequest) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        HttpEntity<String> entity = new HttpEntity<String>(jsonRequest, headers);
        ResponseEntity<String> result;
        RestTemplate restTemplate = this.restTemplateWithoutSSL();
        result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        return result;
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
    public HttpHeaders createClientHttpHeaders(String clientToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Spring's RestTemplate");
        List<MediaType> mediaTypeList = new ArrayList<MediaType>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypeList);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}