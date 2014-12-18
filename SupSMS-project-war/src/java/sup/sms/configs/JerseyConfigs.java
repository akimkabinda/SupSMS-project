/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sup.sms.configs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import sup.sms.controller.api.ClientService;
import sup.sms.controller.api.MessageApiService;

/**
 *
 * @author laurent
 */
@ApplicationPath("api")
public class JerseyConfigs extends Application{
    @Override
    public Set<Class<?>> getClasses(){
        return new HashSet<Class<?>>(
            Arrays.asList(ClientService.class, MessageApiService.class)
        );
    }
}
