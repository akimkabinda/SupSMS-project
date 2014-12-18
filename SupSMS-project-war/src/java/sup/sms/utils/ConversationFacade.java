/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import sup.sms.entity.Contact;
import sup.sms.entity.Message;

/**
 *
 * @author laurent
 */
public class ConversationFacade implements Serializable{
    private static final long serialVersionUID = 1L;
    private Contact contact;
    private String unknownContact;
    private Message LastMessage;
    private int messageCount;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Message getLastMessage() {
        return LastMessage;
    }

    public void setLastMessage(Message LastMessage) {
        this.LastMessage = LastMessage;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public String getUnknownContact() {
        return unknownContact;
    }

    public void setUnknownContact(String unknownContact) {
        this.unknownContact = unknownContact;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.contact);
        hash = 97 * hash + Objects.hashCode(this.unknownContact);
        hash = 97 * hash + Objects.hashCode(this.LastMessage);
        hash = 97 * hash + this.messageCount;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConversationFacade other = (ConversationFacade) obj;
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        if (!Objects.equals(this.unknownContact, other.unknownContact)) {
            return false;
        }
        if (!Objects.equals(this.LastMessage, other.LastMessage)) {
            return false;
        }
        if (this.messageCount != other.messageCount) {
            return false;
        }
        return true;
    }
    
    
}
