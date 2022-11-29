package za.co.metropolitan.getup.client.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.metropolitan.getup.client.dto.ClientDetailsDto;
import za.co.metropolitan.getup.client.dto.LoginRequest;
import za.co.metropolitan.getup.client.dto.LoginResponse;
import za.co.metropolitan.getup.client.service.ClientDetailService;
import za.co.metropolitan.getup.client.service.LoginService;
import za.co.metropolitan.getup.client.service.SharedService;

@RestController
@RequestMapping(value = "/client")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService registrationService;

    @Autowired
    private SharedService detailsService;


    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public LoginResponse login(@RequestBody LoginRequest request) throws Exception {

        LoginResponse response = new LoginResponse();
        ClientDetailsDto client = null;

        //check if id is an existing client
        try {
            client = detailsService.findClientDetails(request.getIdNumber());
        }catch(Exception e){
            response.setClient(false);
        }


        //if client exists
        if(client != null) {
            response.setName(client.getFirstName());
            response.setSurname(client.getSurname());
            response.setClient(true);

        //check that face matches ID
         response.setFaceAndIDMatch(registrationService.faceMatchesID(request));

         if(response.getFaceAndIDMatch()){
             response.setLoginSucceeded(true);
         }else{
             response.setLoginSucceeded(false);
         }

//        if(response.getFaceAndIDMatch()){
//            System.out.println("MATCHED" + client.getClientID());
//
//                //save face to client
////                byte[] encodedImg = Base64.getEncoder().encode(request.getBase64Image().getBytes());
////                Boolean saved = detailsService.saveFaceToClient(client.getClientID(),encodedImg);
////                //byte[] decodedString = Base64.getDecoder().decode(new String(name).getBytes("UTF-8"));
//             //   response.setLoginSucceeded(saved);
//
//
//
//        }

        }else{
            response.setClient(false);
        }
        return response;
    }


}
