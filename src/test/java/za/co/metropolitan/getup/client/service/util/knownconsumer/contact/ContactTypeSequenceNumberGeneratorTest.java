package za.co.metropolitan.getup.client.service.util.knownconsumer.contact;

import org.junit.jupiter.api.Test;
import za.co.metropolitan.getup.client.model.knownconsumer.KnownConsumerContact;
import za.co.metropolitan.getup.client.model.knownconsumer.constants.ContactType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContactTypeSequenceNumberGeneratorTest {

    @Test
    void handleNullInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ContactTypeSequenceNumberGenerator(null));
    }

    @Test
    void handleEmptyList() {
        ContactTypeSequenceNumberGenerator generator = new ContactTypeSequenceNumberGenerator(new ArrayList<>());
        assertEquals(1, generator.getNextSequenceNumber(ContactType.EMAIL));
        assertEquals(1, generator.getNextSequenceNumber(ContactType.CELLPHONE));
    }

    @Test
    void handleSingleEmailContactType() {
        KnownConsumerContact email = new KnownConsumerContact();
        email.setContact_type(ContactType.EMAIL.label);
        email.setContact_type_seq_number(1);

        List<KnownConsumerContact> contactDtoList = new ArrayList<>();
        contactDtoList.add(email);

        ContactTypeSequenceNumberGenerator generator = new ContactTypeSequenceNumberGenerator(contactDtoList);
        assertEquals(2, generator.getNextSequenceNumber(ContactType.EMAIL));
        assertEquals(1, generator.getNextSequenceNumber(ContactType.CELLPHONE));
    }

    @Test
    void handleMultipleEmailContactTypes(){

        KnownConsumerContact email1 = new KnownConsumerContact();
        email1.setContact_type(ContactType.EMAIL.label);
        email1.setContact_type_seq_number(1);
        KnownConsumerContact email2 = new KnownConsumerContact();
        email2.setContact_type(ContactType.EMAIL.label);
        email2.setContact_type_seq_number(2);
        KnownConsumerContact email3 = new KnownConsumerContact();
        email3.setContact_type(ContactType.EMAIL.label);
        email3.setContact_type_seq_number(3);

        List<KnownConsumerContact> contactDtoList = new ArrayList<>();
        contactDtoList.add(email1);
        contactDtoList.add(email2);
        contactDtoList.add(email3);

        ContactTypeSequenceNumberGenerator generator = new ContactTypeSequenceNumberGenerator(contactDtoList);
        assertEquals(4,generator.getNextSequenceNumber(ContactType.EMAIL));
        assertEquals(1,generator.getNextSequenceNumber(ContactType.CELLPHONE));

    }

    @Test
    void handleSingleCellphoneContactType(){
        KnownConsumerContact email = new KnownConsumerContact();
        email.setContact_type(ContactType.CELLPHONE.label);
        email.setContact_type_seq_number(1);

        List<KnownConsumerContact> contactDtoList = new ArrayList<>();
        contactDtoList.add(email);

        ContactTypeSequenceNumberGenerator generator = new ContactTypeSequenceNumberGenerator(contactDtoList);
        assertEquals(2,generator.getNextSequenceNumber(ContactType.CELLPHONE));
        assertEquals(1, generator.getNextSequenceNumber(ContactType.EMAIL));

    }

    @Test
    void handleMultipleCellphoneContactType(){
        KnownConsumerContact cellphone1 = new KnownConsumerContact();
        cellphone1.setContact_type(ContactType.CELLPHONE.label);
        cellphone1.setContact_type_seq_number(1);
        KnownConsumerContact cellphone2 = new KnownConsumerContact();
        cellphone2.setContact_type(ContactType.CELLPHONE.label);
        cellphone2.setContact_type_seq_number(2);
        KnownConsumerContact cellphone3 = new KnownConsumerContact();
        cellphone3.setContact_type(ContactType.CELLPHONE.label);
        cellphone3.setContact_type_seq_number(3);

        List<KnownConsumerContact> contactDtoList = new ArrayList<>();
        contactDtoList.add(cellphone1);
        contactDtoList.add(cellphone2);
        contactDtoList.add(cellphone3);

        ContactTypeSequenceNumberGenerator generator = new ContactTypeSequenceNumberGenerator(contactDtoList);
        assertEquals(4,generator.getNextSequenceNumber(ContactType.CELLPHONE));
        assertEquals(1,generator.getNextSequenceNumber(ContactType.EMAIL));
    }

    @Test
    void handleMixedContactTypesAndMixedSequenceNumbers(){
        KnownConsumerContact cellphone1 = new KnownConsumerContact();
        cellphone1.setContact_type(ContactType.CELLPHONE.label);
        cellphone1.setContact_type_seq_number(5);
        KnownConsumerContact cellphone2 = new KnownConsumerContact();
        cellphone2.setContact_type(ContactType.CELLPHONE.label);
        cellphone2.setContact_type_seq_number(2);
        KnownConsumerContact cellphone3 = new KnownConsumerContact();
        cellphone3.setContact_type(ContactType.CELLPHONE.label);
        cellphone3.setContact_type_seq_number(7);
        KnownConsumerContact email1 = new KnownConsumerContact();
        email1.setContact_type(ContactType.EMAIL.label);
        email1.setContact_type_seq_number(8);
        KnownConsumerContact email2 = new KnownConsumerContact();
        email2.setContact_type(ContactType.EMAIL.label);
        email2.setContact_type_seq_number(4);
        KnownConsumerContact email3 = new KnownConsumerContact();
        email3.setContact_type(ContactType.EMAIL.label);
        email3.setContact_type_seq_number(4);

        List<KnownConsumerContact> contactDtoList = new ArrayList<>();
        contactDtoList.add(cellphone1);
        contactDtoList.add(cellphone2);
        contactDtoList.add(cellphone3);
        contactDtoList.add(email1);
        contactDtoList.add(email2);
        contactDtoList.add(email3);

        ContactTypeSequenceNumberGenerator generator = new ContactTypeSequenceNumberGenerator(contactDtoList);
        assertEquals(8,generator.getNextSequenceNumber(ContactType.CELLPHONE));
        assertEquals(9,generator.getNextSequenceNumber(ContactType.EMAIL));
    }
}