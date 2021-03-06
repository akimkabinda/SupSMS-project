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
import sup.sms.entity.User;
import sup.sms.entity.User_;

/**
 *
 * @author laurent
 */
@Stateless
@LocalBean
public class UserRepository{

    @PersistenceContext
    private EntityManager em;
    
    /**
     * find user by phone and password
     * @param phone
     * @param password
     * @return 
     */
    public List<User> findByPhoneAndPassword(String phone, String password) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = 
			criteriaBuilder.createQuery(User.class);
        Root<User> user = query.from(User.class);
        
        Predicate phoneFilter = criteriaBuilder.equal(user.get(User_.phone), phone);
        Predicate passwordFilter = criteriaBuilder.equal(user.get(User_.password), password);
        Predicate notDeleted = criteriaBuilder.equal(user.get(User_.deleted), false);
        Predicate filter = criteriaBuilder.and(phoneFilter, passwordFilter, notDeleted);
        
        query.where(filter);

        return em.createQuery(query).getResultList();
    }
    
    /**
     * count user with a special phone number
     * @param phone
     * @return 
     */
    public long countByPhone(String phone){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        query.select(criteriaBuilder.count(query.from(User.class)));
        Root<User> user = query.from(User.class);
        Predicate phoneFilter = criteriaBuilder.equal(user.get(User_.phone), phone);
        Predicate notDeleted = criteriaBuilder.equal(user.get(User_.deleted), false);
        Predicate filter = criteriaBuilder.and(phoneFilter, notDeleted);
        query.where(filter);
        
        return em.createQuery(query).getSingleResult();
    }
    
    /**
     * save new user
     * @param user
     * @return 
     */
    public User save(User user){
        em.persist(user);
        return user;
    }
    
    /**
     * update a user
     * @param user
     * @return 
     */
    public User update(User user){
        em.merge(user);
        return user;
    }
    
    /**
     * Find user by its id
     * @param id
     * @return 
     */
    public User find(long id){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> user = query.from(User.class);
        Predicate idFilter = criteriaBuilder.equal(user.get(User_.id), id);
        Predicate notDeleted = criteriaBuilder.equal(user.get(User_.deleted), false);
        Predicate filter = criteriaBuilder.and(idFilter, notDeleted);
        query.where(filter);
        try{
            return em.createQuery(query).getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
    
    /**
     * find all users by their id
     * @return 
     */
    public List<User> findAll(){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = 
			criteriaBuilder.createQuery(User.class);
        Root<User> user = query.from(User.class);
        Predicate notDeleted = criteriaBuilder.equal(user.get(User_.deleted), false);
        Predicate filter = criteriaBuilder.and(notDeleted);
        query.where(filter);
        return em.createQuery(query).getResultList();
    }
    
    /**
     * delete user
     * @param user 
     */
    public void delete(User user){
        user.setDeleted(true);
        em.merge(user);
    }
    
}
