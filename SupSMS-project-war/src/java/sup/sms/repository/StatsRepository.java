/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.repository;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import sup.sms.entity.Message;
import sup.sms.entity.User;
import sup.sms.entity.User_;

/**
 *
 * @author laurent
 */
@Stateless
@LocalBean
public class StatsRepository {
    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Count SupSMS users
     * @return 
     */
    public long countUsers(){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<User> user = query.from(User.class);
        query.select(criteriaBuilder.count(user));
        Predicate notDeleted = criteriaBuilder.isFalse(user.get(User_.deleted));
        query.where(notDeleted);
        
        return em.createQuery(query).getSingleResult();
    }
    
    /**
     * Count SupSMS messages
     * @return 
     */
    public long countMessages(){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        query.select(criteriaBuilder.count(query.from(Message.class)));
        
        return em.createQuery(query).getSingleResult();
    }
}
