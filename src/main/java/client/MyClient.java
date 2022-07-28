/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package client;

import Entity.Customer;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author muzz
 */
@RegisterRestClient(configKey = "myclient")
public interface MyClient {
    
    @ClientHeaderParam(name = "authorization",value = "{generateJWTToken}")
    @RolesAllowed("Admin")
    @GET
    public String sayHello();
    
    
    @ClientHeaderParam(name = "authorization",value = "{generateJWTToken}")
    @RolesAllowed("Admin")
    @GET
    @Path("/GetAllCustomer")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Customer> GetAllCustomer();
    
    default String generateJWTToken(){
        Config config = ConfigProvider.getConfig();
        String token = config.getValue("jwt-string", String.class);
        String authtoken = "Bearer "+token;
        return authtoken;
    }
}
