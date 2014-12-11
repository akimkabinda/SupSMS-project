/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.business;

import javax.ejb.Local;
import sup.sms.entity.User;

/**
 *
 * @author laurent
 */
@Local
public interface IUserBusiness {
    User logIn(String phone, String password);
    User signIn(User user);
    long countByPhone(String phone);
}
