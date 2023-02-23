package nus.iss.ws13.workshop13.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import nus.iss.ws13.workshop13.models.Contact;
import nus.iss.ws13.workshop13.services.ContactService;

@Controller
@RequestMapping("/")
public class ContactController {
    @Autowired
    private ContactService contactService;
    
    @GetMapping
    public String getForm(Model model){
        model.addAttribute("contact", new Contact());
        return "form";
    }
    // public String getForm(Contact contact){
    //     return "form";
    // }

    @PostMapping("/contact")
    public String getContact(@Valid @ModelAttribute Contact contact,BindingResult bindingResult, Model model){
        
        if(bindingResult.hasErrors()){
            // model.addAttribute("a", contact);
            System.out.println("error: " + contact);
            return "form";
        }
        System.out.println("Contact: " + contact);
        //TODO: save to mongodb
        contactService.insertContacts(contact);
       
        return "contact";
    }

    @GetMapping("/contact/{id}")
    public String getContactById(@PathVariable String id, Model model){
        Optional<Contact> contact = contactService.findContactById(id);
        model.addAttribute("contact", contact.get());
        return "contact";
    }

    @GetMapping("/list")
        public String getListOfId(@ModelAttribute Contact contact,Model model){
           List<Contact> contacts = contactService.getListOfContact();
           model.addAttribute("contacts", contacts);
            return "list";
        }
    
}
