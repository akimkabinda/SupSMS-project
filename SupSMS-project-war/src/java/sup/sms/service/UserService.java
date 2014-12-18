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
    
    public User logIn(String phone, String password) {
        List<User> user = userRepository.findByPhoneAndPassword(phone, password);
        if(user.isEmpty() || user.size() > 1){
            return null;
        }
        return user.get(0);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public long countByPhone(String phone) {
        return userRepository.countByPhone(phone);
    }

    public User find(long id) {
        return userRepository.find(id);
    }

    public User update(User user) {
        return userRepository.update(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean delete(long userId) {
        try{
            User user = this.find(userId);
            userRepository.delete(user);
        }catch(Exception e){
            return false;
        }
        return true;
    }
    
    public User findByPhone(String phone){
        List<User> users = userRepository.findByPhone(phone);
        if(users.size() > 0 || users.size() == 0){
            return null;
        }
        return users.get(0);
    }

}
