package za.co.metropolitan.getup.client.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.metropolitan.getup.client.dto.KnownConsumer.KnownConsumerDetailsDto;
import za.co.metropolitan.getup.client.dto.KnownConsumer.KnownConsumerMarketConsentDetailsDto;
import za.co.metropolitan.getup.client.dto.KnownConsumer.KnownConsumerUserDto;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerConsent;
import za.co.metropolitan.getup.client.service.KnownConsumerService;
import za.co.metropolitan.getup.client.service.OTPTokenService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/KnownConsumer")
public class KnownConsumerController {
    private static final Logger log = LoggerFactory.getLogger(ClientDetailController.class);
    @Autowired
    private KnownConsumerService knownConsumerDetailService;
    @Autowired
    private OTPTokenService otpTokenService;


    @RequestMapping(value = "/createKnownConsumer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createKnownConsumer(@RequestBody KnownConsumerDetailsDto request) throws Exception {
        log.info("KnownConsumerController.createKnownConsumer");
        log.info("Known Consumer Details Dto: " + request);
        if (request != null) {
        String known_consumer_number= knownConsumerDetailService.createKnownConsumerIntoDb(request);
            return new ResponseEntity<>(known_consumer_number,HttpStatus.OK);// return known consumer number as well
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/updateKnownConsumer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KnownConsumerDetailsDto> updateKnownConsumer(@RequestBody KnownConsumerDetailsDto request) throws Exception {
        log.info("KnownConsumerController.updateKnownConsumer");
        log.info("Known Consumer Details Dto: " + request);
          if (request != null) {
            knownConsumerDetailService.updateKnownConsumerIntoDb(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    //Generating a new token

    @RequestMapping(value = "/createotpToken", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KnownConsumerUserDto> createOTPToken(@RequestBody KnownConsumerUserDto request) throws Exception {
        log.info("KnownConsumerController.createOTPToken");
        log.info("KnownConsumerUserDto: " + request);
      if (request != null) {
          KnownConsumerUserDto userDto= otpTokenService.GenerateOTP(request);
          return new ResponseEntity<>(HttpStatus.OK);
        } else {
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/validateotpToken", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Boolean>  ValidateOTPToken(@RequestBody KnownConsumerUserDto request)throws Exception {
        log.info("KnownConsumerController.ValidateOTPToken");
        log.info("KnownConsumerUserDto: " + request);
        if (request != null) {
            Boolean validated = otpTokenService.ValidateOTPToken(request);
            return new ResponseEntity<>(validated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }




    @RequestMapping(value = "/createMarketingConsent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createKnownConsumerConsent(@RequestBody KnownConsumerMarketConsentDetailsDto request) throws Exception {
        log.info("KnownConsumerController.createKnownConsumerConsent");
        log.info("KnownConsumerMarketConsentDetailsDto: " + request);
        if (request != null)
        {
            knownConsumerDetailService.createMarketingConsent(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/updateKnownConsumerConsent", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<KnownConsumerConsent> UpdateKnownConsumerConsent(@RequestBody KnownConsumerMarketConsentDetailsDto request) {
        log.info("KnownConsumerController.UpdateKnownConsumerConsent");
        log.info("KnownConsumerMarketConsentDetailsDto: " + request);
        if (request != null)
        {
            knownConsumerDetailService.updateKnownConsumerConsent(request);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/fetchKnownConsumerConsent")
    public ResponseEntity<List<KnownConsumerMarketConsentDetailsDto>> FetchKnownConsumerConsentList(@RequestParam(value = "knownConsumerNumber") String knownConsumerNumber){
        log.info("KnownConsumerController.FetchKnownConsumerConsentList");
        log.info("knownConsumerNumber: " + knownConsumerNumber);
        if (knownConsumerNumber != null)
        {
            List<KnownConsumerMarketConsentDetailsDto> consent = knownConsumerDetailService.findMarketConsent(knownConsumerNumber);
            return new ResponseEntity<>(consent,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/SelectKnownConsumerDetailsIDNumber")
    public ResponseEntity<KnownConsumerDetailsDto> SelectKnownConsumerDetailsIDNumber(@RequestParam(value = "IDNumber") String idNumber){
        log.info("KnownConsumerController.SelectKnownConsumerDetailsIDNumber");
        log.info("knownConsumerNumber: " + idNumber);
        if (idNumber != null)
        {
            KnownConsumerDetailsDto knownConsumer = knownConsumerDetailService.SelectKnownConsumerUsingIdNumber(idNumber);
            return new ResponseEntity<>(knownConsumer,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/SelectKnownConsumerDetails")
    public ResponseEntity<KnownConsumerDetailsDto> SelectKnownConsumerDetails(@RequestParam(value = "knownConsumerNumber") String knownConsumerNumber){
        log.info("KnownConsumerController.SelectKnownConsumerDetails");
        log.info("knownConsumerNumber: " + knownConsumerNumber);
        if (knownConsumerNumber != null)
        {
            KnownConsumerDetailsDto knownConsumer = knownConsumerDetailService.SelectKnownConsumerUsingKnownConsumerNumber(knownConsumerNumber);
            return new ResponseEntity<>(knownConsumer,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
