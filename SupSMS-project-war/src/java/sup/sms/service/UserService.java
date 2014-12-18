/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import sup.sms.entity.User;
import sup.sms.repository.UserRepository;

/**
 *
 * @author laurent
 */
@Stateless
public class UserService{

    @EJB
    UserRepository userRepository;
    
    /**
     * Login method
     * @param phone
     * @param password
     * @return a user if it's a succesful login operation or null in the other case
     */
    public User logIn(String phone, String password) {
        List<User> user = userRepository.findByPhoneAndPassword(phone, password);
        if(user.isEmpty() || user.size() > 1){
            return null;
        }
        return user.get(0);
    }

    /**
     * See repo
     * @param user
     * @return 
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * See repo
     * @param phone
     * @return 
     */
    public long countByPhone(String phone) {
        return userRepository.countByPhone(phone);
    }

    /**
     * See repo
     * @param id
     * @return 
     */
    public User find(long id) {
        return userRepository.find(id);
    }

    /**
     * See repo
     * @param user
     * @return 
     */
    public User update(User user) {
        return userRepository.update(user);
    }

    /**
     * See repo
     * @return 
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * See repo
     * @param userId
     * @return 
     */
    public boolean delete(long userId) {
        try{
            User user = this.find(userId);
            userRepository.delete(user);
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
