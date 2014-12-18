/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import sup.sms.entity.Contact;
import sup.sms.entity.Message;
import sup.sms.entity.User;
import sup.sms.repository.MessageRepository;
import sup.sms.service.jms.MessageJmsService;
import sup.sms.utils.ConversationFacade;
import sup.sms.utils.MessageFacade;

/**
 *
 * @author laurent
 */
@Stateless
public class MessageService{

    @EJB
    MessageRepository messageRepository;
    
    @EJB
    UserService userService;
    
    @EJB
    MessageJmsService messageJmsService;

    /**
     * Get sum up of user's conversations which will contain some data about a specific conversation (last message, total count of messages, ...)
     * @param user
     * @return a list of conversation facades
     */
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
                    if(myContact.getContactPhone().equals(interlocutorPhoneNumber) && !myContact.isContactDeleted()){
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
    
    /**
     * See repo
     * @param user
     * @param interlocutorPhoneNumber
     * @return 
     */
    public List<Message> getConversation(User user, String interlocutorPhoneNumber){
        return messageRepository.findConversation(user, interlocutorPhoneNumber);
    }
    
    /**
     * See repo
     * @param message
     * @return 
     */
    public Message find(long message){
        return messageRepository.find(message);
    }
    
    /**
     * Persist new message thanks to the the user id who sent the message, the interlocutor phone number, and obviously the text message
     * @param userId
     * @param interlocutorPhoneNumber
     * @param message
     * @return 
     */
    public Message sendMessage(long userId, String interlocutorPhoneNumber, MessageFacade message){
        User transmitter = userService.find(userId);
        Message newMessage = new Message();
        newMessage.setDeleted(false);
        newMessage.setMessage(message.getMessage());
        newMessage.setReceiver(interlocutorPhoneNumber);
        newMessage.setTransmitter(transmitter.getPhone());
        newMessage.setTransmissionDate(new Date());
        try{
            Message messagePersisted = messageRepository.save(newMessage);
            messageJmsService.sendSms(messagePersisted);
            return messagePersisted;
        }catch(Exception e){
            return null;
        }
    }
}
