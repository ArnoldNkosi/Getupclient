package za.co.metropolitan.getup.client.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import za.co.metropolitan.getup.client.dto.KnownConsumer.KnownConsumerContactDto;
import za.co.metropolitan.getup.client.dto.KnownConsumer.KnownConsumerDetailsDto;
import za.co.metropolitan.getup.client.dto.KnownConsumer.KnownConsumerMarketConsentDetailsDto;
import za.co.metropolitan.getup.client.dto.UpdateResponseDto;
import za.co.metropolitan.getup.client.errors.InvalidRequestException;
import za.co.metropolitan.getup.client.errors.RecordNotFoundException;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerConsent;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerContact;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerModel;
import za.co.metropolitan.getup.client.modelRepository.KnownConsumer.KnownConsumerContactRepository;
import za.co.metropolitan.getup.client.modelRepository.KnownConsumer.KnownConsumerMarketConsentRepository;
import za.co.metropolitan.getup.client.modelRepository.KnownConsumer.KnownConsumerRepository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KnownConsumerService {
    private static final Logger log = LoggerFactory.getLogger(za.co.metropolitan.getup.client.service.ClientDetailService.class);
    @Autowired
    private KnownConsumerRepository knownConsumerRepository;
    @Autowired
    private KnownConsumerMarketConsentRepository knownConsumerMarketConsentRepository;

    @Autowired
    private KnownConsumerContactService contactService;
    @Autowired
    private KnownConsumerContactRepository contactRepository;

    //Creating the known consumer:
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public String createKnownConsumerIntoDb(KnownConsumerDetailsDto request) throws Exception {
        if(request.getId_number() != null && request.getId_type()==null){
            throw new InvalidRequestException("Id type cannot be null or blank");
        }

        if (request.getKnownConsumerContactDto() != null) {
            String existingKnownConsumerNumber = CheckKnownConsumerDuplicates(request);
            if (existingKnownConsumerNumber != null) {
                return existingKnownConsumerNumber;
            }
        }
        KnownConsumerModel model = new KnownConsumerModel(knownConsumerRepository.getNextKnownConsumerNumber(),
                request.getId_number(),
                request.getId_type(),
                request.getDate_of_birth(),
                request.getFirst_name(),
                request.getSurname(),
                request.getInitials(),
                request.getTitle(),
                request.getGender(),
                request.getMarital_status(),
                request.getEthnicity(),
                request.getEducation_level(),
                request.getGetup_client_number(),
                request.getOriginating_source(),
                request.getUnsubscribed_at(),
                null,
                request.getUpdated_at());

        model.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
        knownConsumerRepository.saveAndFlush(model);


        if (request.getKnownConsumerContactDto() != null) {
            //    KnownConsumerModel fetchModel  = knownConsumerRepository.findByKnownConsumerNumber(model.getKnownConsumerNumber());
            if (request.getKnownConsumerContactDto().size() > 0) {
                KnownConsumerModel fetchKnownConsumerModel = knownConsumerRepository.findByKnownConsumerNumber(model.getKnownConsumerNumber());
                contactService.saveContactDetails(request.getKnownConsumerContactDto(), fetchKnownConsumerModel);
            }
        }

        return model.getKnownConsumerNumber();
    }

    public String CheckKnownConsumerDuplicates(KnownConsumerDetailsDto request) throws Exception {
        String knownConsumerNumber = null;
        for (KnownConsumerContactDto contactDto : request.getKnownConsumerContactDto()) {
            List<KnownConsumerContact> knownConsumerContactDetails = contactRepository.findByContactValueLike(contactDto.getContact_value());
            if (knownConsumerContactDetails.size() > 0) {
                List<KnownConsumerModel> ListOfKnownConsumers = knownConsumerRepository.findByFirstNameAndSurname(request.getFirst_name(), request.getSurname());
                if (ListOfKnownConsumers.size() > 0) {
                    Optional<KnownConsumerModel> knownConsumerModel = ListOfKnownConsumers.stream().findFirst();
                    if(knownConsumerModel.isPresent()){
                        knownConsumerNumber = knownConsumerModel.get().getKnownConsumerNumber();
                        request.setKnown_consumer_number(knownConsumerNumber);
                        updateKnownConsumerIntoDb(request);
                    }
                    return knownConsumerNumber;
                }
            }
        }
        return knownConsumerNumber ;
    }


    //Updating Known Consumer
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateKnownConsumerIntoDb(KnownConsumerDetailsDto knownConsumerDto) throws Exception {
        if(knownConsumerDto.getId_number() != null && knownConsumerDto.getId_type()==null){
            throw new InvalidRequestException("Id type cannot be null or blank");
        }

        KnownConsumerModel getKnownConsumer = knownConsumerRepository.findByKnownConsumerNumber(knownConsumerDto.getKnown_consumer_number());
        if (getKnownConsumer == null) {
            throw new RecordNotFoundException("Known Consumer does not exist ");
        } else {
            getKnownConsumer.setIdNumber(knownConsumerDto.getId_number());
            getKnownConsumer.setId_type(knownConsumerDto.getId_type());
            getKnownConsumer.setUpdated_at(Timestamp.valueOf(LocalDateTime.now()));
            getKnownConsumer.setSurname(knownConsumerDto.getSurname());
            getKnownConsumer.setFirstName(knownConsumerDto.getFirst_name());
            getKnownConsumer.setEthnicity(knownConsumerDto.getEthnicity());
            getKnownConsumer.setGender(knownConsumerDto.getGender());
            getKnownConsumer.setEducation_level(knownConsumerDto.getEducation_level());
            getKnownConsumer.setTitle(knownConsumerDto.getTitle());
            getKnownConsumer.setDate_of_birth(knownConsumerDto.getDate_of_birth());
            getKnownConsumer.setMarital_status(knownConsumerDto.getMarital_status());
            getKnownConsumer.setOriginating_source(knownConsumerDto.getOriginating_source());
            knownConsumerRepository.saveAndFlush(getKnownConsumer);
        }

        if(knownConsumerDto.getKnownConsumerContactDto().size()>0) {
            contactService.saveContactDetails(knownConsumerDto.getKnownConsumerContactDto(), getKnownConsumer);
        }
    }

        @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
        public void createMarketingConsent(KnownConsumerMarketConsentDetailsDto request) throws Exception {

            KnownConsumerModel getKnownConsumer = knownConsumerRepository.findByKnownConsumerNumber(request.getKnown_consumer_number());
            if (getKnownConsumer == null) {
                throw new RecordNotFoundException("Known Consumer does not exist ");
            } else {

                List<KnownConsumerConsent> consentExists = knownConsumerMarketConsentRepository.findByKnownconsumerid(getKnownConsumer.getId());
                if (consentExists.size() > 0) {
                    for (KnownConsumerConsent consent : consentExists) {
                        if (consent.getConsentType() == request.getConsentType()) {
                            consent.setUpdated_at(Timestamp.valueOf(LocalDateTime.now()));
                            consent.setHas_opted_in(Boolean.valueOf((request.getOptedIn().toString().toLowerCase())));
                            knownConsumerMarketConsentRepository.saveAndFlush(consent);
                        }
                    }
                }    else{
                    KnownConsumerConsent knownConsumerConsent = new KnownConsumerConsent(getKnownConsumer.getId(), request.getConsentType(), Boolean.valueOf(request.getOptedIn().toString().toLowerCase()), Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
                    knownConsumerMarketConsentRepository.saveAndFlush(knownConsumerConsent);
                    }
            }
        }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UpdateResponseDto updateKnownConsumerConsent(KnownConsumerMarketConsentDetailsDto request) {
        log.info("CONSENT List  " + request);
        UpdateResponseDto response = new UpdateResponseDto();

        KnownConsumerModel getKnownConsumer = knownConsumerRepository.findByKnownConsumerNumber(request.getKnown_consumer_number());
        List<KnownConsumerConsent> getConsentList = knownConsumerMarketConsentRepository.findByKnownconsumerid(getKnownConsumer.getId());

        try{
            if (getConsentList.size()>0) {
                KnownConsumerConsent knownConsumerConsent = getConsentList.stream().filter(p -> p.getConsentType().equals(request.getConsentType())).findFirst().orElse(null);
                if(knownConsumerConsent!=null){
                    knownConsumerConsent.setHas_opted_in(request.getOptedIn());
                    knownConsumerConsent.setUpdated_at(Timestamp.valueOf(LocalDateTime.now()));
                    knownConsumerMarketConsentRepository.saveAndFlush(knownConsumerConsent);
                }
                else{
                    if(request.getOptedIn()) createMarketingConsent(request);
                }
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            response.setErrorStatus(Boolean.TRUE);
            response.setResponseMessage("Error! Cannot update marketing consent preferences "+ e.getMessage());
        }
        return response;

    }

    //Selecting the KnownConsumer's consent preferences
    public List<KnownConsumerMarketConsentDetailsDto> findMarketConsent(String knownConsumerNumber){
        log.info("getMarketConsent");
        List<KnownConsumerMarketConsentDetailsDto> response = new ArrayList<>();
        KnownConsumerModel getKnownConsumer = knownConsumerRepository.findByKnownConsumerNumber(knownConsumerNumber);
        List<KnownConsumerConsent> getupConsentlist =  knownConsumerMarketConsentRepository.findByKnownconsumerid(getKnownConsumer.getId());

        for ( KnownConsumerConsent k : getupConsentlist) {
            KnownConsumerMarketConsentDetailsDto entry = new KnownConsumerMarketConsentDetailsDto();
            entry.setKnown_consumer_id(k.getKnown_consumer_id());
            entry.setConsentType(k.getConsentType());
            entry.setId(k.getId());
            entry.setOptedIn(k.getHas_opted_in());
            entry.setKnown_consumer_number(getKnownConsumer.getKnownConsumerNumber());
            response.add(entry);
        }
        return response;
    }





    public KnownConsumerDetailsDto createKnownConsumerDetailsDto(KnownConsumerModel getKnownConsumer) {
        KnownConsumerDetailsDto detailsDto = new KnownConsumerDetailsDto();
        detailsDto.setKnown_consumer_number(getKnownConsumer.getKnownConsumerNumber());
        detailsDto.setTitle(getKnownConsumer.getTitle());
        detailsDto.setFirst_name(getKnownConsumer.getFirstName());
        detailsDto.setSurname(getKnownConsumer.getSurname());
        detailsDto.setInitials(getKnownConsumer.getInitials());
        detailsDto.setId_number(getKnownConsumer.getIdNumber());
        detailsDto.setId_type(getKnownConsumer.getId_type());
        detailsDto.setGender(getKnownConsumer.getGender());
        detailsDto.setEthnicity(getKnownConsumer.getEthnicity());
        detailsDto.setOriginating_source(getKnownConsumer.getOriginating_source());
        detailsDto.setMarital_status(getKnownConsumer.getMarital_status());
        detailsDto.setCreated_at(getKnownConsumer.getCreated_at());
        detailsDto.setUpdated_at(getKnownConsumer.getUpdated_at());

        if (getKnownConsumer.getConsent().size() > 0) {
            List<KnownConsumerMarketConsentDetailsDto> consentDetailsDto = new ArrayList<>();
            for (KnownConsumerConsent var : getKnownConsumer.getConsent()) {
                consentDetailsDto.add(new KnownConsumerMarketConsentDetailsDto(var.getConsentType(), var.getHas_opted_in()));
            }
            detailsDto.setKnownConsumerConsents(consentDetailsDto);
        }

        if (getKnownConsumer.getContact().size() > 0) {
            List<KnownConsumerContactDto> contactDtos = new ArrayList<>();
            for (KnownConsumerContact var : getKnownConsumer.getContact()) {
                contactDtos.add(new KnownConsumerContactDto(var.getKnownConsumerId(), var.getContact_type(), var.getContact_type_seq_number(), var.getContact_value(),var.getCreated_at(),var.getUpdated_at()));
            }
            detailsDto.setKnownConsumerContactDto(contactDtos);
        }
        return detailsDto;
    }


    public KnownConsumerDetailsDto SelectKnownConsumerUsingKnownConsumerNumber(String knownConsumerNumber){
        log.info("getKnownConsumerDetails");
        KnownConsumerModel getKnownConsumer = knownConsumerRepository.findByKnownConsumerNumber(knownConsumerNumber);
        if(getKnownConsumer!=null){
            KnownConsumerDetailsDto detailsDto = createKnownConsumerDetailsDto(getKnownConsumer);
            return detailsDto;
        }
        else throw new RecordNotFoundException("Known Consumer NOT FOUND");
    }

    public KnownConsumerDetailsDto SelectKnownConsumerUsingIdNumber(String idNumber) {
        log.info("getKnownConsumerDetails");
        KnownConsumerDetailsDto detailsDto = null;
        KnownConsumerModel getKnownConsumer = knownConsumerRepository.findFirstByIdNumber(idNumber);
        if(getKnownConsumer!=null){
            detailsDto = createKnownConsumerDetailsDto(getKnownConsumer);
        }
        return detailsDto;
    }
}
