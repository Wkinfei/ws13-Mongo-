package nus.iss.ws13.workshop13.respositories;

import static nus.iss.ws13.workshop13.utils.Constants.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import nus.iss.ws13.workshop13.models.Contact;
import nus.iss.ws13.workshop13.utils.Utils;

@Repository
public class ContactRespo {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void insertContacts(Contact contact){

        String c_id = UUID.randomUUID().toString().substring(0, 8);
        contact.setC_id(c_id);
        Document doc = Utils.toDocument(contact);
        mongoTemplate.insert(doc, COLLECTION_CONTACTS);
        
    }

    public Optional<Contact> findContactById(String id){
        /*
         db.getCollection("contacts").find({c_id:"a584f9f0"})
         */

        Criteria criterial = Criteria.where(FIELD_CID).is(id);
        Query query = Query.query(criterial);

        Document result = mongoTemplate.findOne(
        query, Document.class, COLLECTION_CONTACTS);

        Contact contact = result.isEmpty() ? null : Utils.fromMongoDocument(result);

        return Optional.ofNullable(contact);  
    }

    public List<Contact> getListOfContact(){
        // db.getCollection("contacts").find({},{name: 1, c_id:1, _id: 0});
        Query query = Query.query(Criteria.where(""));
        query.fields()
                .exclude(FIELD_OID)
                .include(FIELD_NAME,FIELD_CID);
        List<Document> ListOfContact = mongoTemplate.find(
            query,Document.class,COLLECTION_CONTACTS);
            
        List<Contact> contacts = new LinkedList<>();
        for (Document doc : ListOfContact) {
            System.out.println(">>>> \n\n\n\n\n\n" + doc.toJson());
            contacts.add(Utils.fromMongoDocument(doc));
        }

        // List<Contact> contacts = new LinkedList<>();
        // ListOfContact.forEach(x -> contacts.add(Utils.fromMongoDocument(x)));

        // List<Contact> contacts = ListOfContact.stream().map(x -> Utils.fromMongoDocument(x)).toList();

        return contacts;
    }
}
