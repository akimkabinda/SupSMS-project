/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.service;

import javax.ejb.Local;
import sup.sms.entity.Invoice;
import sup.sms.entity.User;

/**
 *
 * @author laurent
 */
@Local
public interface IInvoiceService {
    boolean invoiceHasBeenPaid(User user);
    Invoice save(Invoice invoice);
}
