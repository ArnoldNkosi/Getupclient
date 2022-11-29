package za.co.metropolitan.getup.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.metropolitan.getup.client.dto.EducationDetailsDto;
import za.co.metropolitan.getup.client.dto.OccupationDetailsDto;
import za.co.metropolitan.getup.client.service.LookupService;


import java.util.List;

@RestController
@RequestMapping(value = "/lookups")
public class LookupController {


    @Autowired
    private LookupService lookupService;

    @CrossOrigin
    @RequestMapping(value = "/getOccupationList", method = RequestMethod.GET)
    public List<OccupationDetailsDto> getOccupationList() throws Exception {
            List<OccupationDetailsDto> occupations = lookupService.getOccupationList();
            return occupations;
    }


    @CrossOrigin
    @RequestMapping(value = "/getEducationLevelsList", method = RequestMethod.GET)
    public List<EducationDetailsDto> getEducationList() throws Exception {
        List<EducationDetailsDto> educationList = lookupService.getEducationList();
        return educationList;
    }



    @CrossOrigin
    @RequestMapping(value = "/getMaritalStatusList", method = RequestMethod.GET)
    public List<String> getMaritalStatusList() throws Exception {
        List<String> maritalStatusist = lookupService.getMaritalStatusList();
        return maritalStatusist;
    }

}
