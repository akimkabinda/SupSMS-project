/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.repository;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import sup.sms.entity.Contact;
import sup.sms.entity.Contact_;
import sup.sms.entity.User;
import sup.sms.entity.User_;

/**
 *
 * @author laurent
 */
@Stateless
@LocalBean
public class ContactRepository {
    @PersistenceContext
    private EntityManager em;
    
    public Contact addContact(Contact contact){
        em.persist(contact);
        return contact;
    }
    
    public Contact find(long contactId){
        return em.find(Contact.class, contactId);
    }
    
    public List<Contact> getClientContact(long clientId){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Contact> query = 
			criteriaBuilder.createQuery(Contact.class);
        Root<Contact> contact = query.from(Contact.class);
        
        Predicate contactOwnerFilter = criteriaBuilder.equal(contact.get(Contact_.owner), clientId);
        
        query = query.where(contactOwnerFilter);

        return em.createQuery(query).getResultList();
    }
}
