package za.co.metropolitan.getup.client.service.util.knownconsumer.contact;

import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerContact;
import za.co.metropolitan.getup.client.model.knownconsumer.constants.ContactType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactTypeSequenceNumberGenerator {

    private final Map<ContactType, Integer> maxSequencesNumbersMap = new HashMap<>();

    public ContactTypeSequenceNumberGenerator(List<KnownConsumerContact> contactList){

        try {

            for(KnownConsumerContact dto:contactList){

                ContactType key = ContactType.valueOfLabel(dto.getContact_type());
                Integer contactTypeSeqNumber = dto.getContact_type_seq_number();

                if(maxSequencesNumbersMap.containsKey(key)){
                    Integer maxSequenceNumber = maxSequencesNumbersMap.get(key);
                    if(maxSequenceNumber < contactTypeSeqNumber){
                        maxSequencesNumbersMap.put(key, contactTypeSeqNumber);
                    }
                } else {
                    maxSequencesNumbersMap.put(key, contactTypeSeqNumber);
                }
            }

        }catch (NullPointerException e){
            throw new IllegalArgumentException("Must be constructed with KnownCustomerDto list");
        }
    }

    // Shouldn't be constructed without arguments.
    private ContactTypeSequenceNumberGenerator(){}

    public int getNextSequenceNumber(ContactType contactType){
        if(maxSequencesNumbersMap.containsKey(contactType)){
            int nextSequence = maxSequencesNumbersMap.get(contactType) + 1;
            maxSequencesNumbersMap.put(contactType, nextSequence);
            return nextSequence;
        } else {
            maxSequencesNumbersMap.put(contactType, 1);
            return 1;
        }
    }
}
