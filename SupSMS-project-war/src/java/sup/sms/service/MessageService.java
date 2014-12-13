/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import sup.sms.entity.Contact;
import sup.sms.entity.Message;
import sup.sms.entity.User;
import sup.sms.repository.MessageRepository;
import sup.sms.utils.ConversationFacade;

/**
 *
 * @author laurent
 */
@Stateless
public class MessageService implements IMessageService{

    @EJB
    MessageRepository messageRepository;
    
    
    @Override
    public List<ConversationFacade> getConversations(User user) {
        //List of conversations
        List<ConversationFacade> conversations = new ArrayList<ConversationFacade>();

        //Find all different conversations contacts
        List<String> distinctReceiversAsTransmitter = messageRepository.findDistinctReceiversAsTransmitter(user);
        List<String> distinctTransmittersAsReceiver = messageRepository.findDistinctTransmittersAsReceiver(user);
        
        Set<String> conversationDistinctInterlocutorPhoneNumbers = new HashSet<String>((List<String>) distinctReceiversAsTransmitter);
        conversationDistinctInterlocutorPhoneNumbers.addAll((List<String>) distinctTransmittersAsReceiver);
        
        if(conversationDistinctInterlocutorPhoneNumbers.size() != 0){
            List<Contact> myContacts = user.getContacts();
        
            for (String interlocutorPhoneNumber : conversationDistinctInterlocutorPhoneNumbers) {
                ConversationFacade conversationFacade = new ConversationFacade();
                //Try to find in our contacts list
                for (Contact myContact : myContacts) {
                    if(myContact.getPhone().equals(interlocutorPhoneNumber)){
                        conversationFacade.setContact(myContact);
                    }
                }
                //Set interlocutor phone number
                conversationFacade.setUnknownContact(interlocutorPhoneNumber);

                //Find last message of the conversation
                List<Message> messages = messageRepository.findConversation(user, interlocutorPhoneNumber);
                conversationFacade.setLastMessage(messages.get(0));
                //Find number of message exchanged
                conversationFacade.setMessageCount(messages.size());

                conversations.add(conversationFacade);
            }
        }
        return conversations;
    }
    
    
}
