/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author laurent
 */
public class PhoneValidator implements ConstraintValidator<Phone, String>{

    private Pattern pattern;
    private Matcher matcher;
    private static final String PHONE_PATTERN = "^0[0-9]{9}$";
    
    @Override
    public void initialize(Phone constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String phone = value.trim();
        if (phone == null) return false;
        
        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(phone);
        if(!matcher.matches()){
            return false;
        }else{
            return true;
        }
    }
    
}
