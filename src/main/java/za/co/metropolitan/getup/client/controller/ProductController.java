package za.co.metropolitan.getup.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.metropolitan.getup.client.dto.PolicyDetailsDto;
import za.co.metropolitan.getup.client.dto.PolicyDocRequest;
import za.co.metropolitan.getup.client.dto.ProductsDto;
import za.co.metropolitan.getup.client.dto.external.PolicyNumber;
import za.co.metropolitan.getup.client.errors.InvalidRequestException;
import za.co.metropolitan.getup.client.service.MetRetailCoreService;
import za.co.metropolitan.getup.client.service.ProductService;
import za.co.metropolitan.getup.client.service.SharedService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/product")

public class ProductController {

        private static final Logger log = LoggerFactory.getLogger(za.co.metropolitan.getup.client.controller.ProductController.class);

        @Autowired
        private SharedService sharedService;

        @Autowired
        private ProductService productService;

        @Autowired
        private MetRetailCoreService metRetailCoreService;

        @CrossOrigin
        @RequestMapping(value = "/findPolicySummaryByIdNumber", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<?> findPolicySummaryByIdNumber(@RequestParam("idnumber") String idnumber) {

            if (idnumber == null || idnumber.equals(""))
                throw new InvalidRequestException("ID Number cannot be null or empty.");

            try {

                List<ProductsDto> products = sharedService.findPolicySummaryByIdNumber(idnumber);

                if(!products.isEmpty()) {
                    log.info("found policies for: " + idnumber);
                    return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
                }else {
                    return new ResponseEntity<>(products, HttpStatus.NO_CONTENT);
                }

            } catch (Exception e) {
                log.info(e.getMessage());
                return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
            }

        }


    }
