package za.co.metropolitan.getup.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.metropolitan.getup.client.dto.ClientMarketConsentDetailsDto;
import za.co.metropolitan.getup.client.modelRepository.client.*;
import za.co.metropolitan.getup.client.modelRepository.product.ProductLifeHackLookupRepository;
import za.co.metropolitan.getup.client.dto.ClientDetailsDto;
import za.co.metropolitan.getup.client.dto.ClientListRequestDto;
import za.co.metropolitan.getup.client.errors.InvalidRequestException;
import za.co.metropolitan.getup.client.model.client.*;
import za.co.metropolitan.getup.client.model.product.ProductLifeHackLookup;

import java.math.BigInteger;
import java.util.List;

@Service
public class ValidationService {

 /** CONTROLLER */
 // ClientDetailController

 @Autowired
 private PrefContactMediumLookupRepository prefContactMediumLookupRepository;

 @Autowired
 private ClientIdTypeLookupRepository idTypeRepository;

 @Autowired
 private ClientTitleLookupRepository titleRepository;

 @Autowired
 private GenderLookupRepository genderRepository;

 @Autowired
 private MaritalStatusLookupRepository maritalRepository;

 @Autowired
 private EthnicityLookupRepository ethnicityRepository;

 @Autowired
 private NQFLevelsLookupRepository nqfRepository;

 @Autowired
 private MarketingConsentLookupRepository consentLookupRepository;

 @Autowired
 private ContactTimeLookupRepository contactTimeLookupRepository;

 @Autowired
 private ProductLifeHackLookupRepository lifeHackProductLookupRepository;

 @Autowired
 private EmploymentTypeLookupRepository employmentTypeRepository;

 @Autowired
 private IndustryLookupRepository industryRepository;

 @Autowired
 private OccupationLookupRepository occupationRepository;

 @Autowired
 private ContactTypeLookupRepository contactTypeRepository;


 public boolean isNumeric(String strNum) {
  if (strNum == null) {
   return false;
  }
  try {
   double d = Double.parseDouble(strNum);
  } catch (NumberFormatException nfe) {
   return false;
  }
  return true;
 }

 private Boolean validateIdNumber(BigInteger identities){
  char[] idchars = identities.toString().toCharArray();
  int sum = 0;
  // loop over each digit right-to-left, including the check-digit
  for (int i = 1; i <= idchars.length; i++) {
   int digit = Character.getNumericValue(idchars[idchars.length - i]);
   if ((i % 2) != 0) {
    sum += digit;
   } else {
    sum += digit < 5 ? digit * 2 : digit * 2 - 9;
   }
  }
  return (sum % 10) == 0;
 }

 // findClientById
 public boolean isValidClientId(String clientId) {
  if (clientId == null || clientId.equals("")) {
   throw new InvalidRequestException("clientId cannot be null or empty");
  }

  if(!this.isNumeric(clientId))
   throw new InvalidRequestException("clientId must be numbers only");

  return true;
 }

 // findClientByIdNumber
 public boolean isValidClientIdNumber(String idNumber) {

  if (idNumber == null || idNumber.equals("")) {
   throw new InvalidRequestException("Client ID number cannot be null or empty");
  }

  if(!this.isNumeric(idNumber))
   throw new InvalidRequestException("ID number must be numbers only");

  BigInteger id = new BigInteger(idNumber);
  if(!this.validateIdNumber(id))
   throw new InvalidRequestException("ID number is invalid");

  return true;
 }

 // searchForClient
 public boolean hasValidSearchRequirements(ClientListRequestDto request) {
  if (request.getIdNumber().equals("") && request.getSurname().equals("") && request.getName().equals("")
      && request.getInitials().equals("")) {
   throw new InvalidRequestException(
       "Client ID number or Client name, surname and initials must be provided in order to search");
  }
  return true;
 }

 // createGetupClient
 public boolean isValidClientIdType(ClientDetailsDto request) {
  if (request.getIdType() == null || request.getIdType().equals("")) {
   throw new InvalidRequestException("ID type cannot be null or empty");
  } else {
   ClientIdTypeLookup type = idTypeRepository.findByIdType(request.getIdType());
   if (type == null) {
    throw new InvalidRequestException("ID type must be 'National ID' or 'Passport'");
   }
  }
  return true;
 }

 // createGetupClient
 public boolean isValidDateOfBirth(ClientDetailsDto request) {
  if (request.getDateOfBirth() == null || request.getDateOfBirth().equals("")) {
   throw new InvalidRequestException("Date of birth cannot be null or empty");
  } else {
   // todo date format validation
  }
  return true;
 }

 // createGetupClient
 public boolean isValidFirstName(ClientDetailsDto request) {
  if (request.getFirstName() == null || request.getFirstName().equals("")) {
   throw new InvalidRequestException("First name cannot be null or empty");
  }
  return true;
 }

 // createGetupClient
 public boolean isValidSurname(ClientDetailsDto request) {
  if (request.getSurname() == null || request.getSurname().equals("")) {
   throw new InvalidRequestException("Surname cannot be null or empty");
  }
  return true;
 }

