package za.co.metropolitan.getup.client.service;


import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import za.co.metropolitan.getup.client.dto.LoginRequest;

import java.util.ArrayList;

import java.util.List;

@Service
public class LoginService {


    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Spring's RestTemplate");
        List<MediaType> mediaTypeList = new ArrayList<MediaType>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypeList);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic LGAg/wihdbFwevLpZJ01tqGm5Pkc3C43ast/bvBAe83yCfqob9t///JxBnVF388E");

        return headers;
    }

    public Boolean faceMatchesID(LoginRequest request) throws Exception{

        Boolean isItAMatch = null;

        String url = "https://1dentity-as-gateway-zan-stg.azurewebsites.net/read/gateway/executeservice?serviceid=cb77dfa6-a299-4451-8de1-7c7f54599848";
        HttpHeaders headers = this.createHttpHeaders();


        JSONObject base64 = new JSONObject();
        JSONObject properties = new JSONObject();
        StringBuffer str = new StringBuffer();
        str.append(request.getBase64Image());

        base64.put("base64image", str.toString());
        properties.put("hasConsent", true);
        properties.put("identificationNumber", request.getIdNumber());
        properties.put("lastName", "");
        properties.put("firstName", "");

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("request_face_verify",base64);
        jsonRequest.put("gateway_generic_properties",properties);

        HttpEntity<String> entity = new HttpEntity<>(jsonRequest.toString(), headers);
         RestTemplate restTemplate = new RestTemplate();
//        System.out.println("URL " + url);
//        System.out.println("RESULT " + jsonRequest.toString());

        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//        System.out.println("RESULT CODE " + result.getStatusCode());
//        System.out.println("RESULT " + result.getBody());
        if(result.getStatusCode().toString().equals("200 OK")) {
            String responseObj = result.getBody();
            JSONObject jsonObj = new JSONObject(responseObj);
           try{
               isItAMatch = jsonObj.getJSONObject("biometric").getJSONObject("response_face_verify").getBoolean("isIdentical");
           }catch(Exception e){
               isItAMatch = false;
           }

//            System.out.println("MATCH " + isItAMatch);
        }

        return isItAMatch;
    }
}
