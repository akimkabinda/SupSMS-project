/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import sup.sms.repository.StatsRepository;

/**
 *
 * @author laurent
 */
@Stateless
public class StatsBusiness implements IStatsBusiness{

    @EJB
    StatsRepository statsRepository;
    
    @Override
    public long countUsers() {
        return statsRepository.countUsers();
    }

    @Override
    public long countMessages() {
        return statsRepository.countMessages();
    }
    
}
