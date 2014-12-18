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
import sup.sms.validator.Phone;

/**
 *
 * @author laurent
 */
@Entity
@Table(name = "CONTACT")
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 1, max = 50)
    private String lastname;
    
    @NotNull
    @Size(min = 1, max = 50)
    private String firstname;
    
    @NotNull
    @Size(min = 1, max = 100)
    private String email;

    @NotNull
    @Size(min = 1, max = 200)
    private String address;
    
    @Phone
    @NotNull
    @Size(min = 1, max = 20)
    private String phone;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationdate;
    
    private boolean deleted;

    public boolean isDeleted() {
        return deleted;
    }

    public Contact setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }
    
    @ManyToOne
    private User owner;
    
    public Integer getId() {
        return id;
    }

    public Contact setId(Integer id) {
        this.id = id;return this;
    }

    public String getLastname() {
        return lastname;
    }

    public Contact setLastname(String lastname) {
        this.lastname = lastname;return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public Contact setFirstname(String firstname) {
        this.firstname = firstname;return this;
    }

    public String getEmail() {
        return email;
    }

    public Contact setEmail(String email) {
        this.email = email;return this;
    }

    public String getAddress() {
        return address;
    }

    public Contact setAddress(String address) {
        this.address = address;return this;
    }

    public String getPhone() {
        return phone;
    }

    public Contact setPhone(String phone) {
        this.phone = phone;return this;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public Contact setCreationdate(Date creationdate) {
        this.creationdate = creationdate;return this;
    }

    public User getOwner() {
        return owner;
    }

    public Contact setOwner(User owner) {
        this.owner = owner;
        return this;
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
