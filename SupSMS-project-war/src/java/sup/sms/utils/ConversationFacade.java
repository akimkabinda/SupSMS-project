/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.utils;

import java.util.Date;
import sup.sms.entity.Contact;
import sup.sms.entity.Message;

/**
 *
 * @author laurent
 */
public class ConversationFacade {
    private Contact contact;
    private String unknownContact;
    private Message LastMessage;
    private int messageCount;

    public Contact getContact() {
        return contact;
    }

    public ConversationFacade setContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public Message getLastMessage() {
        return LastMessage;
    }

    public ConversationFacade setLastMessage(Message LastMessage) {
        this.LastMessage = LastMessage;
        return this;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public ConversationFacade setMessageCount(int messageCount) {
        this.messageCount = messageCount;
        return this;
    }

    public String getUnknownContact() {
        return unknownContact;
    }

    public ConversationFacade setUnknownContact(String unknownContact) {
        this.unknownContact = unknownContact;
        return this;
    }
    
    
}
