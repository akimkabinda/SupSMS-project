/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author laurent
 */
@Entity
@DiscriminatorValue("CLIENT")
public class Client extends User implements Serializable {
    private static final long serialVersionUID = 1L;
}