 // createGetupClient
 public boolean isValidInitials(ClientDetailsDto request) {
  if (request.getInitials() == null || request.getInitials().equals("")) {
   throw new InvalidRequestException("Initials cannot be null or empty");
  }
  return true;
 }

 // createGetupClient
 public boolean isValidTitle(ClientDetailsDto request) {
  if (request.getTitle() == null || request.getTitle().equals("")) {
   throw new InvalidRequestException("Title cannot be null or empty");
  }
  ClientTitleLookup title = titleRepository.findByTitle(request.getTitle());
  if (title == null) {
   throw new InvalidRequestException("Title must be 'Miss','Mr','Mrs','Ms', 'Sir' or 'Dr'");
  }
  return true;
 }

 // createGetupClient
 public boolean isValidGender(ClientDetailsDto request) {
  if (request.getGender() == null || request.getGender().equals("")) {
   throw new InvalidRequestException("Gender cannot be null or empty");
  } else {
   String genderStr = request.getGender();
   request.setGender(genderStr.substring(0, 1).toUpperCase() + genderStr.substring(1));
   GenderLookup gender = genderRepository.findByGender(request.getGender());
   if (gender == null) {
    throw new InvalidRequestException("Gender must be 'Male' or 'Female'");
   }
  }
  return true;
 }

 // createGetupClient
 public boolean isValidMaritalStatus(ClientDetailsDto request) {
  String maritalStatusStr = request.getMaritalStatus();
  request.setMaritalStatus(maritalStatusStr.substring(0, 1).toUpperCase() + maritalStatusStr.substring(1));
  if (request.getMaritalStatus() == null || request.getMaritalStatus().equals("")) {
   throw new InvalidRequestException("Marital status cannot be null or empty");
  } else {
   MaritalStatusLookup maritalStatus = maritalRepository.findByMaritalStatus(request.getMaritalStatus());
   if (maritalStatus == null) {
    throw new InvalidRequestException(
        "Marital Status must be 'Divorced','Separated','Single','Widowed','Married' or 'Unknown'");
   }
  }
  return true;
 }

 // createGetupClient
 public boolean isValidEthnicity(ClientDetailsDto request) {
  if (request.getEthnicity() == null || request.getEthnicity().equals("")) {
//			throw new InvalidRequestException("Ethnicity cannot be null or empty");
  } else {
   EthnicityLookup ethnicity = ethnicityRepository.findByEthnicity(request.getEthnicity());
   if (ethnicity == null) {
    throw new InvalidRequestException("Ethnicity must be 'Black','Coloured','Indian','Asian' or 'White'");
   }
  }
  return true;
 }

 // createGetupClient
 public boolean isValidEducationLevel(ClientDetailsDto request) {
  if (request.getEducationLevel() == null || request.getEducationLevel().equals("")) {
//			 throw new InvalidRequestException("Education level cannot be null or empty");
  } else {
   NQFLevelsLookup education = nqfRepository.findByEducationDisplayText(request.getEducationLevel());
   if (education == null) {
    throw new InvalidRequestException(
        "Education must be 'Not Matriculated / Up to Grade 9','Not Matriculated / Up to Grade 10',"
            + "'Not Matriculated / Up to Grade 11 / N3','Matriculated / Up to Grade 12 / N4 ','Higher Certificates / N5 / N6' , 'National Diploma / Advanced Certificates','Bachelor's Degree / Advanced Diploma / Post Graduate Certificate / B-Tech'"
            + ",'Honours Degree / Post Graduate Diploma / Professional Qualification','Master's Degree' or 'Doctor's Degree'");
   }
  }
  return true;
 }

 // createGetupClient
 public boolean isValidUserSignOn(ClientDetailsDto request) {
  if (request.getUserSignOn() == null || request.getUserSignOn().equals("")) {
   throw new InvalidRequestException("User signon cannot be null or empty");
  }
  return true;
 }

 // marketing consent
 public boolean isValidMarketingConsent(String consentType) {
  if(consentLookupRepository.findByConsentType(consentType) == null){
   throw new InvalidRequestException(
     "Marketing Consent type is not valid");
  }

//  if (consentType == null || consentType.equals("")) {
//   throw new InvalidRequestException(
//       "Marketing Consent type  must be 'New Solutions' or 'Share Info Internally'");
//  } else {
//   if (!(consentType.contentEquals(new StringBuffer("New Solutions")))
//       && !(consentType.contentEquals(new StringBuffer("Share Info Internally")))) {
//    throw new InvalidRequestException(
//        "Marketing Consent type  must be 'New Solutions' or 'Share Info Internally'");
//   }
//  }

  return true;
 }

