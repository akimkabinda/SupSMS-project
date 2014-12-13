/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.service;

import java.util.List;
import javax.ejb.Local;
import sup.sms.entity.User;

/**
 *
 * @author laurent
 */
@Local
public interface IUserService {
    User logIn(String phone, String password);
    User save(User user);
    long countByPhone(String phone);
    User find(long id);
    User update(User user);
    List<User> findAll();
    boolean delete(long userId);
}
