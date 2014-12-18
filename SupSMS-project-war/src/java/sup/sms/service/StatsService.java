/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import sup.sms.repository.StatsRepository;

/**
 *
 * @author laurent
 */
@Stateless
public class StatsService{

    @EJB
    StatsRepository statsRepository;

    /**
     * See repo
     * @return 
     */
    public long countUsers() {
        return statsRepository.countUsers();
    }

    /**
     * See repo
     * @return 
     */
    public long countMessages() {
        return statsRepository.countMessages();
    }
    
}
