/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.Email;
import sup.sms.validator.Phone;

/**
 *
 * @author laurent
 */
@Entity
@XmlRootElement(name = "Contact")
@Table(name = "CONTACT")
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @Size(min = 1, max = 50, message = "Lastname is required and must contain less than {max} caracters.")
    private String contactLastname;
    
    @Size(min = 1, max = 50, message = "Firstname is required and must contain less than {max} caracters.")
    private String contactFirstname;
    
    @Email(message = "Email is not well formated.")
    @Size(min = 1, max = 100, message = "Email is required and must contain less than {max} caracters.")
    private String contactEmail;

    @Size(min = 0, max = 200, message = "Address must contain less than {max} caracters.")
    private String contactAddress;
    
    @Phone
    private String contactPhone;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date contactCreationdate;
    
    private boolean contactDeleted;

    @ManyToOne
    private User contactOwner;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContactLastname() {
        return contactLastname;
    }

    public Contact setContactLastname(String contactLastname) {
        this.contactLastname = contactLastname;return this;
    }

    public String getContactFirstname() {
        return contactFirstname;
    }

    public Contact setContactFirstname(String contactFirstname) {
        this.contactFirstname = contactFirstname;return this;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public Contact setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;return this;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public Contact setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;return this;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public Contact setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;return this;
    }

    public Date getContactCreationdate() {
        return contactCreationdate;
    }

    public Contact setContactCreationdate(Date contactCreationdate) {
        this.contactCreationdate = contactCreationdate;return this;
    }

    public boolean isContactDeleted() {
        return contactDeleted;
    }

    public Contact setContactDeleted(boolean contactDeleted) {
        this.contactDeleted = contactDeleted;return this;
    }

    public User getContactOwner() {
        return contactOwner;
    }

    public Contact setContactOwner(User contactOwner) {
        this.contactOwner = contactOwner;return this;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
