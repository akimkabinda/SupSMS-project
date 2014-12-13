/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import sup.sms.entity.Contact;
import sup.sms.repository.ContactRepository;

/**
 *
 * @author laurent
 */
@Stateless
public class ContactService{
    
    @EJB
    ContactRepository contactRepository;

    public Contact addContact(Contact contact) {
        return contactRepository.addContact(contact);
    }

    public Contact find(long contactId) {
        return contactRepository.find(contactId);
    }

    public List<Contact> getClientContacts(long clientId) {
        return contactRepository.getClientContact(clientId);
    }
    
    
}
