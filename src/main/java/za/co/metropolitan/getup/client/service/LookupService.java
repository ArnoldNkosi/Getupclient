package za.co.metropolitan.getup.client.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.metropolitan.getup.client.modelRepository.client.MaritalStatusLookupRepository;
import za.co.metropolitan.getup.client.modelRepository.client.NQFLevelsLookupRepository;
import za.co.metropolitan.getup.client.modelRepository.client.OccupationLookupRepository;
import za.co.metropolitan.getup.client.dto.EducationDetailsDto;
import za.co.metropolitan.getup.client.dto.OccupationDetailsDto;
import za.co.metropolitan.getup.client.helper.ServiceHelper;
import za.co.metropolitan.getup.client.model.client.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class LookupService {

    private static final Logger log = LoggerFactory.getLogger(LookupService.class);

    @Autowired
    private OccupationLookupRepository occupationRepository;

    @Autowired
    private NQFLevelsLookupRepository educationRepository;

    @Autowired
    private MaritalStatusLookupRepository maritalRepository;


    public List<OccupationDetailsDto> getOccupationList(){
       List<OccupationDetailsDto> occupationList = new ArrayList<>();
       List<OccupationLookup> list = occupationRepository.findAll();
       for(OccupationLookup lookup: list){
           if(!lookup.getOccupationType().equals("?")){
               OccupationDetailsDto details = new OccupationDetailsDto();
               details.setOccupation(lookup.getOccupationType());
               details.setOccupationDisplayText(ServiceHelper.titleCaseConversion(lookup.getOccupationType()));
               details.setOccupationCode(lookup.getOccupationTypeCode());
               occupationList.add(details);
           }

       }

       return occupationList;
    }


    public List<EducationDetailsDto> getEducationList(){
        List<EducationDetailsDto> educationList = new ArrayList<>();
        List<NQFLevelsLookup> nqfList = educationRepository.findAll();
        for(NQFLevelsLookup nqf : nqfList){
            EducationDetailsDto educationDetails = new EducationDetailsDto();
            educationDetails.setNqfCode(nqf.getNqfCode());
            educationDetails.setEducationlevel(nqf.getEducationDisplayText());
            educationList.add(educationDetails);
        }

        return educationList;
    }


    public List<String> getMaritalStatusList(){
        List<String> maritalStatusDetailsList = new ArrayList<>();
        List<MaritalStatusLookup> mList = maritalRepository.findAll();
        for(MaritalStatusLookup status: mList){
            maritalStatusDetailsList.add(status.getMaritalStatus());
        }

        return maritalStatusDetailsList;
    }
}
