package nus.iss.ws13.workshop13.services;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.ws13.workshop13.models.Contact;
import nus.iss.ws13.workshop13.respositories.ContactRespo;

@Service
public class ContactService {
    @Autowired
    private ContactRespo contactRespo;

    public void insertContacts(Contact contact){
        contactRespo.insertContacts(contact);
    }

    public Optional<Contact> findContactById(String id){
        return contactRespo.findContactById(id);
    }

    public List<Contact> getListOfContact(){
        return contactRespo.getListOfContact();
    }
}
