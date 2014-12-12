/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.business;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.validation.ReportAsSingleViolation;
import sup.sms.entity.User;
import sup.sms.repository.UserRepository;

/**
 *
 * @author laurent
 */
@Stateless
public class UserBusiness implements IUserBusiness{

    @EJB
    UserRepository userRepository;
    
    @Override
    public User logIn(String phone, String password) {
        List<User> user = userRepository.findByPhoneAndPassword(phone, password);
        if(user.isEmpty() || user.size() > 1){
            return null;
        }
        return user.get(0);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public long countByPhone(String phone) {
        return userRepository.countByPhone(phone);
    }

    @Override
    public User find(long id) {
        return userRepository.find(id);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
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
