/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.utils;

import java.io.Serializable;

/**
 *
 * @author laurent
 */
/**
 * Facade to get post message dat through the api
 * @author laurent
 */
public class MessageFacade implements Serializable{
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
