/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.repository;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import sup.sms.entity.Contact;
import sup.sms.entity.Contact_;
import sup.sms.entity.Invoice;
import sup.sms.entity.Invoice_;

/**
 *
 * @author laurent
 */
@Stateless
@LocalBean
public class InvoiceRepository {
    @PersistenceContext
    private EntityManager em;
    
    public Invoice save(Invoice invoice){
        em.persist(invoice);
        return invoice;
    }
    
    public List<Invoice> getInvoicesByUserOrderByBeginDateDesc(long userId){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Invoice> query = 
			criteriaBuilder.createQuery(Invoice.class);
        Root<Invoice> invoice = query.from(Invoice.class);
        
        Predicate ownerFilter = criteriaBuilder.equal(invoice.get(Invoice_.owner), userId);
        
        query.where(ownerFilter);
        query.orderBy(criteriaBuilder.desc(invoice.get(Invoice_.beginDate)));

        return em.createQuery(query).getResultList();
    }
}
