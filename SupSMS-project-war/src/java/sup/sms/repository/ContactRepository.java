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

/**
 *
 * @author laurent
 */
@Stateless
@LocalBean
public class ContactRepository {
    @PersistenceContext
    private EntityManager em;
    
    /**
     * find a contact by its id
     * @param contactId
     * @return 
     */
    public Contact find(int contactId){
        return em.find(Contact.class, contactId);
    }
    
    /**
     * Find user's contacts
     * @param owner
     * @return 
     */
    public List<Contact> findContactsByUser(User owner){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Contact> query = 
			criteriaBuilder.createQuery(Contact.class);
        Root<Contact> contact = query.from(Contact.class);
        
        Predicate ownerFilter = criteriaBuilder.equal(contact.get(Contact_.contactOwner), owner);
        Predicate notDeletedFilter = criteriaBuilder.equal(contact.get(Contact_.contactDeleted), false);
        Predicate filter = criteriaBuilder.and(ownerFilter, notDeletedFilter);
        
        query.where(filter);

        return em.createQuery(query).getResultList();
    }
    
    /**
     * Delete a contact
     * @param contact 
     */
    public void delete(Contact contact){
        contact.setContactDeleted(true);
        em.merge(contact);
    }
    
    /**
     * save a contact
     * @param contact
     * @return 
     */
    public Contact save(Contact contact){
        em.persist(contact);
        return contact;
    }
    
    /**
     * update a contact
     * @param contact
     * @return 
     */
    public Contact update(Contact contact){
        em.merge(contact);
        return contact;
    }
}
