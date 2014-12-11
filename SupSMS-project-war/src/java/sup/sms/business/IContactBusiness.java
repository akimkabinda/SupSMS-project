/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.business;

import java.util.List;
import javax.ejb.Local;
import sup.sms.entity.Contact;

/**
 *
 * @author laurent
 */
@Local
public interface IContactBusiness {
    Contact addContact(Contact contact);
    Contact find(long contactId);
    List<Contact> getClientContacts(long clientId);
}