 // marketing consent
 public boolean isValidMarketingConsentRequest(List<ClientMarketConsentDetailsDto> request) {

  List<MarketingConsentLookup> lookupList = consentLookupRepository.findAll();

  String fieldsNotFound= "";

  boolean error = false;

    if(!request.isEmpty()) {

       for (MarketingConsentLookup c : lookupList) {

             if (!request.stream().anyMatch(o -> o.getConsentType().equals(c.getConsentType()))) {

              error = true;
              fieldsNotFound += c.getConsentType() + ", " ;

             }

          }
    }

    if(error)
     throw new InvalidRequestException("Require missing Consent Type/s from request: " +fieldsNotFound.substring(0, fieldsNotFound.length() - 2) );


   for (ClientMarketConsentDetailsDto c : request) {

     this.isValidMarketingConsent(c.getConsentType());

   }

  return true;
 }


  // client validation
 public boolean isValidPersonClient(ClientDetailsDto request) {
  if (isValidClientIdNumber(request.getIdNumber()) && isValidClientIdType(request) && isValidDateOfBirth(request)
      && isValidFirstName(request) && isValidSurname(request) && isValidInitials(request)
      && isValidTitle(request) && isValidGender(request) && isValidMaritalStatus(request)
      && isValidEthnicity(request) && isValidEducationLevel(request) && isValidUserSignOn(request)) {
   return true;
  } else {
   return false;
  }
 }

 //////////////////////////////////////////////////////////////////////////////////////////
 // marketing consent
 public boolean isValidMarketingConsentLookup(String consentType) {
  if (consentType == null || consentType.equals("")) {
   throw new InvalidRequestException(
       "Marketing Consent type  must be 'New Solutions' or 'Share Info Internally'");
  } else {
   MarketingConsentLookup foundConsentType = consentLookupRepository.findByConsentType(consentType);
   if (foundConsentType == null) {
    throw new InvalidRequestException(
        "Marketing Consent type  must be 'New Solutions' or 'Share Info Internally'");
   }
  }
  return true;
 }

 // contact time
 public boolean isValidContactTimeLookup(String contactTime) {
  if (contactTime == null || contactTime.equals("")) {
   throw new InvalidRequestException(
       "Preferred Contact Time must be, Morning or Mid-day or Afternoon or Evening.");
  } else {
   ContactTimeLookup foundContactTime = contactTimeLookupRepository.findByContactTime(contactTime);
   if (foundContactTime == null) {
    throw new InvalidRequestException(
            "Preferred Contact Time must be, Morning or Mid-day or Afternoon or Evening.");
   }
  }
  return true;
 }

 // life hack product
 public boolean isValidLifeHackProductLookup(String product, String lifeHack) {
  if (product == null || product.equals("") || lifeHack == null || lifeHack.equals("")) {
   throw new InvalidRequestException("Product and/or lifeHack is invalid.");
  } else {
   ProductLifeHackLookup foundLifeHack = lifeHackProductLookupRepository.findByProductAndLifehack(product,
       lifeHack);
   if (foundLifeHack == null) {
    throw new InvalidRequestException(
        "Invalid product/lifehack combination, please call the getProductLifeHacks API for valid combinations");
   }
  }
  return true;
 }

 // employment type
 public boolean isValidEmploymentTypeLookup(String employmentType) {
  if (employmentType == null || employmentType.equals("")) {
   throw new InvalidRequestException("Employment type is invalid.");
  } else {
   EmploymentTypeLookup foundEmpType = employmentTypeRepository.findByEmploymentType(employmentType);
   if (foundEmpType == null) {
    throw new InvalidRequestException(
        "Employment type  must be 'Permanently Employed', 'Self-Employed' or 'Contractor'");
   }
  }
  return true;
 }

 // industry
 public boolean isValidIndustryLookup(String industry) {
  if (industry == null || industry.equals("")) {
   throw new InvalidRequestException("Employment type is invalid.");
  } else {
   IndustryLookup industryLookup = industryRepository.findByMetIndustryName(industry);
   if (industryLookup == null) {
    throw new InvalidRequestException(
        "Industry is invalid, please call the getIndustryList API for valid industries");
   }
   return true;
  }
 }

 // occupation
 public boolean isValidOccupationLookup(String occupation) {
  if (occupation == null || occupation.equals("")) {
   throw new InvalidRequestException("Occupation is invalid.");
  } else {
   OccupationLookup occupationLookup = occupationRepository.findByOccupationType(occupation);
   if (occupationLookup == null) {
    throw new InvalidRequestException(
        "Occupation is invalid, please call the getOccupationList API for valid occupations");
   }
   return true;
  }
 }

 // contact type
 public boolean isValidContactTypeLookup(String contactType) {
  if (contactType == null || contactType.equals("")) {
   throw new InvalidRequestException("Contact type is invalid.");
  } else {
   ContactTypeLookup cType = contactTypeRepository.findByContactType(contactType);
   if (cType == null) {
    throw new InvalidRequestException("Contact type  must be 'Cellphone' or 'Email Address'");
   }
   return true;
  }
 }

 //preference medium
 public boolean isValidPrefMediumLookup(String medium) {
  if (medium == null || medium.equals("")) {
   throw new InvalidRequestException("contact medium preference required.");
  } else {
    PrefContactMediumLookup cType = prefContactMediumLookupRepository.findByMedium(medium);
   if (cType == null) {
    throw new InvalidRequestException("Invalid contact medium type/s'");
   }
   return true;
  }
 }



}
