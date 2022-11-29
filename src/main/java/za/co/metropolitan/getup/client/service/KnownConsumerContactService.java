package za.co.metropolitan.getup.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import za.co.metropolitan.getup.client.dto.KnownConsumer.KnownConsumerContactDto;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerContact;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerModel;
import za.co.metropolitan.getup.client.model.knownconsumer.constants.ContactType;
import za.co.metropolitan.getup.client.modelRepository.KnownConsumer.KnownConsumerContactRepository;
import za.co.metropolitan.getup.client.service.util.knownconsumer.contact.ContactTypeSequenceNumberGenerator;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class KnownConsumerContactService {

    @Autowired
    private KnownConsumerContactRepository contactRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveContactDetails(List<KnownConsumerContactDto> contactList, KnownConsumerModel model) throws Exception {

        List<KnownConsumerContact> knownConsumerContactDetails = contactRepository.findByKnownConsumerId(model.getId());
        ContactTypeSequenceNumberGenerator sequenceNumberGenerator = new ContactTypeSequenceNumberGenerator(knownConsumerContactDetails);

        for (KnownConsumerContactDto contact : contactList) {

            Optional<KnownConsumerContact> retrievedContact = findContact(contact.getContact_type(), contact.getContact_value(), knownConsumerContactDetails);

            KnownConsumerContact knownConsumerContact;

            if (retrievedContact.isPresent()) {

                knownConsumerContact = retrievedContact.get();
                knownConsumerContact.setUpdated_at(Timestamp.valueOf(LocalDateTime.now()));

            } else {

                knownConsumerContact = new KnownConsumerContact();
                ContactType contactType = ContactType.valueOfLabel(contact.getContact_type());

                knownConsumerContact.setKnownConsumerId(model.getId());
                knownConsumerContact.setContact_value(contact.getContact_value());
                knownConsumerContact.setContact_type(contact.getContact_type());
                knownConsumerContact.setCreated_at(Timestamp.valueOf(LocalDateTime.now()));
                knownConsumerContact.setUpdated_at(Timestamp.valueOf(LocalDateTime.now()));
                knownConsumerContact.setContact_type_seq_number(sequenceNumberGenerator.getNextSequenceNumber(contactType));

            }
            contactRepository.saveAndFlush(knownConsumerContact);
        }
    }

    private Optional<KnownConsumerContact> findContact(String contactType, String contactValue, List<KnownConsumerContact> retrievedContactList){
        return retrievedContactList.stream()
                .filter(k -> k.getContact_type().equalsIgnoreCase(contactType) && k.getContact_value().equalsIgnoreCase(contactValue))
                .findFirst();
    }
}


