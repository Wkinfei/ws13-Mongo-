package nus.iss.ws13.workshop13.utils;

import org.bson.Document;

import nus.iss.ws13.workshop13.models.Contact;

import static nus.iss.ws13.workshop13.utils.Constants.*;

import java.time.ZoneId;

public class Utils {
    
    public static Document toDocument(Contact contact) {
		Document doc = new Document();
		doc.put("name", contact.getName());
        doc.put("email", contact.getEmail());
        doc.put("phoneNumber", contact.getPhoneNumber());
        doc.put("dateOfBirth", contact.getDateOfBirth());
        doc.put("c_id", contact.getC_id());
		return doc;
	}

    //Doc to POJO(Contact)
    public static Contact fromMongoDocument(Document doc) {
        Contact contact = new Contact();
        contact.setName(doc.getString(FIELD_NAME));

        if(null != doc.getDate(FIELD_DATEOFBIRTH)) {
            contact.setDateOfBirth(doc.getDate(FIELD_DATEOFBIRTH).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());            
        } else {
            contact.setDateOfBirth(null);
        }

        contact.setEmail(doc.getString(FIELD_EMAIL));
        contact.setPhoneNumber(doc.getString(FIELD_PHONENUMBER));
        contact.setC_id(doc.getString(FIELD_CID));
        return contact;
    }

    public static Contact fromMongoDocumentShort(Document doc) {
        Contact contact = new Contact();
        contact.setName(doc.getString(FIELD_NAME));
        contact.setC_id(doc.getString(FIELD_CID));
        return contact;
    }

}
