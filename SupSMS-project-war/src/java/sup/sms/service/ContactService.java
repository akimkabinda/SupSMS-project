/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.service;

import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import sup.sms.entity.Contact;
import sup.sms.entity.User;
import sup.sms.repository.ContactRepository;

/**
 *
 * @author laurent
 */
@Stateless
public class ContactService{
    
    @EJB
    ContactRepository contactRepository;

    /**
     * See repo
     * @param contact
     * @return 
     */
    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    /**
     * See repo
     * @param contactId
     * @return 
     */
    public Contact find(int contactId) {
        return contactRepository.find(contactId);
    }
    
    /**
     * See repo
     * @param contact
     * @return 
     */
    public boolean delete(Contact contact) {
        try{
            contactRepository.delete(contact);
        }catch(Exception e){
            return false;
        }
        return true;
    }
    
    /**
     * See repo
     * @param user
     * @return 
     */
    public List<Contact> findContactsByUser(User user){
        return contactRepository.findContactsByUser(user);
    }
    
    /**
     * See repo
     * @param contact
     * @return 
     */
    public Contact save(Contact contact){
        return contactRepository.save(contact);
    }
    
    /**
     * See repo
     * @param contact
     * @return 
     */
    public Contact update(Contact contact){
        return contactRepository.update(contact);
    }
    
    /**
     * Validation of a contact object according to annotations
     * @param contact
     * @return a string who contains all the errors separated by <br/>
     */
    public String ValidContactObject(Contact contact){
        ValidatorFactory factory = 
                Validation.buildDefaultValidatorFactory();

        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Contact>> constraintViolations; 
        constraintViolations = validator.validate(contact);

        StringBuilder errors = new StringBuilder();

        if(constraintViolations.size() > 0){
            for (ConstraintViolation<Contact> constraintViolation : constraintViolations) {
                errors.append(constraintViolation.getMessage()).append("<br/>");
            }
        }
        
        return errors.toString();
    }
}
