/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.repository;

import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import sup.sms.entity.Contact;
import sup.sms.entity.Message;
import sup.sms.entity.Message_;
import sup.sms.entity.User;
import sup.sms.entity.User_;

/**
 *
 * @author laurent
 */
@LocalBean
@Stateless
public class MessageRepository {
    @PersistenceContext
    private EntityManager em;
    
    public List<Message> findMessageByUser(User user){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Message> query = 
			criteriaBuilder.createQuery(Message.class);
        Root<Message> message = query.from(Message.class);
        
        Predicate receiverFilter = criteriaBuilder.equal(message.get(Message_.receiver), user.getPhone());
        Predicate transmitterFilter = criteriaBuilder.equal(message.get(Message_.transmitter), user.getPhone());
        Predicate filter = criteriaBuilder.or(receiverFilter, transmitterFilter);
        
        query.where(filter);

        return em.createQuery(query).getResultList();
    }
    
    /**
     * Find all distinct receivers' phone number for a user as transmitter
     * @param user
     * @return 
     */
    public List<String> findDistinctReceiversAsTransmitter(User user){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<Message> message = query.from(Message.class);
        query.select(message.get(Message_.receiver)).distinct(true);
        
        Predicate transmitterFilter = criteriaBuilder.equal(message.get(Message_.transmitter), user.getPhone());
        query.where(transmitterFilter);
        
        return em.createQuery(query).getResultList();
    }
    
    /**
     * Find all distinct transmitters' phone number for a user as receiver
     * @param user
     * @return 
     */
    public List<String> findDistinctTransmittersAsReceiver(User user){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<String> query = criteriaBuilder.createQuery(String.class);
        Root<Message> message = query.from(Message.class);
        query.select(message.get(Message_.transmitter)).distinct(true);
        
        Predicate receiverFilter = criteriaBuilder.equal(message.get(Message_.receiver), user.getPhone());
        query.where(receiverFilter);
        
        return em.createQuery(query).getResultList();
    }
    
    /**
     * Find all messages of a converation between a user and a interlocutor (identified by his phone number)
     * @param user
     * @param interlocutorPhoneNumber
     * @return 
     */
    public List<Message> findConversation(User user, String interlocutorPhoneNumber){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Message> query = criteriaBuilder.createQuery(Message.class);
        Root<Message> message = query.from(Message.class);
        
        Predicate userAsTransmitter = criteriaBuilder.equal(message.get(Message_.transmitter), user.getPhone());
        Predicate userAsReceiver = criteriaBuilder.equal(message.get(Message_.receiver), user.getPhone());
        Predicate userFilter = criteriaBuilder.or(userAsTransmitter, userAsReceiver);
        
        Predicate interlocutorAsTransmitter = criteriaBuilder.equal(message.get(Message_.transmitter), interlocutorPhoneNumber);
        Predicate interlocutorAsReceiver = criteriaBuilder.equal(message.get(Message_.receiver), interlocutorPhoneNumber);
        Predicate interlocutorFilter = criteriaBuilder.or(interlocutorAsTransmitter, interlocutorAsReceiver);
        
        Predicate notDeleted = criteriaBuilder.equal(message.get(Message_.deleted), false);
        
        Predicate finalFilter = criteriaBuilder.and(userFilter, interlocutorFilter, notDeleted);
        
        query.where(finalFilter);
        query.orderBy(criteriaBuilder.asc(message.get(Message_.transmissionDate)));
        
        return em.createQuery(query).getResultList();
    }
    
    public Message find(long messageId){
        return em.find(Message.class, messageId);
    }
    
    public Message save(Message message){
        em.persist(message);
        return message;
    }
}
