/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import sup.sms.validator.Phone;

/**
 *
 * @author laurent
 */
@Entity
@Table(name="USERS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="USER_TYPE")
public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Size(min = 1, max = 50, message = "Lastname is required and must contain less than {max} caracters.")
    private String lastname;

    @Size(min = 1, max = 50, message = "Firstname is required and must contain less than {max} caracters.")
    private String firstname;
    
    @Email(message = "Email is not well formated.")
    @Size(min = 1, max = 100, message = "Email is required and must contain less than {max} caracters.")
    private String email;
    
    @Phone
    private String phone;

    @Size(min = 16, max = 16, message = "Credit card number must contain {max} caracters.")
    private String creditcardnumber;

    @Size(min = 1, max = 50, message = "Password must contain between {min} and {max} caracters.")
    private String password;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    
    @OneToMany(mappedBy = "contactOwner")
    private List<Contact> contacts;
    
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Invoice> invoices; 

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
   
    
    private boolean deleted;

    public boolean isDeleted() {
        return deleted;
    }

    public User setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public User setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        return this;
    }
    
    

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;return this;
    }

    public String getLastname() {
        return lastname;
    }

    public User setLastname(String lastname) {
        this.lastname = lastname;return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public User setFirstname(String firstname) {
        this.firstname = firstname;return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;return this;
    }

    public String getCreditcardnumber() {
        return creditcardnumber;
    }

    public User setCreditcardnumber(String creditcardnumber) {
        this.creditcardnumber = creditcardnumber;return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;return this;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public User setCreationdate(Date creationdate) {
        this.creationdate = creationdate;return this;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
}
