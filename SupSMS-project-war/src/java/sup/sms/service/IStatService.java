/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.service;

import javax.ejb.Local;

/**
 *
 * @author laurent
 */
@Local
public interface IStatService {
    long countUsers();
    long countMessages();
}
