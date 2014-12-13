/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.service;

import java.util.List;
import javax.ejb.Local;
import sup.sms.entity.User;
import sup.sms.utils.ConversationFacade;

/**
 *
 * @author laurent
 */
@Local
public interface IMessageService {
    List<ConversationFacade> getConversations(User user);
}
